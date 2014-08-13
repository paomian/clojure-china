#Notice

- In order to protect the database password, we omitted a file called dbconn.clj
- It is look like this

```clojure
(ns clojure-china.dbconn)

(def db-name "your db name")
(def db-user "your db user")
(def db-host "your db host")
(def db-port "your db port")
(def db-spec
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (str "//" db-host ":" db-port "/" db-name)
   :user db-user
   :password "your db password"})
   
(defn pool
  "创建数据库连接池"
  [spec]
  (let [cpds (doto (ComboPooledDataSource.)
               (.setDriverClass (:classname spec))
               (.setJdbcUrl (str "jdbc:" (:subprotocol spec) ":" (:subname spec)))
               (.setUser (:user spec))
               (.setPassword (:password spec))
               ;; expire excess connections after 30 minutes of inactivity:
               (.setMaxIdleTimeExcessConnections (* 30 60))
               ;; expire connections after 3 hours of inactivity:
               (.setMaxIdleTime (* 3 60 60)))]
    {:datasource cpds}))

(def pooled-db (delay (pool db-spec)))

(defn db-connection
  []
  @pooled-db)
```

###Ation

- Befor you run the clojure-china,you must run the script dbinit.sql to init databases; 


