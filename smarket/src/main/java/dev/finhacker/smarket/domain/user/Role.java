package dev.finhacker.smarket.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    public static Role MANAGER = new Role(0, "ROLE_MANAGER");
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    public static Role ENTERPRISE = new Role(1, "ROLE_ENTERPRISE");

    public Role getENTERPRISE() {
        return ENTERPRISE;
    }

    public Role getMANAGER() {
        return MANAGER;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
