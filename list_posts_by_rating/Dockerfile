FROM ruby:3.1.2-alpine


RUN apk --update add build-base tzdata

WORKDIR list_posts_by_rating/app

RUN gem install bundler:2.3.14

COPY Gemfile* ./

RUN bundle install

ADD . /list_posts_by_rating/app

ARG DEFAULT_PORT 3000

EXPOSE ${DEFAULT_PORT}

CMD [ "bundle","exec", "puma", "config.ru"] # CMD ["rails","server"] # you can also write like this.