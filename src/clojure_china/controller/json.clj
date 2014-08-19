(ns clojure-china.controller.json
  "json的相关事宜"
  (:require [cheshire.core :refer :all]
            [clj-time.local :as l]))

(defn map2json
  "map 转化为 json"
  [map]
  (generate-string map))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn result [s]
  (assoc
      {:status  200
       :headers {"Content-Type" "application/json; charset=utf-8"}}
    :body s))
