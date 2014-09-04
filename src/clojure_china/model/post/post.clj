(ns clojure-china.model.post.post
  "对于post的一些列操作"
  (:import (java.sql Timestamp)
           (java.util Date))
  (:require [clojure.java.jdbc :as jdbc]
            [clj-time.local :as l]
            [clojure-china.model.dbutil :as db]))

(def page-size 20)
(def table "CC_POST")

(defn id
  "
  按照id搜索post
  param:
    id post的id
  "
  [^Integer id]
  (first (db/query
           ["SELECT  FROM POSTS WHERE ID = ?" id])))

(defn by-user-id
  "
  按照所给user的id来查找post
  param:
    userid: 用户的id
    pages : 所需要的页数
  "
  [^Integer userid ^Integer pages]
  (db/query
    ["SELECT  FROM POSTS LEFT JOIN USERS ON
    USERS.ID = POSTS.USER_ID WHERE USERS.ID = ? LIMIT ?, ?"
     userid (* pages page-size) page-size]))

(defn by-user-name
  "
  按照所给user的name来查找post
  param:
    username: 用户的username
    pages   : 所需要的页数
  "
  [^Integer username ^Integer pages]
  (db/query
    ["SELECT  FROM POST LEFT JOIN USERS ON
    USERS.ID = POSTS.USER_ID WHERE USERS.USERNAME = ? LIMIT ?, ?"
     username (* pages page-size) pages]))

(defn by-node-id
  "
  按照所给node的id来朝着post
  param:
    id    : node的id
    pages : 所需要的页数
  "
  [^Integer id ^Integer pages]
  (db/query
    ["SELECT  FROM POST LEFT JOIN NODES ON
    NODE.ID = POST.NODE_ID WHERE NODES.ID = ? LIMIT ?, ?"
     id (* pages page-size) page-size]))

(defn by-node-name
  "
  按照所给node的name来朝着post
  param:
    name  : node的name
    pages : 所需要的页数
  "
  [^Integer name ^Integer pages]
  (db/query
    ["SELECT  FROM POST LEFT JOIN NODES ON
    NODE.ID = POST.NODE_ID WHERE NODES.NODENAME = ? LIMIT ?, ?"
     name (* pages page-size) page-size]))

(defn create!
  "
  创建post
  param:
    title   : post的标题
    content : post的内容
    userid  : post的所属用户
    nodeid  : post的所属节点
  "
  [^String title ^String content
   ^Integer userid ^Integer nodeid]
  (db/insert! :posts
              {
                :title      title
                :content    content
                :user_id    userid
                :node_id    nodeid
                :created_on (Timestamp. (.getTime (Date.)))
                :updated_on (Timestamp. (.getTime (Date.)))
                }))

(defn update!
  "
  修改post
  param:
    title   : post的标题
    content : post的内容
    id      : post的id
  "
  [^String title ^String content
   ^Integer id]
  (db/update! :posts
              {
                :title   title
                :content content}
              ["id=?" id]))

(defn delete!
  "
  删除post
    prarm:
    id int post的id
  "
  [^Integer id]
  (db/update! :posts
              {
                :status "delete"}
              ["id = ?" id]))