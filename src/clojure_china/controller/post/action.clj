(ns clojure-china.controller.post.action
  (:require [noir.validation :refer [valid-number?]]

            [clojure-china.controller.json :as mj]
            [clojure-china.model.post.post :as pdb]))

(def msg {

           })

(defn- result
  [status message fun]
  (fn
    [user pages]
    (do
      (println user (type user))
      (-> {}
          (mj/status status)
          (mj/message message)
          (mj/posts (fun user pages))
          (mj/map2json)))))
(def id
  (result 200 "test" pdb/by-user-id))

(def pname
  (result 200 "test" pdb/by-user-name))

(defn query
  [^String id]
  (if (valid-number? id)
    (mj/map2json (pdb/id (Long/valueOf id)))))


(defn by-user
  [user pages]
  (println pages)
  (let [page (- (mj/valid-pages pages) 1)]
    (println (type user) (type page))
    (if (valid-number? user)
      (id (Long/valueOf user) page)
      (pname user page))))