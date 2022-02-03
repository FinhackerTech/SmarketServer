package dev.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 企业贷款整体比例情况
 */
@Embeddable
@Data
@NoArgsConstructor
public class LoanRate {

    private Float loanRate;
    //短期借款占总资产比例（%）
    private Float STB2TA;
    //长期借款占资产比例（%）
    private Float LTB2TA;

}
