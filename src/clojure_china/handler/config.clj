(ns clojure-china.handler.config)

;; Get the application env keyword, default is :dev
(defn env []
  (let [env (System/getenv "CC_ENV")]
  (if env (keyword env) :dev)))

(defn database-config []
  ((env) {
    :dev  {:name "cc_dev", :user "vagrant", :password "root", :host "127.0.0.1", :port "5432"},
    :test {:name "cc_test", :user "vagrant", :password "root", :host "127.0.0.1", :port "5432"}, 
    :prod {:name "cc_prod", :user "vagrant", :password "root", :host "127.0.0.1", :port "5432"}}))
