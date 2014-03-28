;;用户登录验证，和相关处理
(ns clojure-china.control.user-action
  (:require [clojure-china.dbutil.userdbutil    :as dbutil]
            [noir.session                       :as session :refer [get set clear!]]
            [ring.util.response                 :as response :refer [redirect]])
  (:import [org.jasypt.util.password StrongPasswordEncryptor]))
(defn dolongin [user pwd]
  (let [userinfo ((db/user-name-query user))]
    (if ())))
