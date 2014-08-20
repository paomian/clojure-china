(ns clojure-china.controller.post.action
  (:require [noir.validation :refer [valid-number?]]

            [clojure-china.controller.json :refer :all]
            [clojure-china.model.post.post :as pdb]))

#_(defn- swich
  [user pages func func1]
  (if (valid-number? user)
    (func (Long/valueOf user) pages)
    (func1 user pages)))

(str "{\"status\" : \"500\" \"message\" : \"no valid query args\"}")


#_(defn postsbyuser
  [user pages]
  (swich user pages paging-byauthorid paging-byauthorname))

(defn- id
  [user pages]
  (do (println user (type user))
      (-> {}
          (status 200)
          (message "test")
          (posts (pdb/paging-byauthorid user pages))
          (map2json))))

(defn- sname
  [user pages]
  (do (println user (type user))
      (-> {}
          (status 200)
          (message "test")
          (posts (pdb/paging-byauthorname user pages))
          (map2json))))

(defn post-byuser
  [user pages]
  (println pages)
  (let [page (- (valid-pages pages) 1)]
    (println (type user) (type page))
    (if (valid-number? user)
      (id (Long/valueOf user) page)
      (sname user page))))