(ns clojure-china.dbutil.account.account
  "对于用户的个人信息的一系列操作方法"
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-connection]]
            [clj-time.local :as l])
  (:import [java.sql Timestamp]
           [java.util Date]))

;;(defrecord User [])

(defn id-query [id]
  "按照ID查询用户"
  (jdbc/query
    (db-connection)
    ["SELECT * FROM CC_USER WHERE ID = ?" id]))

(defn name-query
  "按照用户名查询用户"
  [username]
  (jdbc/query
    (db-connection)
    ["SELECT * FROM CC_USER WHERE USERNAME = ?" username]))

(defn check-name
  "检测用户名是否存在"
  [username]
  (empty? (jdbc/query
            (db-connection)
            ["SELECT USERNAME FROM CC_USER WHERE USERNAME = ?" username])))

(defn check-id
  "检测id是否存在"
  [id]
  (empty? (jdbc/query
            (db-connection)
            ["SELECT ID FROM CC_USER WHERE ID = ?" id])))

(defn user-create!
  "新建用户"
  [username encrypted-password email is-admin?]
  (jdbc/insert!
    (db-connection) :cc_user
    {:username        username
     :password        encrypted-password
     :email           email
     :is_admin        is-admin?
     :last_login_time (Timestamp. (.getTime (Date.)))
     :register_time   (Timestamp. (.getTime (Date.)))}))

(defn user-update!
  "更新用户信息"
  [username encrypted-password email]
  (jdbc/update!
    (db-connection) :cc_user
    {:password encrypted-password
     :email    email}
    ["USERNAME = ?" username]))

#_(defn user-update
  [])
;;更新用户最后登录时间
(defn update-lastlogintime
  "更新用户最后登录时间"
  [userid]
  (jdbc/update! (db-connection) :cc_user
                {:last_login_time (Timestamp. (.getTime (Date.)))} ["ID=?" userid]))