package com.example.usd.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Data
@Component
public class Rate {
    String no;

    String effectiveDate;

    String bid;

    String ask;
}

