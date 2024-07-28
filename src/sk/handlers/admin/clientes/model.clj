(ns sk.handlers.admin.clientes.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-clientes-sql
  (str
   "
SELECT clientes.*,
   modelos.nombre as modelos_id_formatted,
   distribuidor.nombre as distribuidor_id_formatted
FROM clientes
JOIN modelos on modelos.id = clientes.modelos_id
JOIN distribuidor on distribuidor.id = clientes.distribuidor_id
"))

(defn get-clientes
  []
  (Query db get-clientes-sql))

(def get-clientes-id-sql
  (str
   "
SELECT *
FROM clientes
WHERE id = ?
"))

(defn get-clientes-id
  [id]
  (first (Query db [get-clientes-id-sql id])))

(defn distribuidor-options
  []
  (let [rows  (Query db ["select id as value, nombre as label from distribuidor order by nombre"])]
    (list* {:value "" :label "Seleccione distribuidor..."}
           rows)))

(defn get-modelos-options
  [dist-id]
  (Query db ["select id as value,nombre as label from modelos where distribuidor_id = ?" dist-id]))

(defn get-modelo-value
  [modelo-id]
  (first (Query db ["select * from modelos where id = ?" modelo-id])))

(comment
  (get-modelo-value 1)
  (get-modelos-options 1)
  (get-clientes)
  (distribuidor-options))
