(defproject clojure-china "0.1"
  :description "This is the source code of Clojure China website."
  :url "http://clojure-china.org"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [org.clojure/java.jdbc "0.3.5"] ;jdbc
                 [org.postgresql/postgresql "9.3-1100-jdbc41"] ;pgsql client
                 [com.taoensso/carmine "2.6.2"] ;redis client
                 [lib-noir "0.8.4"] ;A set of utilities and helpers for building ring apps.
                 [org.jasypt/jasypt "1.7"] ;JAVA加密包
                 [hiccup "1.0.5"]
                 [ring/ring-core "1.3.0"] ;ring-core - essential functions for handling parameters, cookies and more
                 [hickory "0.5.3"]
                 [com.mchange/c3p0 "0.9.5-pre8"]            ;连接池
                 [clj-time "0.8.0"]
                 [cheshire "5.3.1"]]                        ;json
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler clojure-china.control.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
