(ns clojure-china.pages.index
  (:require  
    [clojure-china.pages.htmlutil :refer [def-page]]
    [hiccup.core :refer :all]
    [clojure-china.pages.user-action :refer [register-modal login-modal]]
    [noir.session :as session]
    [hiccup.page :refer :all]))
(def index-form
  (list
  register-modal
  login-modal
  [:div {:class "row row-offcanvas row-offcanvas-right"} 
   [:div {:class "col-xs-12 col-sm-9"} 
    [:p {:class "pull-right visible-xs"} 
     [:button {:type "button", :class "btn btn-primary btn-xs", :data-toggle "offcanvas"} "Toggle nav"] 
     ] 
    [:div {:class "jumbotron"}
     [:h2 {} "Welcome to Clojure-China"] 
     [:p {} "欢迎来到Clojure-China，对，没错，这里就是clojure社区，有望成为国内最权威的clojure社区，拥有国内几乎所有资深的clojure工程师。"]]
    [:div {:class "row"} 
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]
     [:div {:class "col-6 col-sm-6 col-lg-4"} 
      [:h2 {} "Heading"]
      [:p {} "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]]]]))
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
