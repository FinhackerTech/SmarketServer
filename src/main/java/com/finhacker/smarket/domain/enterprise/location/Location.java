package com.finhacker.smarket.domain.enterprise.location;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Location {

    @Id
    private Integer listedCoId;

    private String fullName;
    private String locationJson;
    private String industryJson;
    private String investmentList;
    private String controlList;

}
