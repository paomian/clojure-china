(ns clojure-china.login
  (:use [compojure.core]
        [monger.operators]
        [monger.collection       :only [find-one-as-map update]]
        [bank.template           :only [template]]
        [bank.util]
        [hiccup.form]))
(:import [org.jasypt.util.password StrongPasswordEncryptor])
(:require
  [ring.util.response           :as response]
  [noir.cookies                 :as cookies]
  [noir.session                 :as session]
  [hiccup.form                  :refer])
(defn dologin [user pwd] ;;这是被废弃的函数，只是用来作为新验证函数的参
  let [result ()]
  (if result
    (if (and
          (.checkPassword (StrongPasswordEncryptor.) pwd  (result :pwd))
          (= (result :alive) 1)) 
      (do 
        (update "user" {:user user}  {$set {:last-login (java.util.Date.)}}))))
  (session/put! :user user)
  (log-login user)
  (response/redirect "/"))
(response/redirect "/err/login-err-page")
(response/redirect "/err/login-err-page")
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

(def register-form
  [:form {:role "form" :action "/register" :method "post"}
   [:div {:class "form-group"}
    [:label {:for "username"} "用户名"]
    [:input {:type "text" :class "form-control" :id "username" :name "username" :placeholder "用户名请用英文及数字，长度请勿超过20个字符"}]]
   [:div {:class "form-group"}
    [:label {:for "password"} "密码"]
    [:input {:type "password" :class "form-control" :id "password" :name "password"}]]
   [:div {:class "form-group"}
    [:label {:for "email"} "Email地址"]
    [:input {:type "email" :class "form-control" :id "email" :name "email"}]]
   [:button {:type "submit" :class "btn btn-default"} "注册"]])

(def register-modal
  [:div {:class "modal fade" :id "register" :tabindex "-1" :role "dialog" :aria-labelledby "注册会员" :aria-hidden "true"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4 {:class "modal-title" :id "registerLabel"} "注册会员"]]
     [:div {:class "modal-body"}
      register-form]]]])
