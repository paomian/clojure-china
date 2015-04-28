(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.anti-forgery :as anti]
            [clojure.pprint :as pp]
            [noir.session :as ns]))

#_(def site-defaults
  "A default configuration for a browser-accessible website, based on current
  best practice."
  {:params    {:urlencoded true
               :multipart  true
               :nested     true
               :keywordize true}
   :cookies   true
   :session   {:flash true
               :cookie-attrs {:http-only true}}
   :security  {:anti-forgery   true
               :xss-protection {:enable? true, :mode :block}
               :frame-options  :sameorigin
               :content-type-options :nosniff}
   :static    {:resources "public"}
   :responses {:not-modified-responses true
               :absolute-redirects     true
               :content-types          true}})

(defroutes app-routes
  (GET "/" [] "hello world")
  (route/not-found "Not Found"))

(def cc-app
  (-> app-routes
      (wrap-defaults site-defaults)))
