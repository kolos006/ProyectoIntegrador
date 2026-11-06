# PrestaKey - Sistema de Gestión y Préstamo de Llaves U-Tad

**PrestaKey** es una aplicación Android desarrollada con **Kotlin** y **Jetpack Compose** diseñada para la gestión, reserva y lectura NFC de llaves de aulas en el campus universitario de **U-Tad**.

---

## 📐 Arquitectura del Proyecto

El proyecto sigue la arquitectura recomendada por Android con el patrón **MVVM (Model-View-ViewModel)** y **Unidirectional Data Flow (UDF)** utilizando `StateFlow`:

```
com.example.prestakey/
├── data/
│   ├── model/             # Modelos de datos de la aplicación
│   │   ├── KeyStatus.kt     # Enum de estados (AVAILABLE, BORROWED, IN_REVIEW)
│   │   ├── KeyItem.kt       # Modelo de llave y aula
│   │   ├── TeacherUser.kt   # Modelo de usuario docente (@u-tad.com)
│   │   ├── Loan.kt          # Registro de préstamo activo
│   │   ├── IncidentType.kt  # Enum de tipo de incidencia (DAMAGE, LOSS, OTHER)
│   │   └── Incident.kt      # Registro de reporte de incidencia
│   └── repository/        # Repositorio de datos y fuente Mock
│       └── PrestaKeyRepository.kt
├── ui/
│   ├── components/        # Componentes UI reutilizables
│   │   ├── UtadTopBar.kt    # Barra superior con identidad U-Tad
│   │   ├── BottomNavBar.kt  # Barra de navegación inferior
│   │   └── StatusBadge.kt   # Indicador de estado de llave/préstamo
│   ├── navigation/        # Navegación Compose
│   │   ├── ScreenRoutes.kt  # Definición de rutas
│   │   └── AppNavigation.kt # NavHost principal
│   └── screens/           # Pantallas de la aplicación
│       ├── auth/          # 1. Autenticación (Login)
│       ├── keys/          # 2. Panel de llaves y catálogo
│       ├── loans/         # 3. Préstamos activos y devolución
│       ├── nfc/           # 4. Lectura NFC y búsqueda
│       └── incidents/     # 5. Reporte de incidencias
└── MainActivity.kt
```

---

## 📱 Pantallas de la Aplicación

1. **Autenticación (`auth`):**
   - Formulario de acceso para docentes con validación del correo corporativo `@u-tad.com`.
   - Opción para recordar sesión.

2. **Panel de Llaves (`keys`):**
   - Resumen de contadores globales (Disponibles, Prestadas, En revisión).
   - Buscador por aula/código y filtro interactivo por estado.
   - Rejilla/Grid de tarjetas de llaves (Aulas A01, B12, P02, BIB, PRO, C01, etc.).

3. **Préstamos Activos (`loans`):**
   - Listado de llaves actualmente prestadas con detalles de docente, asignatura, horario y límite de devolución.
   - Acción de devolución rápida.

4. **Búsqueda y Lectura NFC (`nfc`):**
   - Escaneo simulado de etiquetas NFC (ej. `Tag NFC-B12`).
   - Vista previa con detalles del aula detectada.

5. **Reporte de Incidencias (`incidents`):**
   - Formulario para notificar desperfectos, pérdidas u otros problemas en las llaves o cerraduras.

---

## 🛠️ Guía de Desarrollo para Colaboradores

- Cada archivo `.kt` incluye un bloque de comentarios con los requerimientos exactos a implementar.
- Mantener las clases de modelo en `data/model` inmutables (`data class`).
- Utilizar `StateFlow` dentro de `PrestaKeyRepository` para que los componentes Compose reaccionen automáticamente a las actualizaciones de estado.
- Usar Material 3 para los componentes y mantener la paleta cromática basada en los colores corporativos de U-Tad.

---

## 🚀 Requisitos de Compilación

- **Android Studio:** Ladybug / Jellyfish o superior
- **JDK:** 11 o superior
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 37
- **Lenguaje:** Kotlin
- **UI Toolkit:** Jetpack Compose (Material 3)
