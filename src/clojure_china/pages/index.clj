(ns clojure-china.pages.index
  (:require  [clojure-china.pages.htmlutil :refer [def-page]
             [hiccup.core :refer :all]
             [hiccup.page :refer :all]]))
(def-page index []
          [:h1 "welcome Clojure-China"])
