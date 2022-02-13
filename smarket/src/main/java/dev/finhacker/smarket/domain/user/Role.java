package dev.finhacker.smarket.domain.user;

public enum Role {

    MANAGER(0, "ROLE_MANAGER"),
    ENTERPRISE(1, "ROLE_ENTERPRISE");

    private Integer id;
    private String name;

    Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
