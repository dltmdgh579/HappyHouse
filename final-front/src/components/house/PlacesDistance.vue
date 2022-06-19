<template>
  <div>
    <div class="accordion" role="tablist">
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-1 variant="outline-info"
            >편의점</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in conveniencePlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-2 variant="outline-info"
            >학교</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in schoolPlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-3 variant="outline-info"
            >지하철역</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in stationPlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-4 variant="outline-info"
            >병원</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-4" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in hospitalPlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-5 variant="outline-info"
            >카페</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-5" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in cafePlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-6 variant="outline-info"
            >은행</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-6" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in bankPlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-7 variant="outline-info"
            >문화시설</b-button
          >
        </b-card-header>
        <b-collapse id="accordion-7" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text v-for="place in culturalPlaces" :key="place.phone">
              <b-row>
                <b-col
                  ><h5>{{ place.place_name }}</h5></b-col
                >
              </b-row>
              <b-row>
                <b-col
                  ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
                >
              </b-row>
              <hr
            /></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>
    <!-- <div class="accordion" role="tablist">
      <my-component
        group="편의점"
        :places="conveniencePlaces"
        accordionid="accordion-1"
      ></my-component>
      <my-component
        group="학교"
        :places="schoolPlaces"
        accordionid="accordion-2"
      ></my-component>
      <my-component
        group="지하철역"
        :places="stationPlaces"
        accordionid="accordion-3"
      ></my-component>
      <my-component
        group="병원"
        :places="hospitalPlaces"
        accordionid="accordion-4"
      ></my-component>
      <my-component
        group="카페"
        :places="cafePlaces"
        accordionid="accordion-5"
      ></my-component>
      <my-component
        group="은행"
        :places="bankPlaces"
        accordionid="accordion-6"
      ></my-component>
      <my-component
        group="문화시설"
        :places="culturalPlaces"
        accordionid="accordion-7"
      ></my-component>
    </div> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

// const MyComponent = {
//   template: `
//       <b-card no-body class="mb-1">
//         <b-card-header header-tag="header" class="p-1" role="tab">
//           <b-button block v-b-toggle.accordion-1 variant="outline-primary"
//             >{{group}}</b-button
//           >
//         </b-card-header>
//         <b-collapse :id="accordionid" accordion="my-accordion" role="tabpanel">
//           <b-card-body>
//             <b-card-text v-for="place in places" :key="place.phone">
//               <b-row>
//                 <b-col
//                   ><h5>{{ place.place_name }}</h5></b-col
//                 >
//               </b-row>
//               <b-row>
//                 <b-col
//                   ><h6>거리 : {{ parseInt(place.distance) }}m</h6></b-col
//                 >
//               </b-row>
//               <hr
//             /></b-card-text>
//           </b-card-body>
//         </b-collapse>
//       </b-card>
//   `,
//   props: {
//     group: {
//       type: String,
//     },
//     places: {
//       type: Array,
//     },
//     accordionid: {
//       type: String,
//     },
//   },
// };

export default {
  name: "PlacesDistance",
  // components: {
  //   MyComponent,
  // },
  computed: {
    ...mapState(houseStore, [
      "conveniencePlaces",
      "schoolPlaces",
      "stationPlaces",
      "bankPlaces",
      "cafePlaces",
      "hospitalPlaces",
      "culturalPlaces",
    ]),
  },
};
</script>

<style></style>
