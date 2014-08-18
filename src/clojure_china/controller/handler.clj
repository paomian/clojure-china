(ns clojure-china.controller.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [noir.validation :refer [valid-number?]]
            [compojure.core :refer :all]
            [taoensso.carmine.ring :refer :all]

            [clojure-china.model.redis :refer [session-conn]]
            [clojure-china.controller.account.action :refer :all]
            [clojure-china.controller.post.action :as pact]
            [clojure-china.model.post.post]))



(defroutes app-routes
           (context "/v1"
                    (GET "/post/:username"
                         {{user :user pages :pages} :params :as request} (do
                                                                           (println request)
                                                                           (pact/userpostapi user pages)))
                    (POST "/login" [user pwd] (user-login user pwd))
                    (GET "/logout" [] (user-logout))
                    (POST "/register" [username password r-password email] (user-register username password r-password email))
                    (route/resources "/")
                    (route/not-found "Not Found")))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-flash)
    (session/wrap-noir-session {:store (carmine-store session-conn {:key-prefix "session" :expiration-secs (* 60 60 24 30)})})))