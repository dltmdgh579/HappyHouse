"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[474],{2474:function(e,t,s){s.r(t),s.d(t,{default:function(){return L}});var o=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("b-container",{staticClass:"bv-example-row mt-3 text-center"},[s("h3",{staticClass:"underline-orange"},[s("b-icon",{attrs:{icon:"house-fill"}}),e._v(" House Service ")],1),s("b-row",[s("b-col",[s("house-search-bar")],1)],1),s("b-row",[s("b-col",{attrs:{cols:"6",align:"left"}},[s("house-list")],1),s("b-col",{attrs:{cols:"6"}},[s("house-detail")],1)],1)],1)},r=[],a=s(3047),l=function(){var e=this,t=e.$createElement,s=e._self._c||t;return e.houses&&0!=e.houses.length?s("b-container",{staticClass:"bv-example-row mt-3"},e._l(e.houses,(function(e,t){return s("house-list-item",{key:t,attrs:{house:e}})})),1):s("b-container",{staticClass:"bv-example-row mt-3"},[s("b-row",[s("b-col",[s("b-alert",{attrs:{show:""}},[e._v("주택 목록이 없습니다.")])],1)],1)],1)},n=[],u=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("b-row",{staticClass:"m-2",class:{"mouse-over-bgcolor":e.isColor},on:{click:e.selectHouse,mouseover:function(t){return e.colorChange(!0)},mouseout:function(t){return e.colorChange(!1)}}},[s("b-col",{staticClass:"text-center align-self-center",attrs:{cols:"2"}},[s("b-img",{attrs:{thumbnail:"",src:"https://picsum.photos/250/250/?image=58",alt:"Image 1"}})],1),s("b-col",{staticClass:"align-self-center",attrs:{cols:"10"}},[e._v(" ["+e._s(e.house.aptCode)+"] "+e._s(e.house.apartmentName)+" ")])],1)},c=[],i=s(4665);const h="houseStore";var m={name:"HouseListItem",data(){return{isColor:!1}},props:{house:Object},methods:{...(0,i.nv)(h,["detailHouse"]),selectHouse(){this.detailHouse(this.house)},colorChange(e){this.isColor=e}}},b=m,p=s(1001),f=(0,p.Z)(b,u,c,!1,null,"7d6770f2",null),v=f.exports;const C="houseStore";var _={name:"HouseList",components:{HouseListItem:v},data(){return{}},computed:{...(0,i.rn)(C,["houses"])}},d=_,g=(0,p.Z)(d,l,n,!1,null,null,null),H=g.exports,w=s(1367),x={name:"HouseView",components:{HouseSearchBar:a.Z,HouseList:H,HouseDetail:w.Z}},k=x,Z=(0,p.Z)(k,o,r,!1,null,"15a80a82",null),L=Z.exports}}]);
//# sourceMappingURL=474.bd9b1a98.js.map