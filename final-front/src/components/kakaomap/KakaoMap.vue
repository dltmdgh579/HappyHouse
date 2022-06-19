<template>
  <div>
    <div id="map"></div>
    <ul id="category">
      <li id="BK9" data-order="0">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      pointMa: 0,
      pointqa: 0,
      markers: [],
      categoryCodes: ["CS2", "SC4", "SW8", "BK9", "HP8", "CE7", "CT1"],
      customOverlays: [],
      categoryMarkers: [],
      currCategory: "",
      contentNode: null,
      ps: null,
      flag: false,
      placeOverlay: null,
      infowindow: null,
      map: null,
    };
  },
  computed: {
    ...mapState("houseStore", [
      "markerPosition",
      "searchPosition",
      "houses",
      "detailFlag",
      "neLat",
      "neLng",
      "swLat",
      "swLng",
      "places",
    ]),
  },
  watch: {
    markerPosition: function (val) {
      this.displayMarker(val);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=16b04a4faa40337d9d0b3f42b9a7af34&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, [
      "detailHouse",
      "setFlag",
      "setPlaceFlag",
      "getHouseListAll",
      "setSearchInit",
      "detailPlace",
      "setFlagFalse",
      "setConveniencePlaces",
      "setSchoolPlaces",
      "setStationPlaces",
      "setbankPlaces",
      "setCafePlaces",
      "setHospitalPlaces",
      "setCulturalPlaces",
      "setPlacesInit",
    ]),
    initMap() {
      const container = document.getElementById("map");
      container.style.width = "100vw";
      container.style.height = "100vh";
      const options = {
        center: new kakao.maps.LatLng(37.5428969, 126.9647623),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      this.categoryMarkers = []; // 마커를 담을 배열입니다
      this.currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map);
      // 지도에 idle 이벤트를 등록합니다

      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      kakao.maps.event.addListener(this.map, "click", this.setFalse);
      kakao.maps.event.addListener(this.map, "dragend", this.setFalse);
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
      // 각 카테고리에 클릭 이벤트를 등록합니다
      this.addCategoryClickEvent();

      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // console.log("flag : " + this.flag);
      if (!this.flag) {
        this.getHouseListAll({
          neLat: "37.54880290287746",
          neLng: "126.97215050914262",
          swLat: "37.53694130034369",
          swLng: "126.95726498904666",
        });
      }
      kakao.maps.event.addListener(this.map, "bounds_changed", () => {
        // 지도 영역정보를 얻어옵니다
        var bounds = this.map.getBounds();

        // 영역정보의 남서쪽 정보를 얻어옵니다
        var swLatlng = bounds.getSouthWest();

        // 영역정보의 북동쪽 정보를 얻어옵니다
        // var neLatlng = bounds.getNorthEast();
        if (
          Math.abs(swLatlng.Ma - this.pointMa) >= 0.001 ||
          Math.abs(swLatlng.La - this.pointqa) >= 0.001
        ) {
          this.pointMa = swLatlng.Ma;
          this.pointqa = swLatlng.qa;
          var neLat = bounds.getNorthEast().getLat();
          var neLng = bounds.getNorthEast().getLng();
          var swLat = bounds.getSouthWest().getLat();
          var swLng = bounds.getSouthWest().getLng();

          this.getHouseListAll({
            neLat: neLat,
            neLng: neLng,
            swLat: swLat,
            swLng: swLng,
          });
        }
      });
    },

    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },

    setFalse() {
      this.setFlagFalse();
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      console.log("pagination" + pagination);
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order,
        );

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        ((marker, place) => {
          kakao.maps.event.addListener(marker, "click", () => {
            this.detailPlace(place);
            this.setPlaceFlag();
          });
        })(marker, places[i]);
      }
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions,
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.categoryMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.categoryMarkers.length; i++) {
        this.categoryMarkers[i].setMap(null);
      }
      this.categoryMarkers = [];
    },

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;

      for (let i = 0; i < children.length; i++) {
        // console.log(children[i]);
        children[i].onclick = () => {
          console.log(children.className);
          this.onClickCategory(children[i].id, children[i].className);
        };
      }
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(id, className) {
      // var id = this.id,
      //   className = this.className;
      this.placeOverlay.setMap(null);
      console.log(className);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        if (id == "BK9") {
          this.changeCategoryClass(
            document.getElementById("category").children.BK9,
          );
        } else if (id == "CE7") {
          this.changeCategoryClass(
            document.getElementById("category").children.CE7,
          );
        } else if (id == "CS2") {
          this.changeCategoryClass(
            document.getElementById("category").children.CS2,
          );
        } else if (id == "MT1") {
          this.changeCategoryClass(
            document.getElementById("category").children.MT1,
          );
        } else if (id == "OL7") {
          this.changeCategoryClass(
            document.getElementById("category").children.OL7,
          );
        } else if (id == "PM9") {
          this.changeCategoryClass(
            document.getElementById("category").children.PM9,
          );
        }

        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }
      if (el) {
        el.className = "on";
      }
    },

    displayMarker(markerPositions) {
      if (this.searchPosition.length > 0) {
        let moveLatLng = new kakao.maps.LatLng(
          this.searchPosition[0].position[0],
          this.searchPosition[0].position[1],
        );
        this.setSearchInit();
        this.map.panTo(moveLatLng);
      }
      if (!this.flag) {
        this.flag = true;
        const positions = markerPositions.map(
          (p) => new kakao.maps.LatLng(...p.position),
        );
        for (let i = 0; i < markerPositions.length; i++) {
          let str = markerPositions[i].recentPrice.split(",")[0].split("");
          let container = document.createElement("div");
          let content = document.createElement("div");
          content.className = "customoverlay";
          if (markerPositions[i].recentPrice.split(",")[0] > 100) {
            content.style =
              "padding:7px;position:relative;background-color: rgb(245, 151, 75);color: white;bottom:40px;border-radius:.4em;float:left;";
          } else if (markerPositions[i].recentPrice.split(",")[0] > 50) {
            content.style =
              "padding:7px;position:relative;background-color: rgb(74, 125, 235);color: white;bottom:40px;border-radius:.4em;float:left;";
          } else {
            content.style =
              "padding:7px;position:relative;background-color: rgb(74, 157, 235);color: white;bottom:40px;border-radius:.4em;float:left;";
          }
          let title = document.createElement("span");
          title.className = "title";
          title.onclick = () => {
            this.detailView(i, positions[i]);
          };
          if (str[str.length - 2]) {
            if (str[str.length - 3]) {
              title.appendChild(
                document.createTextNode(
                  str[str.length - 3] +
                    str[str.length - 2] +
                    "." +
                    str[str.length - 1] +
                    "억",
                ),
              );
            } else {
              title.appendChild(
                document.createTextNode(
                  str[str.length - 2] + "." + str[str.length - 1] + "억",
                ),
              );
            }
          } else {
            title.appendChild(
              document.createTextNode(str[str.length - 1] + "천"),
            );
          }
          content.appendChild(title);
          container.appendChild(content);

          let imageSrc = require("@/assets/apart.png"), // 마커이미지의 주소입니다
            imageSize = new kakao.maps.Size(50, 50); // 마커이미지의 크기입니다

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
          let marker = new kakao.maps.Marker({
            map: this.map,
            title: markerPositions[i].apartmentName,
            position: positions[i],
            image: markerImage,
            zIndex: 2,
          });
          let customOverlay = new kakao.maps.CustomOverlay({
            map: this.map,
            position: positions[i],
            content: content,
            zIndex: 3,
            yAnchor: 1,
          });
          kakao.maps.event.addListener(marker, "click", () => {
            this.map.panTo(positions[i]);
            this.detailHouse(this.houses[i].aptCode);
            this.setFlag();
          });
          this.markers.push(marker);
          this.customOverlays.push(customOverlay);
          customOverlay.setMap(this.map);
        }
      } else {
        var level = this.map.getLevel();
        if (level <= 3) {
          //마커 조회 및 표출
          if (this.markers.length > 0) {
            // this.markers.forEach((marker) => marker.setMap(null));
            for (var i = 0; i < this.markers.length; i++) {
              this.markers[i].setMap(null);
              this.customOverlays[i].setMap(null);
            }
            this.markers = [];
            this.customOverlays = [];
          }
          const positions = markerPositions.map(
            (p) => new kakao.maps.LatLng(...p.position),
          );
          for (let i = 0; i < markerPositions.length; i++) {
            let str = markerPositions[i].recentPrice.split(",")[0].split("");
            let content = document.createElement("div");
            content.className = "customoverlay";
            if (markerPositions[i].recentPrice.split(",")[0] > 100) {
              content.style =
                "padding:7px;position:relative;background-color: rgb(245, 151, 75);color: white;bottom:40px;border-radius:.4em;float:left;";
            } else if (markerPositions[i].recentPrice.split(",")[0] > 50) {
              content.style =
                "padding:7px;position:relative;background-color: rgb(74, 125, 235);color: white;bottom:40px;border-radius:.4em;float:left;";
            } else {
              content.style =
                "padding:7px;position:relative;background-color: rgb(74, 157, 235);color: white;bottom:40px;border-radius:.4em;float:left;";
            }
            let title = document.createElement("span");
            title.className = "title";
            title.onclick = () => {
              this.detailView(i, positions[i]);
            };
            if (str[str.length - 2]) {
              if (str[str.length - 3]) {
                title.appendChild(
                  document.createTextNode(
                    str[str.length - 3] +
                      str[str.length - 2] +
                      "." +
                      str[str.length - 1] +
                      "억",
                  ),
                );
              } else {
                title.appendChild(
                  document.createTextNode(
                    str[str.length - 2] + "." + str[str.length - 1] + "억",
                  ),
                );
              }
            } else {
              title.appendChild(
                document.createTextNode(str[str.length - 1] + "천"),
              );
            }
            content.appendChild(title);
            let imageSrc = require("@/assets/apart.png"), // 마커이미지의 주소입니다
              imageSize = new kakao.maps.Size(50, 50); // 마커이미지의 크기입니다

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            let marker = new kakao.maps.Marker({
              map: this.map,
              title: markerPositions[i].apartmentName,
              position: positions[i],
              image: markerImage,
              zIndex: 2,
            });
            let customOverlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: positions[i],
              content: content,
              zIndex: 3,
              yAnchor: 1,
            });
            kakao.maps.event.addListener(marker, "click", () => {
              this.map.panTo(positions[i]);
              this.detailHouse(this.houses[i].aptCode);
              this.setFlag();
            });
            this.markers.push(marker);
            this.customOverlays.push(customOverlay);
            customOverlay.setMap(this.map);
          }
          if (positions.length > 0) {
            // this.markers = positions.map(
            //   (position) =>
            //     new kakao.maps.Marker({
            //       map: this.map,
            //       position,
            //     }),
            // );
            // const bounds = positions.reduce(
            //   (bounds, latlng) => bounds.extend(latlng),
            //   new kakao.maps.LatLngBounds(),
            // );
            // this.map.setBounds(bounds);
          }
        } else {
          console.log("else");
          //마커 제거
          for (var j = 0; j < this.markers.length; j++) {
            this.markers[j].setMap(null);
            this.customOverlays[j].setMap(null);
          }
          this.markers = [];
          this.customOverlays = [];
        }
      }
    },

    detailView(num, positions) {
      this.map.panTo(positions);
      this.detailHouse(this.houses[num].aptCode);
      this.getDistance(positions);
      this.setFlag();
    },

    placesSearchSaveCB(data, status, pagination) {
      console.log("pagination" + pagination);
      if (status === kakao.maps.services.Status.OK) {
        // console.log("placesSearchSaveCB");
        // 정상적으로 검색이 완료됐으면
        if (data[0] && data[0].category_group_name === "편의점") {
          this.setConveniencePlaces(data);
        }
        if (data[0] && data[0].category_group_name === "학교") {
          this.setSchoolPlaces(data);
        }
        if (data[0] && data[0].category_group_name === "지하철역") {
          this.setStationPlaces(data);
        }
        if (data[0] && data[0].category_group_name === "은행") {
          this.setbankPlaces(data);
        }
        if (data[0] && data[0].category_group_name === "카페") {
          this.setCafePlaces(data);
        }
        if (data[0] && data[0].category_group_name === "병원") {
          this.setHospitalPlaces(data);
        }
        if (data[0] && data[0].category_group_name === "문화시설") {
          this.setCulturalPlaces(data);
        }
        // console.log("places : " + this.places);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // console.log("zero_result");
      } else if (status === kakao.maps.services.Status.ERROR) {
        // console.log("error");
      }
    },

    getNearPlaces(positions) {
      for (let i = 0; i < this.categoryCodes.length; i++) {
        this.ps.categorySearch(this.categoryCodes[i], this.placesSearchSaveCB, {
          sort: kakao.maps.services.SortBy.DISTANCE,
          location: positions,
          radius: 500,
        });
      }
    },

    getDistance(positions) {
      this.setPlacesInit();
      this.getNearPlaces(positions);
      setTimeout(() => {
        for (let i = 0; i < this.places.length; i++) {
          let polyline = new kakao.maps.Polyline({
            path: [
              positions,
              new kakao.maps.LatLng(this.places[i].y, this.places[i].x),
            ],
          });

          this.places[i].distance = polyline.getLength();
          console.log(
            "distance : " +
              this.places[i].distance +
              " " +
              this.places[i].place_name,
          );
        }
      }, 500);
    },

    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 100px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

.customoverlay {
  position: relative;
  background-color: rgb(74, 157, 235);
  color: white;
  bottom: 85px;
  border-radius: 0.4em;
  border: 1px solid rgb(245, 151, 75);
  border-bottom: 2px solid #ddd;
  float: left;
  padding: 10px;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
  padding-left: 0;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
