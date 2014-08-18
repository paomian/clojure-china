;;用户登录验证，和相关处理
(ns clojure-china.controller.account.action
  (:require [clojure-china.model.account.account :as accdb]
            [noir.session :as session]
            [ring.util.response :as response])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))

(defn encryptor
  "密码的加密"
  [pwd]
  (.encryptPassword (StrongPasswordEncryptor.) pwd))

;;用户登录
(defn user-login [user pwd]
  (let [userinfo (first (accdb/name-query user))]
    (if
        (.checkPassword (StrongPasswordEncryptor.) pwd (:password userinfo))
      (do
        (accdb/update-lastlogintime (:id userinfo))
        (println (:id userinfo))
        (session/put! :username (:username userinfo))
        ("恭喜您，登录成功！"))
      (do
        ("登录失败，用户名或密码不正确！")))))          ;;在此加入flash机制

;;用户注册
(defn user-register
  [username password r-password email]
  (if
      (= password r-password)
    (if (accdb/check-name username)
      (do
        (println (str username password r-password email))
        (accdb/create! username (encryptor password) email)
        ("注册成功"))
      ("注册失败，有相同用户名"))
    ("注册失败！，您的两次输入的密码不匹配。")))

(defn user-logout []
  (do
    (session/clear!)
    ("退出成功！")))
