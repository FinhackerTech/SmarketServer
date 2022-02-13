package dev.finhacker.smarket.domain.enterprise.news;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class News {

    @Id
    String url;

    Integer symbol;
    String title;
    Integer NOP;
    Float time;
    String context;

}
