(ns clojure-china.pages.user-action
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [hiccup.form :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]))

(def register-form
  [:form {:role "form" :action "/register" :method "post"}
   [:div {:class "form-group"}
    [:label {:for "username"} "用户名"]
    [:input {:type "text" :class "form-control" :id "username" :name "username" :placeholder "用户名请用英文及数字，长度
请勿超过20个字符"}]]
   [:div {:class "form-group"}
    [:label {:for "password"} "密码"]
    [:input {:type "password" :class "form-control" :id "password" :name "password"}]]
   [:div {:class "form-group"}
    [:label {:for "password"} "密码确认"]
    [:input {:type "password" :class "form-control" :id "rpassword" :name "rpassword"}]]
   [:div {:class "form-group"}
    [:label {:for "email"} "Email地址"]
    [:input {:type "email" :class "form-control" :id "email" :name "email"}]]
   [:button {:type "submit" :class "btn btn-default" }"注册"]])

(def register-modal
  [:div {:class "modal fade" :id "register" :tabindex "-1" :role "dialog" :aria-labelledby "注册会员" :aria-hidden "true"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4 {:class "modal-title" :id "registerLabel"} "注册会员"]]
     [:div {:class "modal-body"}
      registr-form]]]])


#_(def register-form
  [:form.form-signin {:role "form" :method "POST" :action "/register"}
   [:table
    [:tr
     [:td (label :username "Username")]
     [:td [:input.form-control {:name "username" :type "text" :placeholder "Username"}]]]
    [:tr
     [:td (label :password "Password")]
     [:td [:input.form-control {:name "password" :type "password" :placeholder "Password"}]]]
    [:tr
     [:td (label :r-password "Repeat Password")]
     [:td [:input.form-control {:name "r-password" :type "password" :placeholder "Repeat Password"}]]]
    [:tr
     [:td (label :email "Email")]
     [:td [:input.form-control {:name "email" :type "text" :placeholder "Email"}]]]
    [:tr
     [:td]
     [:td [:button.btn.btn-primary {:type "submit"} "Register"]]]]])

(def-page register-page []
          {:hiccup (list
                     (session/flash-get :error)
                     (session/flash-get :success)
                     register-modal)
                     ;;register-form)
           :title "register"})

(def login-form
  [:form {:class "form-signin", :role "form" :method "POST" :action "/login"} 
   [:h2 {:class "form-signin-heading"} "Please sign in"]
   [:input {:name "user" :type "text", :class "form-control", :placeholder "Email address", :required "", :autofocus ""}]
   [:input {:name "pwd" :type "password", :class "form-control", :placeholder "Password", :required ""}]
   [:label {:class "checkbox"}
    [:input {:type "checkbox", :value "remember-me"}] "Remember me"] 
   [:button {:class "btn btn-lg btn-primary btn-block", :type "submit"} "Sign in"]])

(def-page login-page []
          {:hiccup
           (list
             (session/flash-get :error)
             (session/flash-get :success)
             login-form)
           :tilet "登录"})
