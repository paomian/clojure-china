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
     [:h1 {} "Welcome to Clojure-China"] 
     [:p {} "This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action."]]
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
      [:p {} [:a {:class "btn btn-default", :href "#", :role "button"} "View details »"]]]]]
   [:div {:class "col-xs-6 col-sm-3 sidebar-offcanvas", :id "sidebar", :role "navigation"} 
    [:div {:class "list-group"} 
     [:a {:href "#", :class "list-group-item active"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]
     [:a {:href "#", :class "list-group-item"} "Link"]]]]))

(def-page index []
          {:hiccup (list 
                     (session/flash-get :success)
                     index-form) :title "主页"})
