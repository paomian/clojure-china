(ns clojure-china.model.redis)


(def session-conn {:pool {} :spec {:host       "127.0.0.1" :port 6379
                                   :password   "clojure_china"
                                   :timeout-ms 6000
                                   :db         0
                                   }})