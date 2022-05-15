package com.example.flightreservation.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_wallets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wallet extends BaseEntity {

    @Column(name = "funds", columnDefinition = "NUMERIC DEFAULT 0")
    BigDecimal funds;

}
