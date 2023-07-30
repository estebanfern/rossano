
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
