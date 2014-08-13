(ns clojure-china.dbutil.post.post
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-spec]])
  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")

(defn post-create!
  [title content node author]
  (jdbc/insert! db-spec :cc_post
                {:title       title
                 :content     content
                 :node        node
                 :author      author
                 :create_time (Timestamp. (.getTime (Date.)))}))
(defn post-update!
  [id title content node author]
  (jdbc/insert! db-spec :cc_post
                {:title   title
                 :content content
                 :node    node
                 :author  author}))
(defn check-paging
  []
  (jdbc/query db-spec
              ["SELECT COUNT(ID) FROM CC_POST WHERE STATUS IN ('NORMAL','OTHER')"]))
(defn post-id-query
  [id]
  (jdbc/query db-spec
              ["SELECT * FROM CC_POST WHERE ID = ?" id])
  )
;;按作者查询并分页
(defn post-paging-byauthor
  [user & pages]
  (jdbc/query db-spec
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOUR = ? ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)] user))
;;按节点查询并分页
(defn post-paging-bynode
  [node & pages]
  (jdbc/query db-spec
              [(str "SELECT ID,TITLE FROM CC_POST WHERE AUTHOUR = ? ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)] node))
;;按时间排序分页
(defn post-paging-bytime
  [& pages]
  (jdbc/query db-spec
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY CREATE_TIME OFFSET "
                    (* (- (or pages 1) 1) page-size) " LIMIT " page-size)]))

;;按人气排序分页
(defn post-paging-bypopularity
  [pages page-size]
  (jdbc/query db-spec
              [(str "SELECT ID,TITLE FROM CC_POST ORDER BY MARK OFFSET "
                    (* (- pages 1) page-size) " LIMIT " page-size)]))
;;计算帖子权重
