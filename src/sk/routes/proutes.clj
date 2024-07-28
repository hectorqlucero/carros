(ns sk.routes.proutes
  (:require [compojure.core :refer [defroutes GET POST]]
            [sk.handlers.admin.users.controller :as users-controller]
            [sk.handlers.admin.clientes.controller :as clientes-controller]
            [sk.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/admin/users" params users-controller/users)
  (GET "/admin/users/edit/:id" [id] (users-controller/users-edit id))
  (POST "/admin/users/save" params [] (users-controller/users-save params))
  (GET "/admin/users/add" params [] (users-controller/users-add params))
  (GET "/admin/users/delete/:id" [id] (users-controller/users-delete id))

  (GET "/admin/clientes" params clientes-controller/clientes)
  (GET "/admin/clientes/edit/:id" [id] (clientes-controller/clientes-edit id))
  (POST "/admin/clientes/save" params [] (clientes-controller/clientes-save params))
  (GET "/admin/clientes/add" params [] (clientes-controller/clientes-add params))
  (GET "/admin/clientes/delete/:id" [id] (clientes-controller/clientes-delete id))
  (GET "/get_options/:id/:modelo" [id modelo] (clientes-controller/get-options id modelo))

  (GET "/users" params [] (users-dashboard/users params)))
