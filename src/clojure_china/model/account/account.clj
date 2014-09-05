(ns clojure-china.model.account.account
  "对于用户的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clj-time.local :as l]
            [clojure-china.model.dbutil :as db]
            [clojure-china.model.entitys :refer :all])
  (:import (java.sql Timestamp)
           (java.util Date)))



(defn id
  "
  按照ID查询用户
  param:
    id 用户的id
  "
  [^Integer id]
  (first (db/query ["SELECT ID,NAME,EMALI FROM USERS WHERE ID = ?" id])))

(defn name
  "
  按照用户名查询用户
  param:
    name 用户的name
  "
  [name]
  (first (db/query ["SELECT ID,NAME,EMAIL FROM USERS WHERE USERNAME = ?" name])))

(defn check-name
  "
  检测用户是否存在
  param:
    name 用户的name
  "
  [^String name]
  (empty? (db/query
            ["SELECT ID FROM USERS WHERE USERNAME = ?" username])))

(defn check-id
  "
  检测id是否存在
  param:
    id 用户的id
  "
  [id]
  (empty? (db/query
            ["SELECT ID FROM USERS WHERE ID = ?" id])))

(defn create!
  "
  创建用户
  param:
    name 用户申请使用的用户名
    encrypted-password 加密后的用户密码
    email 用户的email
  "
  [name encrypted-password email]
  (db/insert! :users
              {
                :username name
               :password encrypted-password
               :email    email}))

(defn update!
  "
  更新用户信息
  param:
    name 用户修改后的用户名
    encrypted-password 用户修改后的加密后的用户密码
    email 用户修改后的email

  "
  ;todo email的修改机制
  [username encrypted-password email]
  (db/update! :users
              {:password encrypted-password}
              ["username = ?" username]))


(defn update-lastlogintime
  "
  更新用户最后登录时间
  param
    id 需要修改的用户id
  "
  [id]
  (db/update! :users
              {:last_login_time (Timestamp. (.getTime (Date.)))}
              ["id = ?" id]))