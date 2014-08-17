#Notice

- In order to protect the database password, we omitted a file called dbconn.clj
- It is look like this

```clojure
(ns clojure-china.dbutil.dbconn)

(def db-name "your db name")
(def db-user "your db user")
(def db-host "your db host")
(def db-port "your db port")

(def db-spec
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     (str "//" db-host ":" db-port "/" db-name)
   :user        db-user
   :password    "your db passowrd"})
```

###Ation

- Befor you run the clojure-china,you must run the script dbinit.sql to init databases; 


