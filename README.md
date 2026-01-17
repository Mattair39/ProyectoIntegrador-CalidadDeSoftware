# Student Manager - Sistema de Gestión de Estudiantes

## Descripción
Sistema básico de gestión de estudiantes y calificaciones desarrollado en Java.

## Estructura del Proyecto
```
ProyectoIntegrador-CalidadDeSoftware/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── studentmanager/
│   │               └── StudentManager.java
│   └── test/
│       └── java/
│           └── com/
│               └── studentmanager/
│                   └── StudentManagerTest.java
├── pom.xml
└── README.md
```

## Requisitos
- Java 11 o superior
- Maven 3.6 o superior

## Compilación
Para compilar el proyecto:
```bash
mvn clean compile
```

## Ejecución
Para ejecutar la aplicación:
```bash
mvn exec:java -Dexec.mainClass="com.studentmanager.StudentManager"
```

O compilar y ejecutar manualmente:
```bash
mvn clean package
java -cp target/student-manager-1.0-SNAPSHOT.jar com.studentmanager.StudentManager
```

## Pruebas
Para ejecutar las pruebas unitarias:
```bash
mvn test
```

## Funcionalidades Actuales
- ✅ Agregar estudiantes con sus calificaciones
- ✅ Listar estudiantes y calificaciones

## Próximas Mejoras
- Eliminar estudiantes
- Actualizar calificaciones
- Calcular promedios
- Persistencia de datos
- Validaciones de entrada
