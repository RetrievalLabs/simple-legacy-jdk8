# Simple Legacy JDK 8

A single-module Spring MVC loan workflow inspired by `legacy-jdk-8`. It targets Java 8, uses Spring Boot 1.5 / Spring 4.3 and the legacy `javax.servlet` and `javax.validation` APIs. Applications are held in memory, so they reset when the process restarts.

```sh
mvn spring-boot:run
curl -X POST http://localhost:8080/api/loans -H 'Content-Type: application/json' \\
  -d '{"customerId":42,"amount":300000,"tenureMonths":24,"annualIncome":900000,"existingMonthlyDebt":5000,"purpose":"Home renovation"}'
curl http://localhost:8080/api/loans/1
curl -X POST http://localhost:8080/api/loans/1/submit
```

Create validates request fields and calculates EMI at a fixed 10.5% annual rate. Submit checks that monthly debt plus EMI stays within 45% of monthly income, then flags large or borderline loans for manual review. Tenure is limited to 6–60 months.
