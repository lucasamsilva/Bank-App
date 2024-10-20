# Etapa 1: Construir a aplicação
FROM maven:3.8.5-openjdk-17 AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml e as dependências para o contêiner
COPY pom.xml .
COPY src ./src

# Baixar as dependências e compilar a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação
FROM openjdk:17-jdk-alpine

# Diretório de trabalho para o contêiner de runtime
WORKDIR /app

# Copie o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta onde a aplicação Spring irá rodar
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]