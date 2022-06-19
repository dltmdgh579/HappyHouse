import { apiInstance } from "./index.js";

const api = apiInstance();

function getNews(keyword, success, fail) {
  api.get(`/news/${keyword}`).then(success).catch(fail);
}

function getInterest(userid, success, fail) {
  api.get(`/news/interest/${userid}`).then(success).catch(fail);
}

function addInterest(userid, dongname, success) {
  api.get(`/news/interest/${userid}/${dongname}`).then(success);
}

function removeInterest(userid, dongname, success) {
  api.delete(`/news/interest/${userid}/${dongname}`).then(success);
}

function checkInterest(userid, dongname, success) {
  api.get(`/news/check/${userid}/${dongname}`).then(success);
}

export { getNews, getInterest, addInterest, removeInterest, checkInterest };
