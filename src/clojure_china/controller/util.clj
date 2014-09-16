(ns clojure-china.controller.util
  "json的相关事宜"
  (:require [noir.validation :refer [valid-number?]]
            [cheshire.core :refer :all]
            [clj-time.local :as l]))

(defn map2json
  "map 转化为 json"
  [map]
  (generate-string map))

(defn status
  [m ^Integer s]
  (assoc m :status s))

(defn message
  [m ^String mss]
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

(defn valid-n
  [pages]
  (if (number? pages)
    pages
    (if (valid-number? pages)
      (Long/valueOf pages))))

