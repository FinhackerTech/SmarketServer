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
    String url;

    String symbol;
    String title;
    Integer NOP;
    Date time;
    String context;

}
