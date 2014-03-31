(ns clojure-china.pages.user-action
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [hiccup.form :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]))
(def register-form
  [:form.form-horizontal {:method "POST" :action "/register"}
   [:table
    [:tr
     [:td (label :username "Username")]
     [:td [:input.form-control#user {:type "text" :placeholder "Username"}]]]
    [:tr
     [:td (label :password "Password")]
     [:td [:input.form-control#pwd {:type "password" :placeholder "Password"}]]]
    [:tr
     [:td (label :r-password "Repeat Password")]
     [:td [:input.form-control#r-pwd {:type "password" :placeholder "Repeat Password"}]]]
    [:tr
     [:td (label :email "Email")]
     [:td [:input.form-control#email {:type "text" :placeholder "Email"}]]]
    [:tr
     [:td]
     [:td [:button.btn.btn-primary {:type "submit"} "Register"]]]]])

(def-page register-page []
          (session/flash-get :error)
          (session/flash-get :success)
          register-form)
