(ns sk.handlers.admin.clientes.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.handlers.admin.clientes.model :refer [distribuidor-options]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn clientes-view
  [title rows]
  (let [labels ["NOMBRE" "PATERNO" "MATERNO" "CELULAR" "EMAIL" "MODELOS_ID" "DISTRIBUIDOR_ID" "NOTAS"]
        db-fields [:nombre :paterno :materno :celular :email :modelos_id_formatted :distribuidor_id_formatted :notas]
        fields (zipmap db-fields labels)
        table-id "clientes_table"
        args {:new true :edit true :delete true}
        href "/admin/clientes"]
    (build-grid title rows table-id fields href args)))

(defn build-clientes-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-hidden-field {:id "modelos_id_tmp" ;; Nota: para guardar el valor del modelo
                        :name "modelos_id_tmp"
                        :value (if-not (nil? (:modelos_id row))
                                 (:modelos_id row)
                                 "0")})
   (build-field {:label "NOMBRE"
                 :type "text"
                 :id "nombre"
                 :name "nombre"
                 :placeholder "nombre aqui..."
                 :required false
                 :value (:nombre row)})
   (build-field {:label "PATERNO"
                 :type "text"
                 :id "paterno"
                 :name "paterno"
                 :placeholder "paterno aqui..."
                 :required false
                 :value (:paterno row)})
   (build-field {:label "MATERNO"
                 :type "text"
                 :id "materno"
                 :name "materno"
                 :placeholder "materno aqui..."
                 :required false
                 :value (:materno row)})
   (build-field {:label "CELULAR"
                 :type "text"
                 :id "celular"
                 :name "celular"
                 :placeholder "celular aqui..."
                 :required false
                 :value (:celular row)})
   (build-field {:label "EMAIL"
                 :type "text"
                 :id "email"
                 :name "email"
                 :placeholder "email aqui..."
                 :required false
                 :value (:email row)})
   (build-select {:label "DISTRIBUIDOR"
                  :type "text"
                  :id "distribuidor_id"
                  :name "distribuidor_id"
                  :placeholder "distribuidor_id aqui..."
                  :required false
                  :onchange "get_id()"
                  :value (:distribuidor_id row)
                  :options (distribuidor-options)})
   (build-select {:label "MODELO" ;; Nota no hay parametros 'options' ni 'value'
                  :type "text"
                  :id "modelos_id"
                  :name "modelos_id"
                  :placeholder "modelos_id aqui..."
                  :required false})
   (build-textarea {:label "NOTAS"
                    :id "notas"
                    :name "notas"
                    :rows "3"
                    :placeholder "notas aqui..."
                    :required false
                    :value (:notas row)})))

(defn build-clientes-form
  [title row]
  (let [fields (build-clientes-fields row)
        href "/admin/clientes/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-clientes-modal
  [title row]
  (build-modal title row (build-clientes-form title row)))

(defn clientes-edit-view
  [title row rows]
  (list
   (clientes-view "clientes Manteniento" rows)
   (build-clientes-modal title row)))

(defn clientes-add-view
  [title row rows]
  (list
   (clientes-view "clientes Mantenimiento" rows)
   (build-clientes-modal title row)))

(defn clientes-modal-script
  []
  (list
   (modal-script)
   ;; Nota: funcion 'get_id()' para borrar 'options' luego crear 'options' actuales y ejecutar esta funcion al terminar de cargar el DOM o sea la pagina con todos sus elementos.
   [:script
    "
   function get_id() {
    let valor = $('#distribuidor_id').val();
    let modelo = $('#modelos_id_tmp').val();
    $('#modelos_id')
      .find('option')
      .remove()
      .end();
    $.ajax(
    {
      url: '/get_options/' + valor + '/' + modelo,
      type: 'get',
      dataType: 'html',
      success: function(data) {
        $('#modelos_id').append(data);
      }
    });
   }
   $(document).ready(function () {
      get_id();
   });
   "]))

(comment
  (get-modelos-id))
