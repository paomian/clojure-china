(ns clojure-china.control.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [compojure.core :refer :all]
            [clojure-china.pages.index :refer [index]]))

(defroutes app-routes
           (GET "/" [] (index))
           (GET "/register" [] (user-register))
           (POST "/register" [username password rpassword email] (register username password rpassword email))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-session)))
