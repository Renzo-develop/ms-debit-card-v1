package com.rec.msdebitcard.service.impl;

import com.rec.library.Client;
import com.rec.msdebitcard.entity.DebitCard;
import com.rec.msdebitcard.parameter.DebitCardParameters;
import com.rec.msdebitcard.repository.DebitCardRepository;
import com.rec.msdebitcard.service.DebitCardService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@PropertySource("classpath:application.yml")
public class DebitCardServiceImpl implements DebitCardService {
    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private DebitCardParameters parameters;

    private WebClient clientWeb;

    @PostConstruct
    public void init() throws Exception {
        clientWeb = WebClient.create(parameters.getClientUrl());
    }

    @Override
    public Flux<DebitCard> findAll() {
        return debitCardRepository.findAll();
    }

    @Override
    public Flux<DebitCard> findDebitCardsByDni(String dni) {
        return debitCardRepository.findByClientDni(dni);
    }

    @Override
    public Mono<DebitCard> createDebitCard(DebitCard debitCard) {

        return clientWeb.get().uri("/findByDni/{dni}", debitCard.getClient().getDni())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class)
                .flatMap(client -> {
                    debitCard.setClient(client);
                    debitCard.setBalance(0.0);
                    return debitCardRepository.insert(debitCard);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<DebitCard> updateDebitCard(DebitCard debitCard) {
        return debitCardRepository.insert(debitCard);
    }

    @Override
    public Mono<Void> deleteDebitCard(String cardNumber) {
        return debitCardRepository.deleteByCardNumber(cardNumber);
    }

}
