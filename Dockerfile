# Dockerfile development version
FROM ruby:latest AS source-development

# Install yarn
# RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg -o /root/yarn-pubkey.gpg && apt-key add /root/yarn-pubkey.gpg
# RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" > /etc/apt/sources.list.d/yarn.list
# RUN apt-get update && apt-get install -y --no-install-recommends nodejs yarn

ARG UID
ARG GID

# Default directory
ENV INSTALL_PATH /opt/app
RUN mkdir -p $INSTALL_PATH

# Cache directory
RUN mkdir -p /.cache
RUN chown $UID:$GID /.cache

# Install gems
WORKDIR $INSTALL_PATH
COPY source/ .
# RUN rm -rf node_modules
RUN rm -rf vendor
RUN gem install rails bundler
RUN bundle install
# RUN yarn install

# Start server
CMD bundle exec unicorn -c config/unicorn.rb
