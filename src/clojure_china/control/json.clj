(ns clojure-china.util.json
  "json的相关事宜"
  (:require [cheshire.core :refer :all]))

(defn map2json
  "map 转化为 json"
  [map]
  (generate-string map))