# base image
FROM node:20.5.1

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@18.0.6

# add app
COPY . /app

EXPOSE 4200

# start app
ENTRYPOINT ["ng","serve","--host", "0.0.0.0"]
