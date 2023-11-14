package com.rec.msdebitcard.repository;

import com.rec.msdebitcard.entity.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, Long> {

    Flux<DebitCard> findByClientDni(String dni);

    Mono<Void> deleteByCardNumber(String aLong);

}
