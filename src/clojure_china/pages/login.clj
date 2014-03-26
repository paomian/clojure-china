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
(defn dologin [user pwd]
  (let [result (find-one-as-map "user" {:user user})]
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
      (response/redirect "/err/login-err-page"))))

(defn dologout []
  (do 
    (log-logout (session/get :user))
    (session/clear!)
    (response/redirect "/")))
(defn bye []
  (let [result (find-one-as-map "user" (:user (session/get :user)))]
    (do 
      (update "user" {:user (result :user)} {$set {:alive 0}})
      (session/clear!)
      (response/redirect "/"))))
