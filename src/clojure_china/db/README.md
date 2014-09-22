#Notice

- In order to protect the database password, we omitted a file called dbconn.clj
- It is look like this

```clojure
(ns clojure-china.db.dbconn)

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
- In order to protext the cache password, we omitted a file called redis.clj
- It is loo like this

```clojure
(ns clojure-china.db.redis)


(def session-conn {:pool {} :spec {:host       "127.0.0.1" :port 6379
                                   :password   "clojure-china"
                                   :timeout-ms 6000
                                   :db         0
                                   }})
```

by the way, don't forget use `redis-server` command to start up the redis server

###Ation

- Befor you run the clojure-china,you must run some command like [this](https://github.com/paomian/clojure-china#before-runing)


