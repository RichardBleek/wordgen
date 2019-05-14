### wordgen

A reactive word generator using spring boot & spring web flux. Build after being inspired by Spring One.

This takes the [thing explainer](https://xkcd.com/thing-explainer/) [list of words](https://github.com/RichardBleek/wordgen/blob/master/wordgen/src/main/resources/thing-explainer-words-list.txt) and generated a random word from the list every one second.

This version does not use mongo as a datastore. How to use mongo as a datastore in web-flux check: https://github.com/RichardBleek/flux-flix-service
I might add mongo support here later aswell.

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
