(ns clojure-china.model.dbutil
  (:require [clojure.java.jdbc :as j]
            [clojure-china.model.dbconn :as conn]))

(defmacro query
  "
  对clojure.java.jdbc的qurey封装
  prarm:
    sql string 需要执行的sql和参数 exp [\"select * from table where id = ?\" \"10\"]
    fun key 对结果进行处理的参数 exp :row-fn :cost
  "
  [sql & fun]
  `(j/query (conn/connection)
            [~@sql]
            ~@fun))

(defn fquery
  "query宏的函数写法"
  [sql & fun]
  (apply
    (j/query (conn/connection)
             sql) fun))

(defmacro insert!
  "
  对clojure.java.jdbc的insert!封装
  prarm:
    table key 表名 exp :table
    row-map map 插入行的map exp {:id \"1\" :name \"hello\"}
  "
  [table & row-map]
  `(j/insert! (conn/connection) ~table
              ~@row-map))

(defn finsert!
  "insert!宏的函数写法"
  [table & row-map]
  (apply (j/insert! (conn/connection) table) row-map))

(defmacro update!
  "
  对clojure.java.jdbc的update!封装
  prarm:
    table key 表名 exp :table
    update-map map 跟新的字段和值的map exp {:id 10}
    condition vec [\"string\" string or number] 更新字段的限定条件 exp [\"id = ?\" 20]
  "
  [table update-map condition]
  `(j/update! (conn/connection) ~table
              ~update-map
              ~condition))

(defn fupdate!
  "update!宏的函数写法"
  [table update-map condition]
  (j/update! (conn/connection) table
             update-map
             condition))

(defmacro delete!
  "
  对clojure.java.jdbc的delete!的封装
  prarm:
    table key 表名 exp :table
    condition vec [string string or number] 删除行的限定条件 exp [\"id = ?\" 1]
  "
  [table condition]
  `(j/delete! (conn/connection) ~table
              ~condition))

(defn fdelete!
  "delete!宏的函数写法"
  [table condition]
  (j/delete! (conn/connection) table
             condition))