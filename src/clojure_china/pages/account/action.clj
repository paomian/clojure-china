(ns clojure-china.pages.account.action
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [hiccup.form :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]))

(def register-form
  [:form.form-signin {:role "form" :method "POST" :action "/register"}
   [:table
    [:tr
     [:td (label :username "Username")]
     [:td [:input.form-control {:name "username" :type "text" :placeholder "Username"}]]]
    [:tr
     [:td (label :password "Password")]
     [:td [:input.form-control {:name "password" :type "password" :placeholder "Password"}]]]
    [:tr
     [:td (label :r-password "Verify Password")]
     [:td [:input.form-control {:name "r-password" :type "password" :placeholder "Verify Password"}]]]
    [:tr
     [:td (label :email "Email")]
     [:td [:input.form-control {:name "email" :type "text" :placeholder "Email"}]]]
    [:tr
     [:td]
     [:td [:button.btn.btn-primary {:type "submit"} "Register"]]]]])

(def register-modal
  [:div {:class "modal fade" :id "register" :tabindex "-1" :role "dialog" :aria-labelledby "注册会员" :aria-hidden "true"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4 {:class "modal-title" :id "registerLabel"} "注册会员"]]
     [:div {:class "modal-body"}
      register-form]]]])

(def-page register-page []
          {:hiccup (list
                     (session/flash-get :error)
                     (session/flash-get :success)
                     register-modal)
           ;;register-form)
           :title "register"})

(def login-form
  [:form {:role "form" :method "POST" :action "/login"} 
   [:h2 {:class "form-signin-heading"} "Please sign in"]
   [:input {:name "user" :type "text", :class "form-control", :placeholder "Email address", :required "", :autofocus ""}]
   [:input {:name "pwd" :type "password", :class "form-control", :placeholder "Password", :required ""}]
   [:label {:class "checkbox"}
    [:input {:type "checkbox", :value "remember-me"}] "Remember me"] 
   [:button {:class "btn btn-lg btn-primary btn-block", :type "submit"} "Sign in"]])

(def login-modal
  [:div {:class "modal fade" :id "login" :tabindex "-1" :role "dialog" :aria-labelledby "登录" :aria-hidden "true"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4 {:class "modal-title" :id "loginLabel"} "登录"]]
     [:div {:class "modal-body"}
      login-form]]]])

(def-page login-page []
          {:hiccup
           (list
             (session/flash-get :error)
             (session/flash-get :success)
             login-form)
           :tilet "登录"})
