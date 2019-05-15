### wordgen

A reactive word generator using spring boot & spring web flux. Build after being inspired by Spring One@Amsterdam 2019.

This takes the [thing explainer](https://xkcd.com/thing-explainer/) [list of words](https://github.com/RichardBleek/wordgen/blob/master/wordgen/src/main/resources/thing-explainer-words-list.txt) and generated a word from the list every one second.

### commands
Start mongodb
```
docker run --name mongo -p 27017:27017 -d mongo
```

producer spring boot app
```
cd wordgen
mvn spring-boot:run
```

consumer spring boot app
```
cd wordgen-client
mvn spring-boot:run
```

### test endpoints
```
curl http://localhost:8080/words/
curl http://localhost:8080/words/one
curl http://localhost:8080/words/generate
```
