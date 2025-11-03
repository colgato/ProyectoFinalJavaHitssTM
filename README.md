Este proyecto es un manejador de tareas usando Spring, Spring security y tokens de validación para cada tipo de usuario
en el archivo 

EvidenciaProyectoFinal.pdf 

se encuentran las imagenes sobre como funciona cada endpoint los requerimientos originales fueron los siguientes:

Objetivo del Proyecto:
Crear una aplicación CRUD para gestionar tareas personales, aprendiendo los fundamentos de
Spring Boot, como controladores, servicios, repositorios, entidades, y uso de base de datos.
Funcionalidades Básicas:
1. Crear una tarea
2. Listar todas las tareas
3. Actualizar una tarea
4. Eliminar una tarea
Entidades principales: Task, User, Role
Endpoints REST (TaskController):
MÉTODO RUTA DESCRIPCIÓN
GET /tasks Listar todas las tareas
GET /tasks/{id} Obtener una tarea específica
POST /tasks Crear una nueva tarea
PUT /tasks/{id} Actualizar una tarea
DELETE /tasks/{id} Eliminar una tarea
Punto a tomarse en cuenta:
• Uso de anotaciones: @RestController, @Service, @Repository, @Entity, etc.
• Realizar validaciones
• CRUD con Spring Data JPA
• Uso de base de datos MySQL
• Uso de Postman para probar APIs (opcional)
• Manejo de errores básicos
• Uso de Spring Security y jwt (agregar usuarios y roles)
• Tener al menos dos usuarios (USER, ADMIN), el usuario USER solo puede crear tareas y
visualizarlas, y el usuario ADMIN puede realizar todas las operaciones CRUD.
• Documentar el API con Swagger
• Agregar imágenes de pruebas en el repositorio github

