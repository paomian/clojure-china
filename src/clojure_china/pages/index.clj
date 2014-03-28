(ns clojure-china.pages.index
  (:require [noir.session :as session]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [clojure-china.pages.htmlutil :refer [def-page]]
            [clojure-china.control.register :refer [register-user]]
            [clojure-china.pages.register :refer [register-modal]]))

(def-page index
  [alert-type]
  [:div
   register-modal
   (if (= alert-type :register-successful)
     [:div {:class "alert alert-success fade in"}
      [:button {:type "button" :class "close" :data-dismiss "alert" :aria-hidden "true"} "&times;"]
      [:strong "恭喜！"]
      "您已经成功注册用户！"])
   [:div {:class "jumbotron"}
    [:h1 {} "Jumbotron heading"]
    [:p {:class "lead"} "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus."]
    [:button {:class "btn btn-lg btn-success", :data-toggle "modal" :data-target "#register"} "Sign up today"]]])

(defn register
  [params]
  (do
    (register-user params)
    (index :register-successful)))
