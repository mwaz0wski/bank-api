FROM ghcr.io/graalvm/graalvm-community:21 AS build

WORKDIR /usr/src/app

COPY . .

RUN ./mvnw -Pnative native:compile -DskipTests

FROM ubuntu:24.04
WORKDIR /app
COPY --from=build /usr/src/app/target/bank-api /app/bank-api
CMD ["/app/bank-api"]
