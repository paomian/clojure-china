(ns clojure-china.controller.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware
             [content-type :as rc]]
            [noir.session :as session]
            [noir.cookies :as cookies]
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
                    ;;按用户查询post
                    (GET "/user/:user/post"
                         {{user :user pages :pages} :params :as request}
                         (pri request (pact/by-user user pages)))
                    ;;按节点查询post
                    (GET "/node/:node"
                         {{node :node pages :pages} :params :as request}
                         (pri request (pact/by-node node pages))) ;todo post按照需求查询
                    ;;post查询
                    (GET "/post/:post"
                         {{post :post} :params :as request}
                         (pri request (pact/query post)))

                    ;;用户查询
                    (GET "/user/:user"
                         {{user :user} :params :as request}
                         (pri request (aact/get-user user)))
                    ;;用户注册
                    (PUT "/user"
                         {
                           {username :username email :email password :password}
                           :params :as request}
                         (pri request (aact/register username password email)))
                    (POST "/user"
                          {
                            {username :username password :password}
                            :params :as request}
                          (pri request (aact/login username password)))
                    (GET "/logout"
                         request (pri request (aact/logout))))
           (route/resources "/")
           (route/not-found "Not Found"))
(def app
  (-> app-routes
      (handler/api)
      (session/wrap-noir-flash)
      (session/wrap-noir-session {:store       (carmine-store session-conn {
                                                                             :key-prefix      "session"
                                                                             :expiration-secs (* 60 60 24 30)
                                                                             })
                                  :cookie-name "china"})
      (cookies/wrap-noir-cookies)
      (rc/wrap-content-type "text/json")))