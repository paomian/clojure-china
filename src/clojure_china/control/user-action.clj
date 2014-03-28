;;用户登录验证，和相关处理
(ns clojure-china.control.user-action
  (:require [clojure-china.dbutil.userdbutil    :as dbutil]
            [noir.session                       :as session]
            [ring.util.response                 :as response])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))
;;用户登录
(defn user-longin [user pwd]
  (let [userinfo ((dbutil/user-name-query user))]
    (if ())))

;;用户注册
(defn register
    [{:keys [username password email] :as params}]
    (let [encrypter (StrongPasswordEncryptor.)]
          (dbutil/insert-user! username (.encryptPassword encrypter password) email true)))

(defn user-register
  [params]
  (do
    (register-user params)
    (index :register-successful)))
