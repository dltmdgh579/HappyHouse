"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[96],{18096:function(t,e,n){n.r(e),n.d(e,{default:function(){return g}});var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("b-container",{staticClass:"bv-example-row mt-3"},[n("b-row",[n("b-col",[n("br"),n("br"),n("h3",{staticClass:"text-left"},[t._v("자유게시판")]),n("br"),n("br")])],1),n("b-row",[t.articles.length?n("b-col",[n("b-table-simple",{attrs:{hover:"",responsive:""}},[n("b-thead",{attrs:{"head-variant":"light"}},[n("b-tr",[n("b-th",[t._v("글번호")]),n("b-th",[t._v("제목")]),n("b-th",[t._v("조회수")]),n("b-th",[t._v("작성자")]),n("b-th",[t._v("작성일")])],1)],1),n("tbody",t._l(t.pageSlice,(function(e){return n("board-list-item",t._b({key:e.articleno},"board-list-item",e,!1))})),1)],1)],1):t._e()],1),n("b-row",{staticClass:"mb-2"},[n("b-col",{attrs:{sm:"4"}}),n("b-col",[n("b-input-group",[n("b-input-group-prepend",[n("select",{directives:[{name:"model",rawName:"v-model",value:t.type,expression:"type"}],on:{change:function(e){var n=Array.prototype.filter.call(e.target.options,(function(t){return t.selected})).map((function(t){var e="_value"in t?t._value:t.value;return e}));t.type=e.target.multiple?n:n[0]}}},[n("option",{attrs:{value:"subject"}},[t._v(" 제목")]),n("option",{attrs:{value:"id"}},[t._v("글쓴이")]),n("option",{attrs:{value:"content"}},[t._v(" 내용")])])]),n("b-form-input",{model:{value:t.item,callback:function(e){t.item=e},expression:"item"}}),n("b-input-group-append",[n("b-button",{attrs:{variant:"outline-info"},on:{click:t.search}},[t._v("검색")])],1)],1)],1),n("b-col",{staticClass:"text-right",attrs:{sm:"2",align:"left"}},[n("b-button",{attrs:{variant:"outline-primary"},on:{click:function(e){return t.moveWrite()}}},[t._v("글쓰기 ")])],1),n("b-col",{attrs:{sm:"1"}})],1),n("br"),n("b-row",[n("b-col",{attrs:{sm:"5"}}),0==t.pageNo?n("b-button",{attrs:{variant:"outline-dark",disabled:"disabled"}},[t._v(" 이전 페이지")]):n("b-button",{attrs:{variant:"outline-primary"},on:{click:function(e){return t.prevPage()}}},[t._v(" 이전 페이지 ")]),t._v("        "+t._s(this.pageNo+1)+"/"+t._s(this.pageCount)+"        "),t.pageNo==t.pageCount-1?n("b-button",{attrs:{variant:"outline-dark",disabled:"disabled"}},[t._v(" 다음 페이지 ")]):n("b-button",{attrs:{variant:"outline-primary"},on:{click:function(e){return t.nextPage()}}},[t._v("다음 페이지")])],1)],1)},a=[],o=(n(47042),n(12164)),i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("b-tr",[n("b-td",[t._v(t._s(t.articleno))]),n("b-th",{staticClass:"text-left"},[n("router-link",{attrs:{to:{name:"boardDetail",params:{articleno:t.articleno}}}},[t._v(t._s(t.subject))])],1),n("b-td",[t._v(t._s(t.hit))]),n("b-td",[t._v(t._s(t.userid))]),n("b-td",[t._v(t._s(t._f("dateFormat")(t.regtime)))])],1)},c=[],u=(n(9653),n(17133)),s=n.n(u),l={name:"BoardListItem",props:{articleno:Number,userid:String,subject:String,hit:Number,regtime:String},filters:{dateFormat:function(t){return s()(new Date(t)).format("YY.MM.DD")}}},b=l,p=n(1001),h=(0,p.Z)(b,i,c,!1,null,null,null),f=h.exports,v={name:"BoardList",components:{BoardListItem:f},data:function(){return{articles:[],type:"subject",item:"",pageNo:0}},computed:{pageCount:function(){var t=this.articles.length,e=Math.floor((t-1)/10)+1;return e},pageSlice:function(){var t=10*this.pageNo,e=t+10;return this.articles.slice(t,e)}},created:function(){var t=this,e={};(0,o.jC)(e,(function(e){t.articles=e.data}),(function(t){console.log(t)}))},methods:{moveWrite:function(){this.$router.push({name:"boardRegister"})},search:function(){var t=this;(0,o.vA)(this.type,this.item,(function(e){t.articles=e.data}),(function(t){console.log(t)}))},nextPage:function(){this.pageNo=Math.min(this.pageNo+1,this.pageCount-1)},prevPage:function(){this.pageNo=Math.max(0,this.pageNo-1)}}},d=v,m=(0,p.Z)(d,r,a,!1,null,null,null),g=m.exports},12164:function(t,e,n){n.d(e,{B0:function(){return p},HU:function(){return u},cn:function(){return s},fq:function(){return c},hY:function(){return i},jC:function(){return o},jX:function(){return l},vA:function(){return b}});n(38862);var r=n(86927),a=(0,r.b)();function o(t,e,n){a.get("/board",{params:t}).then(e).catch(n)}function i(t,e,n){a.post("/board",JSON.stringify(t)).then(e).catch(n)}function c(t,e,n){a.get("/board/".concat(t)).then(e).catch(n)}function u(t,e,n){a.get("/board/reply/".concat(t)).then(e).catch(n)}function s(t,e,n){a.put("/board/".concat(t.articleno),JSON.stringify(t)).then(e).catch(n)}function l(t,e,n){a.delete("/board/".concat(t)).then(e).catch(n)}function b(t,e,n,r){a.get("/board/search"+t+"/"+e).then(n).catch(r)}function p(t,e,n){a.post("/board/reply",t).then(e).catch(n)}}}]);
//# sourceMappingURL=96-legacy.d995662a.js.map