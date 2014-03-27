(defproject clojure-china "0.1.0-SNAPSHOT"
  :description "This is the source code of Clojure China website."
  :url "http://clojure-china.org"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [org.clojure/java.jdbc "0.3.3"] ;jdbc
                 [postgresql/postgresql "8.4-702.jdbc4"] ;pgsql client
                 [com.taoensso/carmine "2.4.6"] ;redis client
                 [lib-noir "0.8.1"] ;A set of utilities and helpers for building ring apps.
                 [org.jasypt/jasypt "1.7"] ;JAVA加密包
                 [hiccup "1.0.5"]
                 [ring/ring-core "1.2.2"] ;ring-core - essential functions for handling parameters, cookies and more
                 [hickory "0.5.3"]
                 ]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler clojure-china.control.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
