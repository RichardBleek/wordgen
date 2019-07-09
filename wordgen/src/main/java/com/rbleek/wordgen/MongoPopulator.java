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

    private WordRepository wordRepository;

    public MongoPopulator(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

     void populate() {
        wordRepository.deleteAll().subscribe(null, null, () -> {
            Flux<Word> wordFlux = words();
            wordRepository.insert(wordFlux).subscribe(pword -> log.debug("inserted {}", pword));
        });
    }

     Flux<Word> words() {
        try {
            AtomicInteger counter = new AtomicInteger(0);
            Resource resource = new ClassPathResource("thing-explainer-words-list.txt");
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
            return Flux.fromStream(reader.lines()
                    .map(s -> new Word(String.valueOf(counter.getAndIncrement()), s)));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
