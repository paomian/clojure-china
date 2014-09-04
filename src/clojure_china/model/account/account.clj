(ns clojure-china.model.account.account
  "对于用户的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clj-time.local :as l]
            [clojure-china.model.dbutil :as db]
            [clojure-china.model.entitys :refer :all]))



(defn id
  "按照ID查询用户"
  [^Integer id]
  (first (db/query ["SELECT ID,NAME,EMALI FROM USERS WHERE ID = ?" id])))

(defn name
  "按照用户名查询用户"
  [name]
  (first (db/query ["SELECT ID,NAME,EMAIL FROM USERS WHERE NAME = ?" name])))

(defn check-name
  "检测用户是否存在"
  [^String username]
  (empty? (db/query
            ["SELECT ID FROM USERS WHERE USERNAME = ?" username])))

(defn check-id
  "检测id是否存在"
  [id]
  (empty? (db/query
            ["SELECT ID FROM USERS WHERE ID = ?" id])))

(defn create!
  "创建用户"
  [username encrypted-password email]
  (db/insert! :users
              {
                :username username
               :password encrypted-password
               :email    email}))

(defn update!
  "更新用户信息"
  [username encrypted-password email]
  (db/update! :users
              {:password encrypted-password}
              ["username = ?" username]))


(defn update-lastlogintime
  "更新用户最后登录时间"
  [id]
  (db/update! :users
              {:last_login_time (l/local-now)}
              ["id = ?" id]))