(ns clojure-china.pages.index
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]))

(def-page index []
          [:div {:class "jumbotron"}
           [:h1 {} "Jumbotron heading"]
           [:p {:class "lead"} "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus."]
           [:p 
            [:a {:class "btn btn-lg btn-success", :href "#", :role "button"} "Sign up today"]]])
