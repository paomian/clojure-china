(ns clojure-china.pages.user-action
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
                     register-form)
           :title "register"})

(def login-form
  [:form {:class "form-signin", :role "form" :action "/login"} 
   [:h2 {:class "form-signin-heading"} "Please sign in"]
   [:input {:type "text", :class "form-control", :placeholder "Email address", :required "", :autofocus ""}]
   [:input {:type "password", :class "form-control", :placeholder "Password", :required ""}]
   [:label {:class "checkbox"}
    [:input {:type "checkbox", :value "remember-me"}] "Remember me"] 
   [:button {:class "btn btn-lg btn-primary btn-block", :type "submit"} "Sign in"]])

(def-page login-page []
          {:hiccup
           (list
             (session/flash-get :error)
             (session/flash-get :success)
             login-form)
           :tilet "login"})
