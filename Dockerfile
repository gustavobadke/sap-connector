FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia tudo
COPY . .

# Compila o projeto
RUN mvn package

# Segundo estágio (para um container mais limpo)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR da build anterior
COPY --from=build /app/target/*.jar app.jar

# Copia a biblioteca nativa e JCo
COPY libs/arm/sapjco3.jar ./libs/
COPY libs/arm/libsapjco3.so /usr/lib/

ENV LD_LIBRARY_PATH=/usr/lib

# Executa o JAR
CMD ["java", "-cp", "app.jar:libs/sapjco3.jar", "br.com.gnexum.SapConnection"]