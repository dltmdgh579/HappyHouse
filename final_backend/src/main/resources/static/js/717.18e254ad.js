"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[717],{4717:function(e,t,r){r.r(t),r.d(t,{default:function(){return d}});var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("b-container",{staticClass:"bv-example-row mt-3",staticStyle:{"margin-left":"200px"}},[r("br"),r("br"),r("h3",{attrs:{align:"left"}},[e._v("로그인")]),r("br"),r("br"),r("b-row",[r("b-col",{attrs:{cols:"12"}},[r("b-card",{staticClass:"text-center mt-3",staticStyle:{"max-width":"40rem"},attrs:{align:"left"}},[r("b-form",{staticClass:"text-left"},[e.isLoginError?r("b-alert",{attrs:{show:"",variant:"danger"}},[e._v("아이디 또는 비밀번호를 확인하세요.")]):e._e(),r("b-form-group",{attrs:{label:"아이디:","label-for":"userid"}},[r("b-form-input",{attrs:{id:"userid",required:"",placeholder:"아이디 입력...."},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.confirm.apply(null,arguments)}},model:{value:e.user.userid,callback:function(t){e.$set(e.user,"userid",t)},expression:"user.userid"}})],1),r("b-form-group",{attrs:{label:"비밀번호:","label-for":"userpwd"}},[r("b-form-input",{attrs:{type:"password",id:"userpwd",required:"",placeholder:"비밀번호 입력...."},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.confirm.apply(null,arguments)}},model:{value:e.user.userpwd,callback:function(t){e.$set(e.user,"userpwd",t)},expression:"user.userpwd"}})],1),r("b-button",{staticClass:"m-1",attrs:{type:"button",variant:"primary"},on:{click:e.confirm}},[e._v("로그인")]),r("b-button",{staticClass:"m-1",attrs:{type:"button",variant:"success"},on:{click:e.movePage}},[e._v("회원가입")]),r("b-button",{staticClass:"m-1",attrs:{type:"button",variant:"danger"},on:{click:e.findPwd}},[e._v("비밀번호 찾기")])],1)],1)],1),r("b-col")],1)],1)},n=[],a=r(4665);const o="memberStore";var i={name:"MemberLogin",data(){return{user:{userid:null,userpwd:null}}},computed:{...(0,a.rn)(o,["isLogin","isLoginError"])},methods:{...(0,a.nv)(o,["userConfirm","getUserInfo"]),async confirm(){await this.userConfirm(this.user);let e=sessionStorage.getItem("access-token");this.isLogin&&(await this.getUserInfo(e),this.$router.push({name:"home"}))},movePage(){this.$router.push({name:"signup"})},findPwd(){this.$router.push({name:"findpwd"})}}},u=i,l=r(1001),c=(0,l.Z)(u,s,n,!1,null,null,null),d=c.exports}}]);
//# sourceMappingURL=717.18e254ad.js.map