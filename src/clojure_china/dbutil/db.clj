(ns clojure-china.dbutil.db
  (:require [noir.validation :refer [valid-number?]]
            [clojure-china.dbutil.dbconn :refer [db-connection]]
            [clojure-china.control.json :refer [mpa2json]])

  (:import [java.sql Timestamp]
           [java.util Date]))

(defn vpages
  [pages]
  (if pages
    (if (valid-number? pages)
      (Long/valueOf pages)
      1)
    1))