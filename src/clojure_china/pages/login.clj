(ns clojure-china.login
(:use [compojure.core]
      [monger.operators]
      [monger.collection       :only [find-one-as-map update]]
      [bank.template           :only [template]]
      [bank.util]
      [hiccup.form])
(:import [org.jasypt.util.password StrongPasswordEncryptor])
(:require
  [ring.util.response           :as response]
  [noir.cookies                 :as cookies]
  [noir.session                 :as session]))
#_(defn dologin [user pwd] ;;这是被废弃的函数，只是用来作为新验证函数的参
  let [result (find-one-as-map "user" {:user user})]
    (if result
      (if (and
            (.checkPassword (StrongPasswordEncryptor.) pwd  (result :pwd))
            (= (result :alive) 1))
        (do
          (update "user" {:user user}  {$set {:last-login (java.util.Date.)}})
          (session/put! :user user)
          (log-login user)
          (response/redirect "/"))
        (response/redirect "/err/login-err-page"))
      (response/redirect "/err/login-err-page")))

#_(defn dologout []
  (do 
    (log-logout (session/get :user))
    (session/clear!)
    (response/redirect "/")))

#_(defn bye []
  (let [result (find-one-as-map "user" (:user (session/get :user)))]
    (do 
      (update "user" {:user (result :user)} {$set {:alive 0}})
      (session/clear!)
      (response/redirect "/"))))
