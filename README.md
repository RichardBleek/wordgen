### wordgen

A reactive word generator using spring boot & spring web flux. Build after being inspired by Spring One.

This takes the [thing explainer](https://xkcd.com/thing-explainer/) [list of words]() and generated a random word from the list every one second.

### commands
terminal 1
```
cd wordgen
mvn spring-boot:run
```

terminal 2
```
cd wordgen-client
mvn spring-boot:run
```

### endpoints
```
curl http://localhost:8080/words/
curl http://localhost:8080/words/one
curl http://localhost:8080/words/generate
```