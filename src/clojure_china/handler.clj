(ns clojure-china.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "welcome to clojure-china")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
