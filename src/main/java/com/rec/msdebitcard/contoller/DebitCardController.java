package com.rec.msdebitcard.contoller;

import com.rec.library.Client;
import com.rec.msdebitcard.entity.DebitCard;
import com.rec.msdebitcard.entity.ResponseApi;
import com.rec.msdebitcard.service.DebitCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/debitcard")
public class DebitCardController {
    @Autowired
    private DebitCardService debitCardService;

    @GetMapping("/all")
    public Flux<DebitCard> getDebitCards() {
        return debitCardService.findAll();
    }

    @GetMapping("/find/{dni}")
    public Flux<DebitCard> getDebitCardById(@PathVariable String dni) {
        return debitCardService.findDebitCardsByDni(dni);
    }

    @PostMapping("/create")
    public Mono<ResponseApi> createDebitCard(@Validated @RequestBody DebitCard debitCard) {
        return debitCardService.createDebitCard(debitCard)
                .map(r -> ResponseApi.builder()
                        .message("DebitCard " + r.getCardNumber() + " was successful created")
                        .build())
                .doOnSuccess(r -> log.info("Successful {}", r.getMessage()))
                .switchIfEmpty(Mono.just(ResponseApi.builder()
                                .message("Client Dni did not found")
                                .build()))
                .onErrorResume(r -> Mono.just(ResponseApi.builder()
                                    .message("Error creating DebitCard")
                                    .build()))
                .doOnError(ex -> log.error("Error Produced {}", ex.toString()));
    }

}
