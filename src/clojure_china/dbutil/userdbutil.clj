;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil.dbutil
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-conn]]))
;;插入数据
(defn userinsert! [table db])
