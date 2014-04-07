;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil.account.dbutil
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-spec]]
            [clj-time.local :as l])
  (:import  [java.sql Timestamp]
            [java.util Date]))

;;(defrecord User [])
;;按id查询用户
(defn user-id-query [id]
  (jdbc/query db-spec 
              ["select * from cc_user where id = ?" id]))
;;按用户名查询用户
(defn user-name-query
  [username]
  (jdbc/query db-spec 
              ["select * from cc_user where username = ?" username]))
(defn check-username
  [username]
  (empty? (jdbc/query db-spec
                  ["select username from cc_user where username = ?" username])))
;;新建用户
(defn user-create!
  [username encrypted-password email is-admin?]
  (jdbc/insert!
    db-spec :cc_user
    {:username username
     :password encrypted-password
     :email email
     :is_admin is-admin?
     :last_login_time (Timestamp. (.getTime (Date.)))
     :register_time (Timestamp. (.getTime (Date.)))}))

#_(defn user-update
  [])
;;更新用户最后登录时间
(defn user-update-lastlogintime
  [userid]
  (jdbc/update! db-spec :cc_user {:last_login_time (Timestamp. (.getTime (Date.)))} ["id=?" userid]))
