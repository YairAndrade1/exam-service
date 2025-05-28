# exam-service

**Microservicio de Exámenes**

Este proyecto expone un conjunto de endpoints REST para gestionar y consultar registros de exámenes clínicos, filtrarlos por fecha y anomalías, y agruparlos por tipo.

---

## 🔧 Requisitos

* Java 17+
* Maven 3.6+
* PostgreSQL (o la BD relacional configurada)
* Git (para clonar el repositorio)

---

## 🚀 Instalación y ejecución

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/YairAndrade1/exam-service.git
   cd exam-service
   ```

2. **Configurar la conexión a la base de datos**
   Edita `src/main/resources/application.properties` con tus credenciales:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/examdb
   spring.datasource.username=exam_user
   spring.datasource.password=exam_pass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
   ```

3. **Compilar y ejecutar**

   ```bash
   mvn clean package -DskipTests
   java -jar target/exam-service-0.0.1-SNAPSHOT.jar
   ```

   El servicio escuchará en el puerto **8080** por defecto.

---

## 📚 Endpoints disponibles

| Método | Ruta                                                    | Descripción                                                                        |
| ------ | ------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| GET    | `/examen/{tipo}`                                        | Obtiene todos los exámenes con el campo `tipo` igual al parámetro.                 |
| GET    | `/examen/recent?from={ISO_DATE_TIME}`                   | Obtiene exámenes creados después de la fecha `from`.                               |
| GET    | `/examen/recent-anomalies?from={ISO_DATE_TIME}`         | Obtiene exámenes con `resultado` que contiene "anomaly" creados después de `from`. |
| GET    | `/examen/recent-anomalies/grouped?from={ISO_DATE_TIME}` | Devuelve un conteo de anomalías agrupadas por tipo desde la fecha `from`.          |

> **Formato de fecha**: utiliza ISO 8601, por ejemplo `2025-04-01T00:00:00`.

Ejemplo de llamada:

```bash
curl "http://localhost:8080/examen/recent-anomalies/grouped?from=2025-04-01T00:00:00"
```

Respuesta esperada:

```json
{
  "EEG": 123,
  "miRNA": 86,
  "MRI": 42
}
```

---

## 💾 Modelo de datos

Entidad **Examen**:

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="examenes")
public class Examen {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tipo;
  private String resultado;

  @CreatedDate
  @Column(name="creado_en", nullable=false, updatable=false)
  private LocalDateTime creadoEn;
  // getters y setters...
}
```

* **id**: identificador único.
* **tipo**: tipo de examen (EEG, miRNA, MRI, etc.).
* **resultado**: texto con resultado, se considera anomalía si contiene la palabra "anomaly".
* **creadoEn**: fecha/hora de creación automática.

---

## ⚙️ Configuración adicional

* **Auditing**: habilitado en `ExamenServiceApplication` con `@EnableJpaAuditing`.
* **Timezone**: comprueba que tu base de datos y JVM compartan zona horaria o utiliza UTC.

---

## 🧪 Pruebas

* Pruebas unitarias con **Spring Boot Starter Test** y **Reactor Test** (si migras controladores a WebFlux).
* Para integraciones, usa **Postman** o **curl**.

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE).

---

© 2025 CeroBugsTeam
