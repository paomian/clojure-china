(ns clojure-china.pages.index
  (:use [hiccup core page]
        [clojure-china.pages.htmlutil :only [def-page]])
  (:require [noir.session   :as session]
            ))
(def-page index []
          [:div {:class "jumbotron"}
           [:h1 {} "Jumbotron heading"]
           [:p {:class "lead"} "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus."]
           [:p 
            [:a {:class "btn btn-lg btn-success", :href "#", :role "button"} "Sign up today"]]])
