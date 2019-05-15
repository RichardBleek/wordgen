package com.rbleek.wordgen;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WordRepository extends ReactiveMongoRepository<Word, String> {


}
