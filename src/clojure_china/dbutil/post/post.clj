(ns clojure-china.dbutil.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.util.json :refer [map2json]]
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
  "跟新post"
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

(defn vps
  "处理分页页码"
  [pags]
  (* (- (or pags 1) 1) page-size))

(defn query
  [str & args]
  (do (println str args)
  (jdbc/query (db-connection)
              [str (first args)])))

(defn paging-byauthor
  "按作者查询并分页"
  [user & pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
                    (vps pages) " LIMIT " page-size) user]))

(defn paging-byauthor1
  "按作者查询并分页1"
  [user & pages]
  (query (str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
               (vps (first pages)) " LIMIT " page-size) user))

(defn paging-bynode
  "按节点查询并分页"
  [node & pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
                    (vps pages) " LIMIT " page-size) node]))

(defn paging-bytime
  "按创建时间排序分页"
  [& pages]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY CREATE_TIME OFFSET "
                    (vps pages) " LIMIT " page-size)]))


(defn paging-bypopularity
  "按人气排序分页"
  [pages page-size]
  (jdbc/query (db-connection)
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY MARK OFFSET "
                    (vps pages) " LIMIT " page-size)]))

(defn api
  [user & pages]
  (-> {}
      (assoc :status "200")
      (assoc :message "test")
      (assoc :posts (paging-byauthor1 user (first pages)))))