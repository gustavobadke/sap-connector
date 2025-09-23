FROM eclipse-temurin:17-jdk

WORKDIR /app

# Instalar dependências nativas
RUN apt-get update && \
    apt-get install -y libuuid1 && \
    rm -rf /var/lib/apt/lists/*

# Copia os arquivos da aplicação
COPY src/ ./src/
COPY libs/arm/sapjco3.jar ./libs/sapjco3.jar
COPY libs/arm/libsapjco3.so /usr/lib/
COPY SAP_CONFIG.jcoDestination ./SAP_CONFIG.jcoDestination

ENV LD_LIBRARY_PATH=/usr/lib

# Compila o código Java
RUN mkdir -p classes && \
    javac -cp libs/sapjco3.jar -d classes src/br/com/gnexum/SapConnection.java && \
    jar --create --file sapconn.jar -C classes .

# Executa a aplicação
CMD ["java", "-cp", "sapconn.jar:libs/sapjco3.jar", "br.com.gnexum.SapConnection"]