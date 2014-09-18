(ns clojure-china.db.entitys
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]
             [db :as kdb]]
            [clojure-china.model.dbconn :refer [db-spec]]))

(kdb/defdb ccdb db-spec)

(k/defentity users
             (k/table :users)
             (k/database ccdb))

(k/defentity nodes
             (k/table :nodes)
             (k/database ccdb))

(k/defentity posts
             (k/table :posts)
             (k/database ccdb)
             (k/belongs-to users {:fk :user_id})
             (k/belongs-to nodes {:fk :node_id}))


(k/defentity replys
             (k/table :nodes)
             (k/database ccdb)
             (k/belongs-to posts {:fk :post_id}))