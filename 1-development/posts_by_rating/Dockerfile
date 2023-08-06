# Imagem oficial do Ruby 3 como base
FROM ruby:3

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Define variáveis de ambiente para evitar warnings do Rails
ENV LANG=C.UTF-8 \
    LC_ALL=C.UTF-8

# Instala dependências do sistema necessárias para compilar algumas gems nativas e o PostgreSQL client
RUN apt-get update -qq \
    && apt-get install -y nodejs \
                          postgresql-client \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Instala o Rails versão 7
RUN gem install rails -v 7 --no-document

# Copia o Gemfile e o Gemfile.lock para o contêiner
COPY Gemfile Gemfile.lock ./

# Instala as gems definidas no Gemfile
RUN bundle install

# Copia o restante do código da aplicação para o contêiner
COPY . .

# Expõe a porta 3000, que é a porta padrão do servidor de desenvolvimento do Rails
EXPOSE 3000