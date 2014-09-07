(ns clojure-china.model.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clj-time.local :as l]
            [clojure-china.model.dbutil :as db])
  (:import (java.sql Timestamp)
           (java.util Date)))

(def page-size 20)
(def table "CC_POST")

(defn id
  "
  按照id搜索post
  param:
    id post的id
  "
  [^Integer id]
  (first (try
           (db/query
             ["SELECT  FROM POSTS WHERE ID = ?" id])
           (catch Exception e
             (.printStackTrace e)))))

(defn by-user-id
  "
  按照所给user的id来查找post
  param:
    userid: 用户的id
    pages : 所需要的页数
  "
  [^Integer userid ^Integer pages]
  (try
    (db/query
      ["SELECT  FROM POSTS LEFT JOIN USERS ON
    USERS.ID = POSTS.USER_ID WHERE USERS.ID = ? LIMIT ?, ?"
       userid (* pages page-size) page-size])
    (catch Exception e
      (.printStackTrace e))))

(defn by-user-name
  "
  按照所给user的name来查找post
  param:
    username: 用户的username
    pages   : 所需要的页数
  "
  [^Integer username ^Integer pages]
  (try (db/query
         ["SELECT  FROM POST LEFT JOIN USERS ON
    USERS.ID = POSTS.USER_ID WHERE USERS.USERNAME = ? LIMIT ?, ?"
          username (* pages page-size) pages])
       (catch Exception e
         (.printStackTrace e))))

(defn by-node-id
  "
  按照所给node的id来查找post
  param:
    id    : node的id
    pages : 所需要的页数
  "
  [^Integer id ^Integer pages]
  (try
    (db/query
      ["SELECT  FROM POST LEFT JOIN NODES ON
    NODE.ID = POST.NODE_ID WHERE NODES.ID = ? LIMIT ?, ?"
       id (* pages page-size) page-size])
    (catch Exception e
      (.printStackTrace e))))

(defn by-node-name
  "
  按照所给node的name来查找post
  param:
    name  : node的name
    pages : 所需要的页数
  "
  [^Integer name ^Integer pages]
  (try
    (db/query
      ["SELECT  FROM POST LEFT JOIN NODES ON
    NODE.ID = POST.NODE_ID WHERE NODES.NODENAME = ? LIMIT ?, ?"
       name (* pages page-size) page-size])
    (catch Exception e
      (.printStackTrace e))))

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
  (try
    (db/insert! :posts
                {
                  :title      title
                  :content    content
                  :user_id    userid
                  :node_id    nodeid
                  :created_on (Timestamp. (.getTime (Date.)))
                  :updated_on (Timestamp. (.getTime (Date.)))
                  })
    (catch Exception e
      (.printStackTrace e))))

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
  (try
    (db/update! :posts
                {
                  :title   title
                  :content content}
                ["id=?" id])
    (catch Exception e
      (.printStackTrace e))))

(defn delete!
  "
  删除post
    prarm:
    id int post的id
  "
  [^Integer id]
  (try
    (db/update! :posts
                {
                  :status "delete"}
                ["id = ?" id])
    (catch Exception e
      (.printStackTrace e))))