#Notice

- In order to protect the database password, we omitted a file called dbconn.clj
- It is look like this

```clojure
(ns clojure-china.dbconn)

(def db-name "your db name")
(def db-user "your db user")
(def db-host "your db host")
(def db-port "your db port")
(def db-conn
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (str "//" db-host ":" db-port "/" db-name)
   :user db-user
   :password "your db password"})
```

###Ation

- Befor you run the clojure-china,you must run the script cc_user.sql to init databases; 


