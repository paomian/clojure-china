(ns clojure-china.handler.util
  "json的相关事宜"
  (:require [noir.validation :refer [valid-number?]]
            [cheshire.core :refer :all]
            [clj-time.local :as l]))

(defn map2json
  "map 转化为 json"
  [map]
  (generate-string map))

(defn status
  [m ^Integer s]
  (assoc m :status s))

(defn message
  [m ^String mss]
  (assoc m :message mss))

(defn posts
  [m p]
  (assoc m :post p))

(defn users
  [m p]
  (assoc m :user p))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn created_at
  [m c]
  (assoc m :created_on c))

(defn valid-n
  [pages]
  (if (number? pages)
    pages
    (if (valid-number? pages)
      (Long/valueOf pages))))

(defn wrap-json [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (if (not= (:status response) 404)
          (assoc-in response [:headers "Content-Type"] content-type)
        response))))

;;todo 写一个wrap handler 来验证用户的权限 重构权限管理的所有模块

#_(defmacro with-validations
  "triggers a validation before evaluating 'body', if the validation fails, throws an InvalidPlayerOperation.
   param 'vs' should be a binding of a pred and an error message (in string or hash-map), e.g:
     (defn cost-money [pid cost-amount]
       (let [money (get-money pid)]
         (with-validations
           [(< money cost-amount) \"not enough money\"
            (wallet-locked? pid)   \"your wallet is locked\"]
         ;; the function that actually costs money, without checking
         (cost-money* pid cost-amount))))

   Remind that due to 'InvalidPlayerOperation' being an exception,
     when 'InvalidPlayerOperation' raises, the 'with-validations' short-circuits its follow-up operations even out of the scope"
  [vs & body]
  (let [[f vs body]
        (if (coll? vs)
          [`identity vs body]
          [vs (first body) (rest body)])
        c (partition 2 vs)
        c (mapcat
            (fn [[pred to-throw]]
              [pred `(throw
                       (InvalidPlayerOperation.
                         (pr-str
                           (~f ~(if (:message to-throw)
                                  to-throw
                                  {:message to-throw})))))])
            c)]
    `(~f (do (cond ~@c) ~@body))))

#_(defmacro assuming
  "Guard body with a series of tests. Each clause is a test-expression
  followed by a failure value. Tests will be performed in order; if
  each test succeeds, then body is evaluated. Otherwise, fail-expr is
  evaluated with the symbol 'why bound to the failure value associated
  with the failing test."
  [[& clauses] body & [fail-expr]]
  `(if-let [[~'why]
            (cond
              ~@(mapcat (fn [[test fail-value]]
                          [`(not ~test) [fail-value]])
                        (partition 2 clauses)))]
     ~fail-expr
     ~body))

#_(defn do-register [user pwd repeat-pwd email]
  (let [lower-user (.toLowerCase user)]
    (assuming [(nil? (fetch-one :users :where {:user lower-user}))
               (err-msg "settings.user-exists"),
               (< 3 (.length lower-user) 14)
               (err-msg "settings.uname-size"),
               (= lower-user
                  (first (re-seq #"[A-Za-z0-9_]+" lower-user)))
               (err-msg "settings.uname-alphanum")
               (< 6 (.length pwd))
               (err-msg "settings.pwd-size"),
               (= pwd repeat-pwd)
               (err-msg "settings.pwd-match"),
               (plausible-email? email)
               (err-msg "settings.email-invalid")
               (nil? (fetch-one :users :where {:email email}))
               (err-msg "settings.email-exists")]
              (do
                (insert! :users
                         {:user lower-user
                          :pwd (.encryptPassword (StrongPasswordEncryptor.) pwd)
                          :email email})
                (session/put! :user lower-user)
                (response/redirect "/"))
              (do
                (session/flash-put! :user user)
                (session/flash-put! :email email)
                (flash-error "/register" why)))))

(defn a )