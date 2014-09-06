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
  "
  密码的加密
  param:
    pwd : 明文密码
  "
  [pwd]
  (.encryptPassword (StrongPasswordEncryptor.) pwd))

(defn decryptor
  "
  解密
  param:
    pwd : 明文密码
    mpwd: 加密密码
  "
  [pwd mpwd]
  (.checkPassword (StrongPasswordEncryptor.) pwd mpwd))

(defn get-user
  "
  查询用户
  param:
    user : 用户的id或者name
  "
  [^String user]
  (println user (type user))
  (let [result-user (adb/get-user user)]
    (if (not (empty? result-user))
      (-> {}
          (mj/status 200)
          (mj/message "test")
          (mj/users result-user)
          (mj/map2json))
      (-> {}
          (mj/status 200)
          (mj/message "no value")
          (mj/map2json)))))

(defn logout
  "
  用户注销
  "
  []
  (session/clear!)
  (mj/map2json {:code 200 :status "ok" :message "退出成功！"}))

(defn login
  "
  用户登录
  param:
    user : 用户名
    pwd : 用户明文密码
  "
  [username password]
  (let [user (adb/uname username)]
    (if
        (decryptor password (:password user))
      (do
        (println (:id user))
        (adb/update-lastlogintime (:id user))
        (session/put! :username (:username user))
        (mj/map2json {:code 201 :status "ok" :message "login success"}))
      (mj/map2json {:code 200 :status "error" :message "password or username error"}))))

;;用户注册
(defn register
  "
  用户注册
  param:
    username : 用户名
    password : 用户明文密码
    email : 用户email
  "
  [^String username ^String password ^String email]
  (if (adb/check-name username)
    (if (adb/create! username (encryptor password) email)
      (mj/map2json {:code 200 :status "ok" :message "register success"})
      (mj/map2json {:code 200 :status "error" :message "other error"}))
    (mj/map2json {:code 200 :status "error" :message "the name is existd"})))
