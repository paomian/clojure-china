(ns clojure-china.dbutil.post.post
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-spec]])
  (:import  [java.sql Timestamp]
           [java.util Date]))
(def page-size 20)
(def table "cc_post")

(defn post-create!
  [title content node author]
  (jdbc/insert! db-spec :cc_post
                {:title title
                 :content content
                 :node node
                 :author author
                 :create_time (Timestamp. (.getTime (Date.)))}))
(defn post-update!
  [id title content node author]
  (jdbc/insert! db-spec :cc_post
                {:title title
                 :content content
                 :node node
                 :author author}))
(defn check-paging
  []
  (jdbc/query db-spec
              ["select count(id) from cc_post where status in ('normal','other')"]))
(defn post-id-query
  [id]
  (jdbc/query db-spec
              ["select * from cc_post where id = ?" id])
  )
;;按作者查询并分页
(defn post-paging-byauthor
  [user & pages]
  (jdbc/query db-spec
              [(str "select id,title from cc_post where authour = ? order by create_time offset " (* (- (or pages 1) 1) page-size) " limit " page-size)] user))
;;按节点查询并分页
(defn post-paging-bynode
  [node & pages]
  (jdbc/query db-spec
              [(str "select id,title from cc_post where authour = ? order by create_time offset " (* (- (or pages 1) 1) page-size) " limit " page-size)] node))
;;按时间排序分页
(defn post-paging-bytime
  [& pages]
  (jdbc/query db-spec
              [(str "select id,title from cc_post order by create_time offset " (* (- (or pages 1) 1) page-size) " limit " page-size)]))

;;按人气排序分页
(defn post-paging-bypopularity
  [pages page-size]
  (jdbc/query db-spec
              [(str "select id,title from cc_post order by mark offset " (* (- pages 1) page-size) " limit " page-size)]))
;;计算帖子权重
