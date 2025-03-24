FROM hseeberger/scala-sbt:8u312_1.8.2_2.13.12

WORKDIR /app
COPY . .

RUN sbt compile

CMD sbt run