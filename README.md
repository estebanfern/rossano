
# Rossano Web Admin

Gestión de inventario, pedidos, facturas, etc.


[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)



## Autor

- [@estebanfern](https://www.github.com/estebanfern)


## Variables de Entorno

## Despliegue

Para desplegar este proyecto puedes generar la imagen y ejecutar

```bash
mvn clean package -Ddb_url="jdbc:postgresql://localhost:5432/rossano" -Ddb_username="postgres" -Ddb_password="postgres" -Demail_password="emailPassword" -Demail_address="example@mail.com"
```

```bash
docker build -t rossanoadmin .
```

```bash
docker run -e db_url=jdbc:postgresql://host.docker.internal:5432/rossano -e db_username=postgres -e db_password=postgres -e email_password=emailPassword -e email_address=example@mail.com -p 8089:8089 rossanoadmin
```

O bien, crear el archivo docker-compose como ejemplo:
```yaml
version: "3.9"
services:
  rossanoadmin:
    container_name: rossanoadmin
    image: estebanfern/rossanoadmin:1.0.0
    logging:
      driver: "json-file"
      options:
        max-size: "500m"
        max-file: "2"
    ports:
      - "8089:8089"
    env_file:
      - rossano.env
```
Y el archivo de configuración ```.env``` de las siguientes maneras:
- Utilizando las variables de entorno dentro del ```application.yaml```
```
db_url=jdbc:postgresql://host.docker.internal:5432/rossano
db_username=postgres
db_password=postgres
email_address=example@mail.com
email_password=emailpassword
```
- Sobreescribiendo las configuraciones del ```application.yaml```
```
spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/rossano
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.mail.username=example@mail.com
spring.mail.password=emailpassword
```
