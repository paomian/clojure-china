(ns clojure-china.model.account.account
  "对于用户的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]]                                  ;todo
            [clj-time.local :as l]
            [clojure-china.model.dbconn :refer [db-spec]]
            [clojure-china.model.dbconn :refer [connection]]
            [clojure-china.model.entitys :refer :all])
  (:import [java.sql Timestamp]
           [java.util Date]))


(defn id-query [id]
  "按照ID查询用户"
  (k/select users
            (k/where {:id id})))

(defn id-query [^Integer id]
  "ID查询用户"
  (jdbc/query )
  )

(defn name-query
  "按照用户名查询用户"
  [username]
  (k/select users
            (k/where {:username username})))

(defn check-name
  "检测用户名是否存在"
  [username]
  (empty? (k/select users
                    (k/fields :id)
                    (k/where {:username username}))))

(defn check-id
  "检测id是否存在"
  [id]
  (empty? (k/select users
                    (k/fields :id)
                    (k/where {:id id}))))

(defn create!
  "创建用户"
  [username encrypted-password email]
  (k/insert users
            (k/values {:username username
                       :password encrypted-password
                       :email    email})))

(defn user-update!
  "更新用户信息"
  [username encrypted-password email]
  (k/update users
            (k/set-fields {
                            :password encrypted-password
                            :email    email})
            (k/where {:username username})))

#_(defn user-update
  [])
;;更新用户最后登录时间
(defn update-lastlogintime
  "更新用户最后登录时间"
  [id]
  (k/update users
            (k/set-fields {:last_login_time (l/local-now)})
            (k/where {:id id})))