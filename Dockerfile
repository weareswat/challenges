FROM node:12-alpine

EXPOSE 8080 5399

WORKDIR /app

CMD ["node", "build/index.js"]
