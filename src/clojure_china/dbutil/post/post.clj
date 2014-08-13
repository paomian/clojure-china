(ns clojure-china.dbutil.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-connection]])
  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")

(defn create!
  "创建post"
  [title content node author]
  (jdbc/insert! (db-connection) :cc_post
                {:title       title
                 :content     content
                 :node        node
                 :author      author
                 :create_time (Timestamp. (.getTime (Date.)))}))
(defn update!
  "跟新帖子"
  [id title content node author]
  (jdbc/insert! (db-connection) :cc_post
                {:title   title
                 :content content
                 :node    node
                 :author  author}))
(defn check-paging
  []
  (jdbc/query (db-connection)
              ["SELECT COUNT(ID) FROM CC_POST WHERE STATUS IN ('NORMAL','OTHER')"]))

(defn id-query
  "按id查询post"
  [id]
  (jdbc/query (db-connection)
              ["SELECT * FROM CC_POST WHERE ID = ?" id])
  )

(defn paging-byauthor
  "按作者查询并分页"
  [user & pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOUR = ? ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)] user))

(defn post-paging-bynode
  "按节点查询并分页"
  [node & pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOUR = ? ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)] node))

(defn post-paging-bytime
  "按创建时间排序分页"
  [& pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)]))


(defn post-paging-bypopularity
  "按人气排序分页"
  [pages page-size]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY MARK OFFSET "
                    (* (- pages 1) page-size) " LIMIT " page-size)]))
;;计算帖子权重
