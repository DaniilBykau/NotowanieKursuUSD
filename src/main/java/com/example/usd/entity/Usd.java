package com.example.usd.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Data
@Component
public class Usd {
    private String table;

    private String currency;

    private String code;

    private List<Rate> rates;
}
