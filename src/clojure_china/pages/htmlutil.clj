(ns clojure-china.pages.htmlutil
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))
;;页面模板
(defn head-template [code]
  (let [user (session/get :user)]
    (html5
      [:head
       [:title (:title code)]
       (include-css "http://clojure-china.qiniudn.com/bootstrap-theme.min.css")
       (include-css "http://clojure-china.qiniudn.com/bootstrap.min.css")
       #_(include-css "")
       [:body
        [:nav.navbar.navbar-inverse.navbar-static-top {:role "navigation"}
         [:div.navbar-header
          [:button.navbar-toggle 
           {:type "button" :data-toggle "collapse" :data-target "#bs-example-navbar-collapse-8"}
           [:span.sr-only ""]
           (for [x (range 3)]
             [:span.icon-bar])]]
         [:a.navbar-brand {:href "/"} "Clojure-China"]
         [:div.collapse.navbar-collapse{:id "bs-example-navbar-collapse-8"}
          [:ul.nav.navbar-nav
           [:ul.nav.navbar-nav
            (for [[content url classes] '(["首页" "#" ""]
                                          ["Wiki" "#" ""]
                                          ["会员" "#" ""])]
              [:li [:a {:class classes :href url} content]])]]]]
        code
        (include-js "http://clojure-china.qiniudn.com/jquery.min.js")
        (include-js "http://clojure-china.qiniudn.com/bootstrap.min.js")
        ]])))

(defmacro def-page [page-name [& args] & code]
  `(defn ~page-name [~@args]
     (head-template (do ~@code))))
