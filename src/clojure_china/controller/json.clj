(ns clojure-china.controller.json
  "json的相关事宜"
  (:require [noir.validation :refer [valid-number?]]
            [cheshire.core :refer :all]
            [clj-time.local :as l]))

(defn map2json
  "map 转化为 json"
  [map]
  (generate-string map))

(defn status
  [m s]
  (assoc m :status s))

(defn message
  [m mss]
  (assoc m :message mss))

(defn posts
  [m p]
  (assoc m :post p))

(defn users
  [m p]
  (assoc m :user p))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn valid-pages
  [pages]
  (if pages
    (if (valid-number? pages)
      (Long/valueOf pages)
      1)
    1))

(defn result [s]
  (assoc
      {:status  200
       :headers {"Content-Type" "application/json; charset=utf-8"}}
    :body s))
