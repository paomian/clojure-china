(ns clojure-china.htmlutil
  (:user [hiccup core page])
  (:require [noir.session         :as session]))
;;页面模板
(defn html-template [code]
 (let [user (session/get :user)]
   (html5
     [:head
      [:title (:title code)]
      (include-css "")
      (include-css "")
      #_(include-css "")
      [:body
       [:nav.navbar.navbar-default.navbar-static-top {:role "navigation"}
        [:div.navbar-header
         [:button.navbar-toggle 
          {:type "button" :data-toggle "collapse" :data-target "#bs-example-navbar-collapse-8"}
          [:span.sr-only ""]
          (for [x (range 3)]
            [:span.icon-bar])]]
        ]]])))
