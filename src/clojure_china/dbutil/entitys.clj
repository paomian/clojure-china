(ns clojure-china.dbutil.entitys
  (:require [clojure.java.jdbc :as jdbc]
            [korma
             [core :as k]
             [db :as kdb]]
            [clojure-china.dbutil.dbconn :refer [db-spec]]))

(kdb/defdb ccdb db-spec)

(k/defentity users
             (k/table :cc_user)
             (k/database ccdb)
             (k/entity-fields :id :username))

(k/defentity nodes
             (k/table :cc_node)
             (k/database ccdb)
             (k/entity-fields :id :node))

(k/defentity posts
             (k/table :cc_post)
             (k/database ccdb)
             (k/entity-fields :id :title)
             (k/belongs-to users {:fk :author_id})
             (k/belongs-to nodes  {:fk :node_id}))


(k/defentity replys
             (k/table :cc_node)
             (k/database ccdb)
             (k/entity-fields :id :reply :nods :status)
             (k/belongs-to posts {:fk :post_id}))