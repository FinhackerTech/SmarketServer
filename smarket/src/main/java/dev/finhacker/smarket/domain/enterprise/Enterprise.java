package dev.finhacker.smarket.domain.enterprise;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The entity of enterprises.
 */
@Entity
@Data
@NoArgsConstructor
public class Enterprise {

    @Id
    private Integer id;

}
