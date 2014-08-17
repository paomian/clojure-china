(ns clojure-china.pages.post.action
  (:require [noir.session :as session]
            [clojure-china.pages.htmlutil :refer :all]
            [clojure-china.dbutil.post.post :refer :all]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(declare post-list)
(declare user-list)
(declare index-list-time)
(declare index-list-reply)
(declare detail)
(declare create)
(declare delete)
(defn index-list)

(defn post-list [pages]
  (for [post (post-paging-bytime pages)]
    [:div {:class "row"} 
     [:div {:class "col-12 col-sm-12 col-lg-12"} 
      [:div {:class "col-2 col-sm-2 col-lg-2"}
       [:div {:class "thumbnail"}
        [:img {:class "mg-rounded" :src "pantakill.jpg" :alt "paomian"}]]
       ]
      [:div {:class "col-10 col-sm-10 col-lg-10"}
       [:h4 {} (:content post)]
       [:p {} (:author post)]]]]))


(def-page post-list-bytime [pages]
          {:hiccup (list
                     (session/flash-get :error)
                     (session/flash-get :success)
                     (post-list pages)
                     )})

;;(def )
#_(defn post-create []
  )
;;(def-page post-list-byauthor [pager])
