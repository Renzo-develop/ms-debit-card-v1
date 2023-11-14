package com.rec.msdebitcard.entity;

import com.rec.library.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "debitcard")
@Data
public class DebitCard {
    @Field
    private Long debitCardId;

    @Field
    @NotNull
    @Pattern(regexp = "[0-9]{16}$")
    private String cardNumber;

    @Field
    @NotNull
    private Client client;

    @Field
    @Pattern(regexp = "(0[1-9]|10|11|12)/[0-9]{2}$")
    private String expirationDate;

    @Field
    private Double balance;

}
