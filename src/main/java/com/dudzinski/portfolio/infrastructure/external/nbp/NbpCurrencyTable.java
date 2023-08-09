package com.dudzinski.portfolio.infrastructure.external.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NbpCurrencyTable implements Serializable {

    private String table;

    private String no;

    private String effectiveDate;

    private List<NbpCurrency> rates;
}
