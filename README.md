# AndroidNotifications
Ejemplo de uso de las notificaciones en Android con Java.
Además se hace uso de la libreria Butterknife para enlazar los View del layout. 
Se dividio el ejemplo en varias ramas porque son varios los topicos que se abordan. 
- http://jakewharton.github.io/butterknife/
- https://github.com/JakeWharton/butterknife

### Master

En la rama master se ejemplifica el uso basico de notificaciones 

### PendingIntent 

En la rama PendingIntent se agrega un comportamiento sobre la notificacion. Al pinchar en ella envia a un segundo activity 
que muestra detalles. 

### Actions

En la rama Actions se hace lo mismo que en PendingIntent pero mediante un boton (Action) en la notificacion,
ya no pinchando en ella. 

### GroupNotifications

En esta rama, ademas de lo anterior, se agrupan notificaciones para que al lanzar distintas notificaciones no aparezcan sueltas,sino mas bien agrupadas todas en una Notificacion Summary, que es un comportamiento estandar en las aplicaciones. 
