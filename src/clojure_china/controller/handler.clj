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
            [clojure-china.controller.account.action :as aact]
            [clojure-china.controller.json :refer [result]]
            [clojure-china.model.post.post]))

(defmacro pri [request & body]
  `(do
     (println ~request)
     ~@body)
  )

(defroutes app-routes
           (context "/v1" _
                    (GET "/user/:username/post"
                         {{user :username pages :pages} :params :as request} (pri request (result (pact/post-byuser user pages))))

                    (GET "/user/:username"
                         {{user :username} :params :as request} (pri request (result (aact/user-query user))))
                    (GET "/:test" [test] (print test))
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