package dev.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 偿债能力
 */
@Embeddable
@Data
@NoArgsConstructor
public class Solvency {

    //资产负债率
    private Float ALR;
    //现金流利息保障倍数
    private Float CFICM;
    //权益乘数
    private Float TRAIM;
    //经营活动现金流量净额债务比
    private Float NCF;
    //速动比率
    private Float QR;
    //经营活动现金流量对流动负债的比率
    private Float ROCFCL;
    //经营活动现金流量对净利润的比率
    private Float ROCFNI;

}
