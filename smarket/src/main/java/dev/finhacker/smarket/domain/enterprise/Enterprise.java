package dev.finhacker.smarket.domain.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.finhacker.smarket.domain.enterprise.quota.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * The entity of enterprises.
 */
@Entity
@Data
@NoArgsConstructor
public class Enterprise {

    @Id
    private Integer listedCoId;
    private Long securityId;
    private String symbol;
    private String shortName;
    private Date endDate;
    private String industryName;
    private String officeAddress;
    private String secretary;
    private String secretaryTel;
    private String secretaryFax;
    private String secretaryEmail;
    private String ISIN;
    private String fullName;
    private String legalRepresentative;
    private String establishDate;
    private Long registerCapital;
    private String website;
    private String email;
    private String province;
    private String city;
    private String mainBusiness;
    private Integer enterpriseVrisk;
    private Integer industryVrisk;
    private String logoUrl;
    private String description;

    @Embedded
    private LoanRate loanRate;
    @Embedded
    private Solvency solvency;
    @Embedded
    private BusinessCapacity businessCapacity;
    @Embedded
    private Profitability profitability;
    @Embedded
    private RiskLevel riskLevel;
    @Embedded
    private DevelopmentCapacity developmentCapacity;

    @JsonIgnore
    public Brief getBrief() {
        return new Brief(logoUrl, fullName, registerCapital);
    }

    @Data
    @AllArgsConstructor
    public class Brief {
        private String logoUrl;
        private String fullName;
        private Long registerCapital;
    }

    @JsonIgnore
    public Basic getBasic() {
        return new Basic(logoUrl, fullName, enterpriseVrisk, industryVrisk);
    }

    @Data
    @AllArgsConstructor
    public class Basic {
        private String logoUrl;
        private String fullName;
        private Integer enterpriseVrisk;
        private Integer industryVrisk;
    }

    @JsonIgnore
    public About getAbout() {
        return new About(logoUrl, listedCoId, securityId, symbol, industryName, website, shortName, fullName, registerCapital, description);
    }

    @Data
    @AllArgsConstructor
    public class About {
        private String logoUrl;
        private Integer listedCoId;
        private Long securityId;
        private String symbol;
        private String industryName;
        private String website;
        private String shortName;
        private String fullName;
        private Long registerCapital;
        private String description;
    }

    @JsonIgnore
    public Analyse getAnalyse() {
        return new Analyse(businessCapacity, developmentCapacity, loanRate, profitability, riskLevel, solvency);
    }

    @Data
    @AllArgsConstructor
    public class Analyse {
        private BusinessCapacity businessCapacity;
        private DevelopmentCapacity developmentCapacity;
        private LoanRate loanRate;
        private Profitability profitability;
        private RiskLevel riskLevel;
        private Solvency solvency;
    }

}
