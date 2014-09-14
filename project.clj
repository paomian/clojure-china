(defproject clojure-china "0.2"
            :description "This is the source code of Clojure China website."
            :url "https://github.com/paomian/clojure-china"
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [compojure "1.1.8"]
                           [org.clojure/java.jdbc "0.3.5"]  ;jdbc
                           [korma "0.3.3"]                  ;orm
                           [org.postgresql/postgresql "9.3-1100-jdbc41"] ;pgsql client
                           [com.mchange/c3p0 "0.9.5-pre8"]
                           [com.taoensso/carmine "2.7.0"]   ;redis client
                           [lib-noir "0.8.4"]               ;A set of utilities and helpers for building ring apps.
                           [org.jasypt/jasypt "1.7"]        ;JAVA加密包
                           [ring/ring-core "1.3.0"]         ;ring-core - essential functions for handling parameters, cookies and more
                           [clj-time "0.8.0"]
                           [lobos "1.0.0-beta3"]
                           [cheshire "5.3.1"]               ;json
                           [log4j "1.2.15" :exclusions [javax.mail/mail
                                                        javax.jms/jms
                                                        com.sun.jdmk/jmxtools
                                                        com.sun.jmx/jmxri]]]
            :plugins [[lein-ring "0.8.10"]]
            :ring {:handler clojure-china.controller.handler/app
                   :init    lobos.migrations/dbinit}
            :profiles
            {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]
                                  [midje "1.5.1"]]}})
