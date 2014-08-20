;;用户登录验证，和相关处理
(ns clojure-china.controller.account.action
  (:require [noir.session :as session]
            [noir.validation :refer [valid-number?]]
            [clojure-china.controller.json :refer :all]
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
            (status 200)
            (message "no value")
            (map2json))
        (-> {}
            (status 200)
            (message "OK")
            (users (adb/id-query user))
            (map2json)))))

(defn- sname
  [^String user]
  (do (println user (type user))
      (if (adb/check-name user)
        (-> {}
            (status 200)
            (message "no value")
            (map2json))
        (-> {}
            (status 200)
            (message "test")
            (users (adb/name-query user))
            (map2json)))))

(defn user-query
  [user]
  (println user)
  (if (valid-number? user)
    (id (Long/valueOf user))
    (sname user)))



;;用户登录
(defn user-login [user pwd]
  (let [userinfo (first (adb/name-query user))]
    (if
        (.checkPassword (StrongPasswordEncryptor.) pwd (:password userinfo))
      (do
        (adb/update-lastlogintime (:id userinfo))
        (println (:id userinfo))
        (session/put! :username (:username userinfo))
        ("恭喜您，登录成功！"))
      (do
        ("登录失败，用户名或密码不正确！")))))                             ;;在此加入flash机制

;;用户注册
(defn user-register
  [username password r-password email]
  (if
      (= password r-password)
    (if (adb/check-name username)
      (do
        (println (str username password r-password email))
        (adb/create! username (encryptor password) email)
        ("注册成功"))
      ("注册失败，有相同用户名"))
    ("注册失败！，您的两次输入的密码不匹配。")))

(defn user-logout []
  (do
    (session/clear!)
    ("退出成功！")))
