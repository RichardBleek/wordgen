package com.rbleek.wordgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class WordService {

    private Logger log = LoggerFactory.getLogger(WordService.class);
    private List<Word> words = new ArrayList<>();

    WordService() throws IOException {
        Resource resource = new ClassPathResource("thing-explainer-words-list.txt");
        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
        reader.lines().map(Word::new).forEach(words::add);
        log.info("Created WordService with {} words.", words.size());
    }

    public Flux<Word> stream() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Word> wordStream = Flux.fromStream(Stream.generate(this::randomWord));

        return Flux.zip(interval, wordStream).map(Tuple2::getT2);
    }

    public Mono<Word> one() {
        return Mono.just(randomWord());
    }

    public Flux<Word> all() {
        return Flux.fromStream(words.stream());
    }

    private Word randomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}

