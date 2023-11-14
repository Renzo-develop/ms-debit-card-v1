package com.rec.msdebitcard.service;

import com.rec.library.Client;
import com.rec.msdebitcard.entity.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {

    public Flux<DebitCard> findAll();

    public Flux<DebitCard> findDebitCardsByDni(String dni);

    public Mono<DebitCard> createDebitCard(DebitCard debitCard);

    public Mono<DebitCard> updateDebitCard(DebitCard debitCard);

    public Mono<Void> deleteDebitCard(String cardNumber);

}
