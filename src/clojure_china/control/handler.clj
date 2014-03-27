(ns clojure-china.control.handler
  (:use [compojure.core]
        [clojure-china.pages.index   :only [index]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]))

(defroutes app-routes
           (GET "/" [] (index))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-session)))
