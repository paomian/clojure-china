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
                    ;;按用户查询post
                    (GET "/user/:user/post"
                         {{user :user pages :pages} :params :as request} (pri request (result (pact/post-byuser user pages))))
                    ;;按节点查询post
                    (GET "/node/:node"
                         {{node :node pages :pages} :params :as request} (pri request (result (pact/post-byuser node pages))))
                    ;;post查询
                    (GET "/post/:post"
                         {{post :post} :params :as request} (pri requset (result (pact/post-query post))))

                    ;;用户查询
                    (GET "/user/:user"
                         {{user :user} :params :as request} (pri request (result (aact/user-query user))))
                    ;;用户注册
                    (POST "/user"
                          {{username :username email :email password :password} :params :as request} (pri request ()))
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