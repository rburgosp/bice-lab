FROM node:13.12.0-alpine

RUN mkdir /app
COPY package.json ./
COPY package-lock.json ./
COPY . /app
WORKDIR /app
RUN rm -rf node_modules
RUN npm install react-scripts@3.4.1 -g --silent
RUN npm install --no-optional
RUN npm i
CMD ["npm", "start"]
#ENTRYPOINT ["sh", "-c", "npm run build && npm run start"]
