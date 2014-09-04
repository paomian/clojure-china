;;用户登录验证，和相关处理
(ns clojure-china.controller.account.action
  (:require [noir.session :as session]
            [noir.cookies :as cookies]
            [ring.util.response :as rr]
            [noir.validation :refer [valid-number?]]
            [clojure-china.controller.json :as mj]
            [clojure-china.model.account.account :as adb])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))

(defn encryptor
  "密码的加密"
  [pwd]
  (.encryptPassword (StrongPasswordEncryptor.) pwd))

(defn- id
  [^Integer user]
  (do (println user (type user))
      (if (adb/check-id user)
        (-> {}
            (mj/status 200)
            (mj/message "no value")
            (mj/map2json))
        (-> {}
            (mj/status 200)
            (mj/message "OK")
            (mj/users (adb/id user))
            (mj/map2json)))))

(defn- sname
  [^String user]
  (do (println user (type user))
      (if (adb/check-name user)
        (-> {}
            (mj/status 200)
            (mj/message "no value")
            (mj/map2json))
        (-> {}
            (mj/status 200)
            (mj/message "test")
            (mj/users (adb/name user))
            (mj/map2json)))))

(defn user-query
  [user]
  (println user)
  (if (valid-number? user)
    (id (Long/valueOf user))
    (sname user)))



;;用户登录
(defn user-login [user pwd]
  (let [userinfo (first (adb/name user))]
    (if
        (.checkPassword (StrongPasswordEncryptor.) pwd (:password userinfo))
      (do
        (adb/update-lastlogintime (:id userinfo))
        (println (:id userinfo))
        (session/put! :username (:username userinfo))
        (str "恭喜您，登录成功！"))
      (do
        (str "登录失败，用户名或密码不正确！")))))                             ;;在此加入flash机制

;;用户注册
(defn user-register
  [^String username ^String password ^String r-password ^String email]
  (if
      (= password r-password)
    (if (adb/check-name username)
      (do
        (println (str username password r-password email))
        (adb/create! username (encryptor password) email)
        (mj/map2json {:code 200 :status "ok" :message "register success"}))
      (mj/map2json {:code 200 :status "error" :message "the name is existd"}))
    (mj/map2json {:code 200 :status "error" :message "other error"})))

(defn user-logout []
  (do
    (session/clear!)
    (str "退出成功！")))

;;测试
(defn test-user-login [user pwd]
  (let [userinfo (first (adb/name user))]
    (if
        (.checkPassword (StrongPasswordEncryptor.) pwd (:password userinfo))
      (do
        (adb/update-lastlogintime (:id userinfo))
        (println (:id userinfo))
        (session/put! :username (:username userinfo))
        (cookies/put! :username (:username userinfo))
        (rr/redirect "/"))
      (do
        (str "登录失败，用户名或密码不正确！")))))

;;用户注册
(defn test-user-register
  [^String username ^String password ^String r-password ^String email]
  (if
      (= password r-password)
    (if (adb/check-name username)
      (do
        (println (str username password r-password email))
        (adb/create! username (encryptor password) email)
        (mj/map2json {:code 200 :status "ok" :message "register success"}))
      (mj/map2json {:code 200 :status "error" :message "the name is existd"}))
    (mj/map2json {:code 200 :status "error" :message "other error"})))
