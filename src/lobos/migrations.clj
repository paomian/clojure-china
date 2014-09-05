(ns lobos.migrations
  (:require [clojure-china.model.dbconn :refer [db-spec]])
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defn dbinit []
  (do
    (migrate)
    (lobos.connectivity/close-global)))

(defmigration users
              (up []
                  (create
                    (tbl :users
                         (varchar :username 50 :not-null)
                         (varchar :password 100 :not-null)
                         (varchar :email 100 :not-null)
                         (boolean :is_admin :not-null (default false))
                         (timestamp :last_login)
                         (timestamp :register_on :not-null (default (now)))
                         (varchar :alive 20 :not-null (default "normal"))
                         (check :check_alive (in :alive ["normal" "delete" "look" "other"])))))
              (down [] (drop (table :users))))

(defmigration nodes
              (up []
                  (create
                    (tbl :nodes
                         (varchar :node 50 :not-null))))
              (down [] (drop (table :nodes))))

(defmigration posts
              (up []
                  (create
                    (tbl :posts
                         (integer :mark (default 0))
                         (varchar :title 200 :not-null :unique)
                         (text :content)
                         (varchar :status 20 (default "normal"))
                         (check :check_status (in :status ["normal" "delete" "other"]))
                         (refer-to :users)
                         (refer-to :nodes))))
              (down [] (drop (table :posts))))

(defmigration replys
              (up []
                  (create
                    (tbl :replys
                         (text :reply)
                         (varchar :status 20)
                         (check :check_status (in :status ["normal" "delete" "good" "other"]))
                         (refer-to :posts))))
              (down [] (drop (table :replys))))
