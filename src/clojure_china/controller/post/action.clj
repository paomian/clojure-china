(ns clojure-china.controller.post.action
  (:require [noir.validation :refer [valid-number?]]
            [noir.session :as session]
            [clojure-china.controller.json :as mj]
            [clojure-china.model.post.post :as pdb]))

(def msg {

           })

(defn- result
  [status message res]
  (println res)
  (-> {}
      (mj/status status)
      (mj/message message)
      (mj/posts res)))

(defn query
  "
  直接按照id查询post
  param:
    id post id
  "
  [^String id]
  (if (valid-number? id)
    (pdb/id (Long/valueOf id))))


(defn by-user
  "
  按照用户来查询post
  param:
    user: 用户名或者用户id
    pages: 分页后的页码 当pages为空时 默认为0，
  "
  ([^String user]
   (by-user user 0))

  ([^String user ^String pages]
   (println pages)
   (let [page (mj/valid-pages pages)]
     (println (type user) (type page))
     (if (valid-number? user)
       (result 200 "test" (pdb/by-user-id user page))
       (result 200 "test" (pdb/by-user-name user page))))))


(defn by-node
  "
  按照用户来查询post
  param:
    node: 节点名或者节点id
    pages: 分页后的页码 当pages为空时 默认为0，
  "
  ([^String node]
   (by-node node 0))

  ([^String node ^String pages]
   (println pages)
   (let [page (mj/valid-pages pages)]
     (println (type node) (type page))
     (if (valid-number? node)
       (result 200 "test" (pdb/by-node-id node page))
       (result 200 "test" (pdb/by-node-name node page))))))

(defn create!
  ""
  {:arglists }
  [title content node]
  (if-let [user (session/get :username)]
  (pdb/create! title content user node)))