(ns clojure-china.pages.index
  (:require  [clojure-china.pages.htmlutil :refer [def-page]]
             [hiccup.core :refer :all]
             [hiccup.page :refer :all]))
(def index-form
  [:h1 {:style "text-align:center"}"Welcome Clojure-China"])
(def-page index []
          {:hiccup (list index-form) :title "主页"})
