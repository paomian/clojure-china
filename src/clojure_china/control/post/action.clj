(ns clojure-china.control.post.action
  (:require [noir.validation :refer [valid-number?]]
            [clojure-china.util.json :refer [map2json]]
            [clojure-china.dbutil.post.post :refer :all]))


(defn- status
  [s]
  (assoc {} :status s))

(defn- message
  [m]
  (assoc {} :message m))

(defn- posts
  [p]
  (assoc {} :post p))

(defn- swich
  [user pages func func1]
  (if (valid-number? user)
    (func (Long/valueOf user) pages)
    (func1 user pages)))

(str "{\"status\" : \"500\" \"message\" : \"no valid query args\"}")


(defn postsbyuser
  [user pages]
  (swich user pages paging-byauthorid paging-byauthorname))

(defn- api
  [user & pages]
  (do (println user (type user))
      (-> {}
          (status 200)
          (message "test")
          (posts (paging-byauthorid user (first pages)))
          (map2json))))