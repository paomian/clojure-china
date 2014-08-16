(ns clojure-china.dbutil.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [korma.core :as k]
            [clojure-china.dbutil.dbconn :refer :all])
  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")
(declare users posts nodes replys)

(k/defentity users
               (k/table :cc_user)
               (k/database ccdb)
               (k/entity-fields :id :username))

(k/defentity posts
             (k/table :cc_post)
             (k/database ccdb)
             (k/entity-fields :id :title)
             (k/belongs-to users {:fk :author_id})
             (k/many-to-many nodes :posts_nodes {:lfk :post_id :rfk :node_id}))

(k/defentity nodes
             (k/table :cc_node)
             (k/database ccdb)
             (k/entity-fields :id :node)
             (k/many-to-many posts :posts_nodes {:lfk :post_id :rfk :node_id}))

(k/defentity replys
             (k/table :cc_node)
             (k/database ccdb)
             (k/entity-fields :id :reply :nods :status)
             (k/belongs-to posts {:fk :post_id}))

(defn paging-byauthorid [id pages]
(k/select users
          (k/where {:id id})
          (k/limit page-size)
          (k/offset (* pages 20))))

(defn paging-byauthorname [name pages]
  (k/select users
            (k/where {:username name})
            (k/limit page-size)
            (k/offset (* pages 20))))

(defn paging-byauthorid1 [id pages]
  (k/select ))