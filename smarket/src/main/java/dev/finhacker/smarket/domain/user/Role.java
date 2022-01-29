package dev.finhacker.smarket.domain.user;

import java.io.Serializable;

public class Role implements Serializable {

    Integer id;
    String name;

    public static Role MANAGER = new Role(0, "ROLE_MANAGER");
    public static Role ENTERPRISE = new Role(1, "ROLE_ENTERPRISE");

    private Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

}
