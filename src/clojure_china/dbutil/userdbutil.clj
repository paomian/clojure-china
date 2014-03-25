;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil
  (:require   [clojure.java.jdbc  :as jdbc]))
;;连接数据库
(def pgsql-db {:subprotool "postgre"
               :subname "/127.0.0.1:3306/clojure-china"
               :user "clojure-china"
               :password "clojure-china"})
;;插入数据
(defn userinsert! [table ])
