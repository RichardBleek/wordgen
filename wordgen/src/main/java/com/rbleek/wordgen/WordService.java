package com.rbleek.wordgen;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Service
@Slf4j
public class WordService {

    private WordRepository wordRepository;

    WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * @return a Flux that will produce a word every 1s
     */
    public Flux<Word> stream() {

        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> this.one());
    }

    /**
     * @return a random Word from the mongo db
     */
    public Mono<Word> one() {
        Mono<String> randomId = count()
                .map(c -> ThreadLocalRandom.current().nextLong(c))
                .map(String::valueOf);
        return wordRepository.findById(randomId);
    }

    /**
     * @return amount of documents in the database
     */
    public Mono<Long> count() {
        return wordRepository.count();
    }

    public Flux<Word> all() {
        return wordRepository.findAll();
    }
}

