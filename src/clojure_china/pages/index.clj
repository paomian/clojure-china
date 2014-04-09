(ns clojure-china.pages.index
  (:require  
    [clojure-china.pages.htmlutil :refer :all]
    [hiccup.core :refer :all]
    [clojure-china.pages.account.action :refer :all]
    [noir.session :as session]
    [hiccup.page :refer :all]))
(def index-form
  (list
    (create-modal {:id "register" :name "注册"} register-form)
    (create-modal {:id "login" :name "登录"} login-form)
  [:div {:class "row row-offcanvas row-offcanvas-right"} 
   [:div {:class "col-xs-12 col-sm-9"} 
    [:p {:class "pull-right visible-xs"} 
     [:button {:type "button", :class "btn btn-primary btn-xs", :data-toggle "offcanvas"} "Toggle nav"] 
     ] 
    [:div {:class "jumbotron"}
     [:h2 {} "Welcome to Clojure-China"] 
     [:p {} "欢迎来到Clojure-China，对，没错，这里就是clojure社区，有望成为国内最权威的clojure社区，拥有国内几乎所有资深的clojure工程师。"]]
    (for [x (range 3)]
    [:div {:class "row"} 
     [:div {:class "col-12 col-sm-12 col-lg-12"} 
      [:div {:class "col-2 col-sm-2 col-lg-2"}
       [:div {:class "thumbnail"}
       [:img {:class "mg-rounded" :src "pantakill.jpg" :alt "paomian"}]]
       ]
      [:div {:class "col-10 col-sm-10 col-lg-10"}
      [:h4 {} "测试帖子一定要很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长"]
      [:p {} "这是来自paomian的一个测试渲染效果的帖子"]]
      ]])
    ]]))
(def right-link
   [:div {:class "col-xs-6 col-sm-3 sidebar-offcanvas", :id "sidebar", :role "navigation"} 
  [:div {:class "list-group"} 
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]
   [:a {:href "#", :class "list-group-item"} "Link"]]])

(def-page index []
          {:hiccup (list 
                     (session/flash-get :error)
                     (session/flash-get :success)
                     index-form) :title "主页"})
