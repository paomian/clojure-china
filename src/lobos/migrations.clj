(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers))
  (:require [clojure-china.dbutil.dbconn :refer [db-spec]]))



(defmigration users
              (up []
                  (create
                    (tbl :cc_users
                         (varchar :username 50 :not-null)
                         (varchar :password 100 :not-null)
                         (varchar :email 100 :not-null)
                         (boolean :id_admin :not-null (default false))
                         (varchar :alive 20 :not-null (default "normal"))
                         (check :check_alive (in :alive ["normal" "delete" "look"])))))
              (down [] (drop (table :cc_users))))

(defmigration nodes
              (up []
                  (create
                    (tbl :cc_nodes
                         (varchar :node 50 :not-null))))
              (down [] (drop (table :cc_nodes))))

(defmigration posts
              (up []
                  (create
                    (tbl :cc_posts
                         (integer :mark (default 0))
                         (varchar :title 200 :not-null :unique)
                         (text :content)
                         (varchar :status 20)
                         (check :check_status (in :status ["normal" "delete" "other"]))
                         (refer-to :cc_users)
                         (refer-to :cc_nodes))))
              (down [] (drop (table :cc_posts))))

(defmigration replys
              (up []
                  (create
                    (tbl :cc_replys
                         (text :reply)
                         (varchar :status 20)
                         (check :check_status (in :status ["normal" "delete" "good"]))
                         (refer-to :cc_posts))))
              (down [] (drop (table :cc_replys))))
