(ns clojure-china.account.model
  "对于用户的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [noir.validation :refer [valid-number?]]

            [clojure-china.db.dbutil :as db])
  (:import (java.sql Timestamp)
           (java.util Date)))



(defn id
  "
  按照ID查询用户
  param:
    id 用户的id
  "
  [id]
  (first (try
           (db/query
             [
               "SELECT
                 ID,USERNAME,EMAIL,ADMIN,ALIVE,LAST_LOGIN,
                 CREATED_ON,UPDATED_ON,REGISTER_ON,OTHER
               FROM USERS
                 WHERE ID = ?" id])
           (catch Exception e
             (.printStackTrace e)))))

(defn uname
  "
  按照用户名查询用户
  param:
    name 用户的name
  "
  [^String name]
  (first (try
           (db/query
             [
               "SELECT
                 ID,USERNAME,PASSWORD,EMAIL,ADMIN,ALIVE,LAST_LOGIN,
                 CREATED_ON,UPDATED_ON,REGISTER_ON,OTHER
               FROM USERS
                 WHERE USERNAME = ?" name])
           (catch Exception e
             (.printStackTrace e)))))

(defn get-user
  "
  封装了id 和 name 函数
  "
  [user]
  (if (valid-number? user)
    (id (Long/valueOf user))
    (uname user)))


(defn check-name
  "
  检测用户是否存在
  param:
    name 用户的name
  "
  [^String name]
  (empty? (try
            (db/query
              ["SELECT
              ID
             FROM USERS
              WHERE USERNAME = ?" name])
            (catch Exception e
              (.printStackTrace e)))))

(defn check-id
  "
  检测id是否存在
  param:
    id 用户的id
  "
  [id]
  (empty? (try
            (db/query
              ["SELECT ID FROM USERS WHERE ID = ?" id])
            (catch Exception e
              (.printStackTrace e)))))

(defn create!
  "
  创建用户
  param:
    name 用户申请使用的用户名
    encrypted-password 加密后的用户密码
    email 用户的email
  "
  [name encrypted-password email]
  (try
    (db/insert! :users
                {
                  :username name
                  :password encrypted-password
                  :email    email})
    (catch Exception e
      (.printStackTrace e))))

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
  (try
    (db/update! :users
                {:password encrypted-password}
                ["username = ?" username])
    (catch Exception e
      (.printStackTrace e))))


(defn update-lastlogintime
  "
  更新用户最后登录时间
  param
    id 需要修改的用户id
  "
  [id]
  (try
    (db/update! :users
                {:last_login (Timestamp. (.getTime (Date.)))}
                ["id = ?" id])
    (catch Exception e
      (.printStackTrace e))))