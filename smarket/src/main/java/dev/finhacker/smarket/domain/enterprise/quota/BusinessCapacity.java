package dev.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 经营能力
 */
@Embeddable
@Data
@NoArgsConstructor
public class BusinessCapacity {

    private Float bussinessCapacity;
    //存货周转率
    private Float IT;
    //资本密集度
    private Float CI;
    //固定资产周转率
    private Float FAT;
    //应收账款周转率
    private Float ART;
    //总资产周转率
    private Float TAT;
    //非流动资产周转率
    private Float TNCA;
    //流动资产周转率
    private Float CAT;
    //股东权益周转率（次）
    private Float SETT;

}
