(ns clojure-china.controller.post.action
  (:require [noir.validation :refer [valid-number?]]
            [noir.session :as session]
            [clojure-china.controller.util :as cu]
            [clojure-china.model.post.post :as pdb]))

(def emsg {
            :other "other error"
            :not-own "you can't delete this post"
            :not-login "you don't login"
            :post-id-error "post id not exist"
           })

(defn- result
  [status message res]
  (println res)
  (-> {}
      (cu/status status)
      (cu/message message)
      (cu/posts res)))

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
   (let [page (cu/valid-n pages)]
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
   (if (and (valid-number? pages) (valid-number? node))
     (let [node (Long/valueOf node)
           page (Long/valueOf pages)]
       (println (type node) (type page))
       (result 200 "test" (pdb/by-node-id node page))
       (result 200 "test" (pdb/by-node-name node page))))))

(defn create!
  ""
  {:arglists}
  [^String title ^String content ^String node]
  (if-let [user (session/get :userid)]
    (assoc {:code 200 :status "ok"} :message (pdb/create! title content user node))
    {:code 200 :status "error" :message "未登录"}))

(defn delete!
  ""
  {:arglists}
  [^String id]
  (if-let [user (session/get :userid)]
    (if-let [post (pdb/id id)]
      (if (= user (post :user_id))
        (assoc {:code 200 :status "ok" } :message (pdb/delete! id))
        {:code 200 :status "error" :message "you can't delete this post"})
      {})))
(defn delete!
  ""
  {:arglists}
  [^String id]
  (if (valid-number? id)
   (let [result {:code 200 :status "error" :messag "delete success"}]
     (if-let [user (session/get :userid)]
       (if-let [post (pdb/id id)]
         (if (= user (post :user_id))
           (do
             (pdb/delete! (Long/valueOf id))
             (assoc result :status "ok"))
           (assoc result :message (emsg :not-own)))
         (assoc result :message (emsg :post-id-error))))
     (assoc result :message (emsg :not-login)))))

(defn update!
  ""
  {:arglists}
  [^String title ^String content
   ^String id]
  (if-let [user (session/get :userid)]
    (if-let [post (pdb/id id)]
      (if (= user (post :user_id))
        (pdb/update! title content id)))))