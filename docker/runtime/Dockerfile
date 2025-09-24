FROM eclipse-temurin:17-jdk

WORKDIR /app

# Instalar dependências nativas
RUN apt-get update && \
    apt-get install -y libuuid1 && \
    rm -rf /var/lib/apt/lists/*

# Copia os arquivos da aplicação
COPY . .

# Cria diretório para bibliotecas
RUN mkdir -p /usr/lib/jvm/lib/amd64

# Copia a biblioteca nativa do SAP JCo para o diretório do sistema
COPY libs/x86/libsapjco3.so /usr/lib/jvm/lib/amd64/

# Define permissões para a biblioteca
RUN chmod 755 /usr/lib/jvm/lib/amd64/libsapjco3.so

# Variável de ambiente para localização das bibliotecas nativas
ENV LD_LIBRARY_PATH=/usr/lib/jvm/lib/amd64:$LD_LIBRARY_PATH
