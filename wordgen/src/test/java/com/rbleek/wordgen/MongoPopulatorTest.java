package com.rbleek.wordgen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MongoPopulatorTest {

    @Mock
    WordRepository wordRepository;

    @Before
    public void before() {
        wordRepository = mock(WordRepository.class);
    }

    @Test
    public void testWords() {

        MongoPopulator populator = new MongoPopulator(wordRepository);
        Flux<Word> words = populator.words();
        assertNotEquals(0L, words.count().blockOptional());
    }

    @Test
    public void testPopulate() {

        MongoPopulator populator = new MongoPopulator(wordRepository);
        when(wordRepository.deleteAll()).thenReturn(Mono.empty()); //Nothing to delete
        when(wordRepository.insert(any(Publisher.class))).thenReturn(populator.words());

        populator.populate();
    }

}