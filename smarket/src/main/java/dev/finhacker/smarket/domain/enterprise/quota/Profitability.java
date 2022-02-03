package dev.finhacker.smarket.domain.enterprise.quota;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 盈利能力
 */
@Embeddable
@Data
@NoArgsConstructor
public class Profitability {

    private Float profitability;
    //非经常性损益/总利润
    private Float NRP;
    //流动资产净利润率
    private Float CPM;
    //净资产收益率（ROE）
    private Float ROE;
    //固定资产净利润率
    private Float NPM;
    //总营业成本率
    private Float TOCR;
    //现金与利润总额比
    private Float CGPR;
    //销售净利率
    private Float NIRS;
    //息税前利润/营业总收入
    private Float EGOI;
    //销售毛利率
    private Float GMOS;
    //营业利润率
    private Float OPM;
    //经营活动净收益/利润总额
    private Float TNIPOA;

}
