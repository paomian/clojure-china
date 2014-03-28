(ns clojure-china.control.register
  (:require [clojure-china.dbutil.userdbutil :refer [insert-user!]])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))

(defn user-register
  [{:keys [username password rpassword email] :as params}]
  (let [encrypter (StrongPasswordEncryptor.)]
    (if (= password rpassword)
    (insert-user! username (.encryptPassword encrypter password) email true))
    ()
    ))
(defn user-login []
  ())
(defn user-logout []
  ())
