(ns clojure-china.handler.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware
             [content-type :as rc]]
            [noir.session :as session]
            [noir.cookies :as cookies]
            [noir.validation :refer [valid-number?]]
            [compojure.core :refer :all]
            [taoensso.carmine.ring :refer :all]

            [clojure-china.db.redis :refer [session-conn]]
            [clojure-china.post.controller :as pc]
            [clojure-china.account.controller :as ac]
            [clojure-china.handler.util :as hu]))

(defn pri [request body]
  (println request)
  (hu/map2json body))

(defroutes china-routes
           (context "/v1" _
                    ;;按用户查询post
                    (GET "/user/:user/post"
                         {{user :user pages :pages} :params :as request}
                         (pri request (pc/by-user user pages)))
                    ;;按节点查询post
                    (GET "/node/:node/post"
                         {{node :node pages :pages} :params :as request}
                         (pri request (pc/by-node node pages))) ;todo post按照需求查询
                    ;;post查询
                    (GET "/post/:post"
                         {{post :post} :params :as request}
                         (pri request (pc/query post)))

                    ;;用户查询
                    (GET "/user/:user"
                         {{user :user} :params :as request}
                         (pri request (ac/get-user user)))
                    ;;用户注册
                    (PUT "/user"
                         {
                           {username :username email :email password :password}
                           :params :as request}
                         (pri request (ac/register username password email)))
                    (POST "/user"
                          {
                            {username :username password :password}
                            :params :as request}
                          (pri request (ac/login username password)))
                    (GET "/logout"
                         request (pri request (ac/logout))))
           (route/resources "/")
           (route/not-found "Not Found"))

(defn wrap-json [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (println response)
      (assoc-in response [:headers "Content-Type"] content-type))))

(def app
  (-> china-routes
      (handler/api)
      #_(rc/wrap-content-type)
      (session/wrap-noir-flash)
      (session/wrap-noir-session {:store       (carmine-store session-conn {
                                                                             :key-prefix      "session"
                                                                             :expiration-secs (* 60 60 24 30)
                                                                             })
                                  :cookie-name "china"})
      (cookies/wrap-noir-cookies)
      (wrap-json "text/json")))