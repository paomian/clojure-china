(ns clojure-china.dbutil.account.account
  "对于用户的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]
             [db :as kdb]]
            [clojure-china.dbutil.dbconn :refer [db-spec]]
            [clojure-china.dbutil.entitys :refer :all])
  (:import [java.sql Timestamp]
           [java.util Date]))


(defn id-query [id]
  "按照ID查询用户"
  ((k/select users
             (k/where {:id id}))))

(defn name-query
  "按照用户名查询用户"
  [username]
  ((k/select users
             (k/where {:username username}))))

(defn check-name
  "检测用户名是否存在"
  [username]
  (empty? ((k/select users
                     (k/fields :id)
                     (k/where {:username username})))))

(defn check-id
  "检测id是否存在"
  [id]
  (empty? ((k/select users
                     (k/fields :id)
                     (k/where {:id id})))))

(defn create!
  [username encrypted-password email]
  (k/insert users
            (values {:username username
                     :password encrypted-password
                     :email    email})))
(update users
        (set-fields {:status "active"
                     :beta   false})
        (where {:visits [> 10]}))

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
            (k/set-fields {:last_login_time (k/now)})
            (k/where {:id id})))