;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil.userdbutil
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-spec]]
            [clj-time.local :as l])
  (:import  [java.sql Timestamp]
            [java.util Date]))
;;插入数据
(defn insert-user!
  [username encrypted-password email is-admin?]
  (jdbc/insert!
    db-spec :cc_user
    {:username username
     :password encrypted-password
     :email email
     :is_admin is-admin?
     :register_time (Timestamp. (.getTime (Date.)))}))
