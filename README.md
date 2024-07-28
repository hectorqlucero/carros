# carros
Ejemplo de dos selects dependientes - distribuidor y modelos

## Requisitos antes de Instalar
1. Java sdk instalado
2. MySQL instalado y configurado con una contraseña
3. Leiningen instalado. https://leiningen.org
4. Tu editor de preferencia que este configurado para codificar en clojure.
5. Si usas vim puedes bajar la configuracion para clojure.
    1. Dotfiles para vim aqui: https://github.com/hectorqlucero/vim
    1. Necesitas instalar **vifm**
6. Recomiendo dbeaver-ce para administrar la base de datos.
    1. Lo puedes bajar aqui: https://dbeaver.io

## Instalación
1. En una terminal: git clone https://github.com/hectorqlucero/carros.git
2. Configurar `project.clj`
3. Renombrar: `/resources/private/config_example.clj` a `/resources/private/config.clj`
4. Configurar: `/resources/private/config.clj`
5. Crear una base de datos con el mismo nombre de la configuracion **5.** Usando dbeaver o tu cliente de preferencia.
6. En el folder donde esta el codigo abrir una terminal y ejecutar: **lein with-profile dev run**
7. Abrir otra terminal en el folder donde esta el codigo y ejecutar: **lein repl**
8. Abrir otra terminal en el folder donde esta el codigo y ejecutar:
    1. **lein migrate**:
        1. Crea tabla de **users**
        2. Crea tabla de distribuidor
        3. Crea tabla de modelos
        4. Crea tabla de clientes
    2. **lein database** - Esto creara los records siguientes:
        1. **Usuario**: user@gmail.com    **contraseña**: user
        2. **Usuario**: admin@gmail.com   **contraseña**: admin
        3. **Usuario**: sistema@gmail.com **contraseña**: sistema
        4. dos records en la tabla distibuidor
        5. seis records en la tabla modelos

## Usarlo cuando esta instalado
**En el browser de tu gusto**: http://localhost:3000 para correr la pagina
Crear clientes para ver como funcionan los selects dependientes. distribuidor -> modelos
