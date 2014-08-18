(ns lobos.config
  (:require [clojure-china.model.dbconn :refer [db-spec]])
  (:use lobos.connectivity))

(open-global db-spec)