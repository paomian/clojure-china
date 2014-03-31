(ns clojure-china.control.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [compojure.core :refer :all]
            [clojure-china.pages.index :refer [index]]
            [clojure-china.pages.user-action :refer [register-page]]
            [clojure-china.control.user-action :refer :all]))

(defroutes app-routes
           (GET "/" [] (index))
           (GET "/register" [] (register-page))
           (POST "/register" [username password rpassword email] (user-register username password rpassword email))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-session)))
