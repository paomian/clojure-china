(ns clojure-china.model.post.post
  "对于post的一些列操作"
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]
             [db :as kdb]]
            [clojure-china.model.dbconn :refer [db-spec]]
            [clojure-china.model.entitys :refer :all])
  (:import [java.sql Timestamp]
           [java.util Date]))

(def page-size 20)
(def table "CC_POST")

(defn id
  [^Integer id]
  (first
    (k/select posts
              (k/where {:id id}))))

(defn paging-byauthorid
  [^Integer id ^Integer pages]
  (k/select posts
            (k/with users)
            (k/fields :posts.id :posts.title)
            (k/where {:users.id id})
            (k/limit page-size)
            (k/offset (* pages 20))))

(defn paging-byauthorname
  [^String username ^Integer pages]
  (k/select posts
            (k/with users)
            (k/fields :posts.id :posts.title)
            (k/where {:users.username username})
            (k/limit page-size)
            (k/offset (* pages 20))))

(defn paging-bynodeid
  [^Integer id ^Integer pages]
  (k/select posts
            (k/with nodes)
            (k/fields :posts.id :posts.title)
            (k/where {:nodes.id id})
            (k/limit page-size)
            (k/offset (* pages 20))))

(defn paging-bynodename
  [^String nodename ^Integer pages]
  (k/select posts
            (k/with nodes)
            (k/fields :posts.id :posts.title)
            (k/where {:nodes.node nodename})
            (k/limit page-size)
            (k/offset (* pages 20))))

(defn ttt []
  (k/select posts
            (k/with users)
            (k/fields :posts.id :posts.title)))