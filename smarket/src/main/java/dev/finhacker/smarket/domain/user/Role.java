package dev.finhacker.smarket.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Integer id;
    private String name;

    public static Role MANAGER = new Role(0, "ROLE_MANAGER");
    public static Role ENTERPRISE = new Role(1, "ROLE_ENTERPRISE");

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
