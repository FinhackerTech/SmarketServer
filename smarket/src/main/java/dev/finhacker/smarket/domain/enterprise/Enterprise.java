package dev.finhacker.smarket.domain.enterprise;

import dev.finhacker.smarket.domain.enterprise.quota.*;
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
    private Integer listedColId;
    private Integer securityId;
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
    private Integer registerCapital;
    private String website;
    private String email;
    private String province;
    private String city;
    private String mainBusiness;

    @Embedded
    private LoanRate loan;
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

}
