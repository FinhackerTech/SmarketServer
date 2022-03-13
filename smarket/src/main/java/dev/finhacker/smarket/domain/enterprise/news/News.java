package dev.finhacker.smarket.domain.enterprise.news;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class News {

    @Id
    private String url;

    private String symbol;
    private String title;
    private Integer NOP;
    private Date time;
    private String context;

}
