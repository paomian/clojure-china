;;用户登录验证，和相关处理
(ns clojure-china.control.user-action
  (:require [clojure-china.dbutil.userdbutil    :as dbutil]
            [noir.session                       :as session]
            [ring.util.response                 :as response]
            [clojure-china.pages.htmlutil       :as html])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))
(defn encryptor [pwd]
  (.encryptPassword (StrongPasswordEncryptor.) pwd))
;;用户登录
(defn user-longin [user pwd]
  (let [userinfo (first (dbutil/user-name-query user))]
    (if
      (.checkPassword (StrongPasswordEncryptor.) pwd  (:password userinfo))
      (do
        (dbutil/user-update-lastlogintime (:id userinfo))
        (session/put! :user (:id userinfo))
        (response/redirect "/"))
      (do
        (html/flash-err "/"  "登录失败，用户名或密码不正确！"))))) ;;在此加入flash机制

;;用户注册
(defn user-register
  [username password r-password email]
  (if
    (= password r-password)
    (do
      (println (str username password r-password email))
      (dbutil/user-insert! username (encryptor password) email false)
      (html/flash-suc "/register"  "注册成功！点击" [:a.alert-link {:href "#"} "登录"]))
    (html/flash-err "/register" "注册失败！")))

(defn user-logout []
  (do
    (session/clear!)
    (html/flash-msg "/" "退出成功！")))
