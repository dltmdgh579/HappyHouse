"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[474],{22474:function(e,t,s){s.r(t),s.d(t,{default:function(){return S}});var o=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("b-container",{staticClass:"bv-example-row mt-3 text-center"},[s("h3",{staticClass:"underline-orange"},[s("b-icon",{attrs:{icon:"house-fill"}}),e._v(" House Service ")],1),s("b-row",[s("b-col",[s("house-search-bar")],1)],1),s("b-row",[s("b-col",{attrs:{cols:"6",align:"left"}},[s("house-list")],1),s("b-col",{attrs:{cols:"6"}},[s("house-detail")],1)],1)],1)},n=[],r=s(43047),a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return e.houses&&0!=e.houses.length?s("b-container",{staticClass:"bv-example-row mt-3"},e._l(e.houses,(function(e,t){return s("house-list-item",{key:t,attrs:{house:e}})})),1):s("b-container",{staticClass:"bv-example-row mt-3"},[s("b-row",[s("b-col",[s("b-alert",{attrs:{show:""}},[e._v("주택 목록이 없습니다.")])],1)],1)],1)},l=[],u=s(93019),c=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("b-row",{staticClass:"m-2",class:{"mouse-over-bgcolor":e.isColor},on:{click:e.selectHouse,mouseover:function(t){return e.colorChange(!0)},mouseout:function(t){return e.colorChange(!1)}}},[s("b-col",{staticClass:"text-center align-self-center",attrs:{cols:"2"}},[s("b-img",{attrs:{thumbnail:"",src:"https://picsum.photos/250/250/?image=58",alt:"Image 1"}})],1),s("b-col",{staticClass:"align-self-center",attrs:{cols:"10"}},[e._v(" ["+e._s(e.house.aptCode)+"] "+e._s(e.house.apartmentName)+" ")])],1)},i=[],h=s(34665),m="houseStore",b={name:"HouseListItem",data:function(){return{isColor:!1}},props:{house:Object},methods:(0,u.Z)((0,u.Z)({},(0,h.nv)(m,["detailHouse"])),{},{selectHouse:function(){this.detailHouse(this.house)},colorChange:function(e){this.isColor=e}})},f=b,p=s(1001),v=(0,p.Z)(f,c,i,!1,null,"7d6770f2",null),C=v.exports,_="houseStore",d={name:"HouseList",components:{HouseListItem:C},data:function(){return{}},computed:(0,u.Z)({},(0,h.rn)(_,["houses"]))},g=d,H=(0,p.Z)(g,a,l,!1,null,null,null),w=H.exports,x=s(81367),Z={name:"HouseView",components:{HouseSearchBar:r.Z,HouseList:w,HouseDetail:x.Z}},k=Z,L=(0,p.Z)(k,o,n,!1,null,"15a80a82",null),S=L.exports}}]);
//# sourceMappingURL=474-legacy.c931cd86.js.map