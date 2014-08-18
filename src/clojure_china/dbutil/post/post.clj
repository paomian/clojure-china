(ns clojure-china.dbutil.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]
             [db :as kdb]]
            [clojure-china.dbutil.dbconn :refer [db-spec]]
            [clojure-china.dbutil.entitys :refer :all])
  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")



(defn paging-byauthorid [id pages]
  (k/select posts
            (k/with users
                    (k/where {:user.id id}))
            (k/limit page-size)
            (k/offset (* pages 20))))

#_(defn paging-byauthorname [name pages]
  (k/select users
            (k/where {:username name})
            (k/limit page-size)
            (k/offset (* pages 20))))

#_(defn paging-byauthorid1 [id pages]
  (k/select))