# Reward

<h1>포로젝트 설정</h1>

<h2>데이터베이스</h2>
<ol>먼저 로컬에 docker가 설치되어 있어야 한다.</ol>
<ol>docker가 설치되어 있다면 command 창에서 프로젝트 루트에서 docker-compose 실행</ol>

```bash 
docker-compose up -d
```

<h2>restdoc 경로</h2>

```
http://localhost:8080/docs/v1_api.html
```

<h2>빌드방법</h2>
<o1>command 창에서 아래 명령어 실행</o1>

```bash
./gradlew build 
```

```bash
cd build/libs
```

```bash
java -jar FirstServed.jar
```