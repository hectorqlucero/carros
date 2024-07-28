(ns sk.models.cdb
  (:require [noir.util.crypt :as crypt]
            [sk.models.crud :refer [db Insert-multi Query!]]))

(def users-rows
  [{:lastname  "User"
    :firstname "Regular"
    :username  "user@gmail.com"
    :password  (crypt/encrypt "user")
    :dob       "1957-02-07"
    :email     "user@gmail.com"
    :level     "U"
    :active    "T"}
   {:lastname "User"
    :firstname "Admin"
    :username "admin@gmail.com"
    :password (crypt/encrypt "admin")
    :dob "1957-02-07"
    :email "admin@gmail.com"
    :level "A"
    :active "T"}
   {:lastname "User"
    :firstname "System"
    :username "sistema@gmail.com"
    :password (crypt/encrypt "sistema")
    :dob "1957-02-07"
    :email "sistema@gmail.com"
    :level "S"
    :active "T"}])

(def distribuidor-rows
  [{:id "1"
    :nombre "Ford"
    :notas "usa autos"}
   {:id "2"
    :nombre "Chevrolet"
    :notas "usa autos"}])

(def modelos-rows
  [{:id "1"
    :nombre "Mustang"
    :precio "36000"
    :distribuidor_id "1"}
   {:id "2"
    :nombre "Pinto"
    :precio "15000"
    :distribuidor_id "1"}
   {:id "3"
    :nombre "F-50"
    :precio "38000"
    :distribuidor_id "1"}
   {:id "4"
    :nombre "Ranger"
    :precio "32000"
    :distribuidor_id "1"}
   {:id "5"
    :nombre "Chevelle"
    :precio "17000"
    :distribuidor_id "2"}
   {:id "6"
    :nombre "Impala"
    :precio "22000"
    :distribuidor_id "2"}])

(defn populate-tables
  "Populates table with default data"
  [table rows]
  (Query! db (str "LOCK TABLES " table "WRITE;"))
  (Insert-multi db (keyword table) rows)
  (Query! db "UNLOCK TABLES;"))

(defn database []
  (populate-tables "users" users-rows)
  (populate-tables "distribuidor" distribuidor-rows)
  (populate-tables "modelos" modelos-rows))
