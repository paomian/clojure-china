(ns clojure-china.pages.register)

(def register-form
  [:form {:role "form" :action "/register" :method "post"}
   [:div {:class "form-group"}
    [:label {:for "username"} "用户名"]
    [:input {:type "text" :class "form-control" :id "username" :name "username" :placeholder "用户名请用英文及数字，长度请勿超过20个字符"}]]
   [:div {:class "form-group"}
    [:label {:for "password"} "密码"]
    [:input {:type "password" :class "form-control" :id "password" :name "password"}]]
   [:div {:class "form-group"}
    [:label {:for "email"} "Email地址"]
    [:input {:type "email" :class "form-control" :id "email" :name "email"}]]
   [:button {:type "submit" :class "btn btn-default"} "注册"]])

(def register-modal
  [:div {:class "modal fade" :id "register" :tabindex "-1" :role "dialog" :aria-labelledby "注册会员" :aria-hidden "true"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4 {:class "modal-title" :id "registerLabel"} "注册会员"]]
     [:div {:class "modal-body"}
      register-form]]]])
