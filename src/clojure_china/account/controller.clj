;;用户登录验证，和相关处理
(ns clojure-china.account.controller
  (:require [noir.session :as session]
            [noir.cookies :as cookies]
            [ring.util.response :as rr]
            [noir.validation :refer [valid-number?]]
            [clojure-china.handler.util :as mj]
            [clojure-china.account.model :as adb])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))


(def okmsg {
             :logout           "注销成功"
             :login-success    "恭喜您，登录成功"
             :register-success "恭喜你，注册成功"
             })
(def errmsg {
              :no-value      "您请求的数据不存在"
              :pwd-or-un-err "对不起，请检查你输入的用户名或密码是否有误"
              :name-existd   "对不起，你输入的用户名已存在，请更换后在尝试提交"
              :other         "其他错误，请重试"
              })

(def code {
            :ok 200
            :created 201
            :accepted 202
            })

(def status {
              :ok    "ok"
              :error "error"
              })

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
          (mj/users (dissoc result-user
                            :password
                            :other
                            :alive
                            :updated_on
                            :last_login
                            :admin)))
      (-> {}
          (mj/status 200)
          (mj/message "no value")))))

(defn logout
  "
  用户注销
  "
  []
  (session/clear!)
  {:code 200 :status "ok" :message "退出成功！"})

(defn login
  "
  用户登录
  param:
    user : 用户名
    pwd : 用户明文密码
  "
  [username password]
  (if (session/get username)
    (let [user (adb/uname username)]
      (if
          (decryptor password (:password user))
        (do
          (println (:id user))
          (adb/update-lastlogintime (:id user))
          (session/put! :userid (:id user))
          {:code 201 :status "ok" :message "login success"})
        {:code 200 :status "error" :message "password or username error"}))
    {:code 200 :status "error" :message "user is logined"}))

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
      {:code 200 :status "ok" :message "register success"}
      {:code 200 :status "error" :message "other error"})
    {:code 200 :status "error" :message "the name is existd"}))

;;尝试改写注册
;todo
#_(defn- register
  [username password email]
  (let [m (if (adb/check-name username)
            (if (adb/create! username (encryptor password) email)
              (msg :register-success)
              (msg :other))
            (msg :name-existd))])
    (-> {}
        (fn [m] (if (adb/check-name username)
                    (if (adb/create! username (encryptor password) email)
                      (assoc m :status (status :ok)
                                 :message (msg :register-success))
                      (assoc m :status (status :error)
                                 :message (msg :other)))
                    (assoc m :status (status :error)
                               :message (msg :name-existd))))
        (fn [m] (assoc m :code (if (= (status :ok) (m :status))
                                 ())))))
