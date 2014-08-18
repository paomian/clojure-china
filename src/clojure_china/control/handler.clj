(ns clojure-china.control.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [noir.validation :refer [valid-number?]]
            [compojure.core :refer :all]
            [taoensso.carmine.ring :refer :all]



            #_[clojure-china.pages.index :refer [index]]
            #_[clojure-china.pages.account.action :refer :all]
            [clojure-china.dbutil.redisutil :refer [session-conn]]
            [clojure-china.control.account.action :refer :all]
            [clojure-china.control.post.action :as pact]
            [clojure-china.dbutil.post.post]))



(defroutes app-routes
           (context "/v1"
                    (GET "/post/:username"
                         {{user :user pages :pages} :params :as request} (do
                                                                           (println request)
                                                                           (pact/userpostapi user pages)))
                    ;; (GET "/login" [] (login-page))
                    (POST "/login" [user pwd] (user-login user pwd))
                    (GET "/logout" [] (user-logout))
                    ;;(GET "/register" [] (register-page))
                    (POST "/register" [username password r-password email] (user-register username password r-password email))
                    (route/resources "/")
                    (route/not-found "Not Found")))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-flash)
    (session/wrap-noir-session {:store (carmine-store session-conn {:key-prefix "session" :expiration-secs (* 60 60 24 30)})})))