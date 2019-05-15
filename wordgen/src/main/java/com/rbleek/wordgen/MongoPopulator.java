package com.rbleek.wordgen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MongoPopulator {

    public MongoPopulator(WordRepository wordRepository) {
        wordRepository.deleteAll().subscribe(null, null, () -> {
            try {
                AtomicInteger counter = new AtomicInteger(0);
                Resource resource = new ClassPathResource("thing-explainer-words-list.txt");
                BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
                Flux<Word> wordFlux = Flux.fromStream(reader.lines()
                        .map(s -> new Word(String.valueOf(counter.getAndIncrement()), s)));
                wordRepository.insert(wordFlux).subscribe(pword -> log.info("inserted {}", pword));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}
