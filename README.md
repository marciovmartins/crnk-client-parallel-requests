To run the project it's necessary a mysql running locally on port 3306 and a `test` database.

You can change the `server/src/main/resources/application.yml` to point to another mysql configuration.

It's necessary to run the server before execute the test.

On terminal:
`mvn -pl server clean spring-boot:run`

In a second terminal:
`mvn clean test` 