(ns clojure-china.pages.user-action
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]
            [clojure-china.control.register :refer [register-user]]
            [clojure-china.pages.register :refer [register-modal]]))

(def-page register-success-page
          []
          [:div
           register-modal
           #_(if (= alert-type :register-successful)
             [:div {:class "alert alert-success fade in"}
              [:button {:type "button" :class "close" :data-dismiss "alert" :aria-hidden "true"} "&times;"]
              [:strong "恭喜！"]
              "您已经成功注册用户！"])
           [:div {:class "jumbotron"}
            [:h1 {} "Jumbotron heading"]
            [:p {:class "lead"} "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus."]
            [:button {:class "btn btn-lg btn-success", :data-toggle "modal" :data-target "#register"} "Sign up today"]]])
(def-page register-page
          []
          register-form)

(def register-form
  [:form {:role "form" :action "/register" :method "post"}
   [:div {:class "form-group"}
    [:label {:for "username"} "用户名"]
    [:input {:type "text" :class "form-control" :id "username" :name "username" :placeholder "用户名请用英文及数字，长度请勿超过20个字符"}]]
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
