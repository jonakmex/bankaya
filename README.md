# Bankaya projecto Pokemon API - Jonathan Josue Gomez Bustamante

Proyecto Spring boot que expone un endpoint SOAP con las siguientes operaciones:
* FindAbilities
* FindBaseExperience
* FindHeldItems
* FindID
* FindName
* FindLocationAreaEncounters

Consume la informacion provista por la api https://pokeapi.co/

## Construccion con Gradle

Clonar el proyecto con el comando

```bash
git clone https://github.com/jonakmex/bankaya.git
```

Compilacion y Tests
1. Proyecto Core

```bash
cd pokemon-core
gradlew clean build publishToMavenLocal
```

2. Proyecto SOAP

```bash
cd pokemon-soap
gradlew clean genJaxb build 
```

3. Proyecto Eureka

```bash
cd naming-server
gradlew clean build
```
4. Proyecto API Gateway

```bash
cd api-gateway
gradlew clean build
```

## Launch manual con Gradle
1. Iniciar Servidor Eureka
```bash
cd naming-server
gradlew bootRun
```

2. Iniciar Servidor API Gateway
```bash
cd api-gateway
gradlew bootRun
```

3. Iniciar Pokemon SOAP
```bash
cd pokemon-soap
gradlew bootRun
```
## Launch con Docker compose
```bash
docker-compose up -d
```


## Usage
Proyecto [SOAPUI](https://github.com/jonakmex/bankaya/blob/main/pokemon-soapui-project.xml)

Enpoint:
http://localhost:8765/pokemon-ws/pokemon-ws

Para acceder a la inspeccion de requests guardados en base de datos:
http://localhost:9411

Dar click en el boton de busqueda para cargar las ultimas entradas.
