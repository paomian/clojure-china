(ns clojure-china.index
  (:use [hiccup core page])
  (:require [noir.session   :as session]
            []))
(defn html-temp [code]
  (let [user (session/get :user)]
    (html5
      [:head])))
