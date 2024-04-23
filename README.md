# commerce-submit

## 프로젝트 기술 스택

1. Java 8
2. Spring Boot 2.7.18
3. Gradle 8.6
4. Spring Data JPA
5. H2 2.1.214 (lastest) (local & test)

## 스웨거 접속 방법

- http://localhost:8080/swagger-ui/index.html

## 프로젝트 실행 방법(개발툴)

1. 프로젝트를 클론합니다.
2. 프로젝트를 빌드합니다.
3. 프로젝트를 실행합니다.

```shell
git clone  "https://github.com/qkrtkdwns3410/commerce-submit.git"
./gradlew build
./gradlew bootRun
```

## .http client 사용법

1. 프로젝트 루트에 존재하는 api-request-test.http 파일을 엽니다.
2. http-client.env.json 을 적용해야하기에, intellij 에서 local 환경~~으로 설정해줍니다.
3. 원하는 api를 선택하고, Intellij에서 해당 api를 실행합니다.~~