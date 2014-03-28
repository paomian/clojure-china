(ns clojure-china.control.register
  (:require [clojure-china.dbutil.userdbutil :refer [insert-user!]])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))

(defn register-user
  [{:keys [username password email] :as params}]
  (let [encrypter (StrongPasswordEncryptor.)]
    (insert-user! username (.encryptPassword encrypter password) email true)))
