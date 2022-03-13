package com.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 发展能力
 */
@Embeddable
@Data
@NoArgsConstructor
public class DevelopmentCapacity {

    private Float developmentCapacity;
    //资本积累率
    private Float CAR;
    //营业总收入增长率
    private Float GRGOI;
    //净资产收益率增长率
    private Float GRROE;
    //固定资产增长率
    private Float GRFA;
    //营业利润子张履
    private Float GROP;
    //总资产增长率
    private Float GRTA;
    //净利润同比增长
    private Float YYG;

}
