import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDetailList,
  houseListAll,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "시/군/구" }],
    dongs: [{ value: null, text: "읍/면/동" }],
    houses: [],
    house: null,
    markerPosition: [],
    searchPosition: [],
    detailHouses: [],
    detailFlag: false,
    place: [],
    conveniencePlaces: [],
    schoolPlaces: [],
    stationPlaces: [],
    bankPlaces: [],
    cafePlaces: [],
    hospitalPlaces: [],
    culturalPlaces: [],
    detailPlaceFlag: false,
    neLat: "",
    neLng: "",
    swLat: "",
    swLng: "",
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_DONG_LIST2: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongName, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "시/군/구" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "읍/면/동" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.detailHouses = house;
    },
    SET_MARKER_POSITION: (state) => {
      state.markerPosition = [];
      state.houses.forEach((house) => {
        state.markerPosition.push({
          apartmentName: house.apartmentName,
          aptCode: house.aptCode,
          recentPrice: house.recentPrice,
          position: [house.lat, house.lng],
        });
      });
    },
    SET_SEARCH_POSITION: (state) => {
      state.searchPosition = [];
      state.houses.forEach((house) => {
        state.searchPosition.push({
          apartmentName: house.apartmentName,
          aptCode: house.aptCode,
          recentPrice: house.recentPrice,
          position: [house.lat, house.lng],
        });
      });
    },
    SET_DETAIL_PLACE: (state, place) => {
      state.place = [];
      state.place.push({
        place_url: place.place_url,
        category_name: place.category_name,
        category_group_name: place.category_group_name,
        place_name: place.place_name,
        road_address_name: place.road_address_name,
        address_name: place.address_name,
        phone: place.phone,
        distance: "",
      });
    },
    SET_CONVENIENCE_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.conveniencePlaces.push(places[i]);
      }
    },
    SET_SCHOOL_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.schoolPlaces.push(places[i]);
      }
    },
    SET_STATION_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.stationPlaces.push(places[i]);
      }
    },
    SET_BANK_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.bankPlaces.push(places[i]);
      }
    },
    SET_CAFE_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.cafePlaces.push(places[i]);
      }
    },
    SET_HOSPITAL_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.hospitalPlaces.push(places[i]);
      }
    },
    SET_CULTURAL_PLACES: (state, places) => {
      for (let i = 0; i < places.length; i++) {
        if (i == 5) return;
        state.culturalPlaces.push(places[i]);
      }
    },
    SET_SEARCH_POSITION_INIT: (state) => {
      state.searchPosition = [];
    },
    SET_DETAIL_FLAG_TRUE: (state) => {
      state.detailFlag = true;
    },
    SET_DETAIL_PLACE_FLAG_TRUE: (state) => {
      state.detailPlaceFlag = true;
    },
    SET_DETAIL_FLAG_FALSE: (state) => {
      state.detailFlag = false;
      state.detailPlaceFlag = false;
    },
    SET_PLACES_INIT: (state) => {
      state.conveniencePlaces = [];
      state.schoolPlaces = [];
      state.stationPlaces = [];
      state.bankPlaces = [];
      state.cafePlaces = [];
      state.hospitalPlaces = [];
      state.culturalPlaces = [];
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong2: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST2", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response.data);
          commit("SET_MARKER_POSITION");
          commit("SET_SEARCH_POSITION");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseListAll: ({ commit }, loc) => {
      const params = {
        swLng: loc.swLng,
        swLat: loc.swLat,
        neLng: loc.neLng,
        neLat: loc.neLat,
      };
      houseListAll(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response.data);
          commit("SET_MARKER_POSITION");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, aptCode) => {
      houseDetailList(
        aptCode,
        (response) => {
          commit("SET_DETAIL_HOUSE", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailPlace: ({ commit }, place) => {
      commit("SET_DETAIL_PLACE", place);
    },
    setConveniencePlaces: ({ commit }, places) => {
      commit("SET_CONVENIENCE_PLACES", places);
    },
    setSchoolPlaces: ({ commit }, places) => {
      commit("SET_SCHOOL_PLACES", places);
    },
    setStationPlaces: ({ commit }, places) => {
      commit("SET_STATION_PLACES", places);
    },
    setbankPlaces: ({ commit }, places) => {
      commit("SET_BANK_PLACES", places);
    },
    setCafePlaces: ({ commit }, places) => {
      commit("SET_CAFE_PLACES", places);
    },
    setHospitalPlaces: ({ commit }, places) => {
      commit("SET_HOSPITAL_PLACES", places);
    },
    setCulturalPlaces: ({ commit }, places) => {
      commit("SET_CULTURAL_PLACES", places);
    },
    setFlag: ({ commit }) => {
      commit("SET_DETAIL_FLAG_TRUE");
    },
    setPlaceFlag: ({ commit }) => {
      commit("SET_DETAIL_PLACE_FLAG_TRUE");
    },
    setFlagFalse: ({ commit }) => {
      commit("SET_DETAIL_FLAG_FALSE");
    },
    setSearchInit: ({ commit }) => {
      commit("SET_SEARCH_POSITION_INIT");
    },
    setPlacesInit: ({ commit }) => {
      commit("SET_PLACES_INIT");
    },
  },
};

export default houseStore;
