# 📈 Calculadora de Inversiones - Spring Boot + Thymeleaf

Este proyecto es un sistema web desarrollado como parte del laboratorio de la semana 2 de la asignatura **Desarrollo de Software II**. La aplicación permite simular inversiones con interés compuesto, asignar categorías al cliente según el capital inicial, y almacenar cada simulación para análisis posterior.

---

## 🧠 Funcionalidades

✅ Registrar clientes con datos personales  
✅ Realizar simulaciones de inversión (fórmula de interés compuesto)  
✅ Calcular monto final e interés ganado  
✅ Clasificar la inversión por categoría  
✅ Mostrar historial de simulaciones en una tabla  
✅ Eliminar simulaciones registradas  
✅ Validación de datos en formularios  

---

## 🛠️ Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Thymeleaf  
- Bootstrap (CSS básico)  
- Maven  

---

## 🗃️ Estructura del sistema

### 🔹 Modelos

- `ClienteModelo`: contiene `nombres`, `cédula`, `email`, `celular`.
- `InversionModelo`: contiene `capitalInicial`, `numeroPeriodosPorAnio`, `tiempoAnos`, `tasaInteres`, `montoFinal`, `interesGanado`, `categoria`, `fkCliente`.

### 🔹 Fórmula utilizada

> A = P × (1 + r / n) ^ (n × t)  
> Donde:  
> - `P` = capital inicial  
> - `r` = tasa de interés anual (dependiendo de la periodicidad)  
> - `n` = número de periodos por año  
> - `t` = tiempo en años  
> - `A` = monto acumulado

### 🔹 Reglas de categoría

- **Categoría 3**: capital entre 100 y 500 USD y n = 12  
- **Categoría 2**: capital entre 501 y 1000 USD y n = 12  
- **Categoría 1**: capital mayor a 1000 USD y n = 12  
- **Categoría 0**: no clasificado

---

## 🧪 Validaciones importantes

- `capitalInicial` debe ser > 100 USD  
- Email debe tener formato válido  
- Celular debe tener 10 dígitos  
- `tiempoAnos` mínimo 1  
- Se debe seleccionar cliente para simular  

---

## 🚀 Cómo ejecutar

1. Clonar el repositorio  
2. Crear una base de datos MySQL llamada `inversiones`  
3. Configurar `application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inversiones
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

4. Ejecutar la aplicación con:

```bash
mvn spring-boot:run
```

5. Acceder a:

```
http://localhost:8080/inversiones/inversionform
```

---

## 📸 Interfaz

- Sidebar para navegar entre formularios y tablas  
- Formulario claro y validado para simulaciones  
- Tabla con resultados bien formateados (decimales, porcentaje, moneda)

---

## 👨‍🎓 Autores

- Paul Roche  
- Cristian Sinche  
- Henry Cevallos  

**Materia:** Desarrollo de Software II  
**Docente:** Mg. Carlos Salazar  
**Universidad Tecnológica Israel**
