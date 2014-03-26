;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil.dbutil
  (:use [clojure-china.dbutil.dbconn :only db-postgres])
  (:require [clojure.java.jdbc  :as jdbc]))
;;插入数据
(defn userinsert! [table db])
