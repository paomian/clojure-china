(ns lobos.config
  (:require [clojure-china.dbutil.dbconn :refer [db-spec]])
  (:use lobos.connectivity))

(open-global db-spec)