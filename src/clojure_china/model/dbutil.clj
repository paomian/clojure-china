(ns clojure-china.model.dbutil
  (:require [clojure.java.jdbc :as j]
            [clojure-china.model.dbconn :as conn]))

(defmacro query
  [sql & fun]
  "
  对clojure.java.jdbc的qurey封装
  prarm:
    sql string 需要执行的sql和参数 exp (\"select * from table where id = ?\" \"10\")
    fun key 对结果进行处理的参数 exp :row-fn :cost
  "
  `(j/query (conn/connection)
            [~@sql]
            ~@fun))

(defmacro insert!
  [table & row-map]
  "
  对clojure.java.jdbc的insert!封装
  prarm:
    table key 表名 exp :table
    row-map map 插入行的map exp {:id \"1\" :name \"hello\"}
  "
  `(j/insert! (conn/connection) ~table
              ~@row-map))

(defmacro update!
  [table update-map condition]
  "
  对clojure.java.jdbc的update!封装
  prarm:
    table key 表名 exp :table
    update-map map 跟新的字段和值的map exp {:id 10}
    condition vec [\"string\" string or number] 更新字段的限定条件 exp [\"id = ?\" 20]
  "
  `(j/update! (conn/connection) ~table
              ~update-map
              ~condition))

(defmacro delete!
  [table condition]
  "
  对clojure.java.jdbc的delete!的封装
  prarm:
    table key 表名 exp :table
    condition vec [string string or number] 删除行的限定条件 exp [\"id = ?\" 1]
  "
  `(j/delete! (conn/connection) ~table
              ~condition))