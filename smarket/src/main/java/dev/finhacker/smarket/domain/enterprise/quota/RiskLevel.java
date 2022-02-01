package dev.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 风险水平
 */
@Embeddable
@Data
@NoArgsConstructor
public class RiskLevel {

    //营运杠杆
    private Float OL;
    //综合杠杆
    private Float DTL;

}
