(ns clojure-china.dbutil.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.util.json :refer [map2json]]
            [clojure-china.dbutil.dbconn :refer [db-connection]]
            [clojure-china.util.json :refer [map2json]])

  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")

(defn- query
  [str & args]
  (do (println str args)
      (jdbc/query (db-connection)
                  (flatten [str args]))))
(defn- vps
  "处理分页页码"
  [pages]
    (println pages)
    (* (- pages 1) page-size))

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
  (query
    ["SELECT COUNT(ID) FROM CC_POST WHERE STATUS IN ('NORMAL','OTHER')"]))

(defn id-query
  "按id查询post"
  [id]
  (query
    ["SELECT * FROM CC_POST WHERE ID = ?" id])
  )

(defn querytest
  []
  (jdbc/query (db-connection)
              ["SELECT P.ID,P.TITLE FROM CC_POST AS P WHERE ID = ?" 1]))

(defn paging-byauthorid
  "按作者ID查询并分页"
  [user pages]
  (query
    (str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
          (vps pages) " LIMIT " page-size) user))

(defn paging-byauthorname
  "按作者用户名查询分页"
  [user pages]
  (query (str "SELECT P.ID,P.TITLE FROM CC_USER AS U,CC_POST AS P
                   WHERE U.ID = P.AUTHOR
               AND U.USERNAME=?
                   ORDER BY CREATE_TIME OFFSET " (vps pages) " LIMIT " page-size) user))

#_(defn paging-byauthor1
  "按作者查询并分页1"
  [user & pages]
  (query (str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
              (vps pages) " LIMIT " page-size) user))

(defn paging-bynode
  "按节点查询并分页"
  [node pages]
  (query
    [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOR = ? ORDER BY CREATE_TIME OFFSET "
          (vps pages) " LIMIT " page-size) node]))

(defn paging-bytime
  "按创建时间排序分页"
  [pages]
  (query
    [(str "SELECT ID,TITLE FROM CC_POST ORDER BY CREATE_TIME OFFSET "
          (vps pages) " LIMIT " page-size)]))


(defn paging-bypopularity
  "按人气排序分页"
  [pages page-size]
  (query
    [(str "SELECT ID,TITLE FROM CC_POST ORDER BY MARK OFFSET "
          (vps pages) " LIMIT " page-size)]))