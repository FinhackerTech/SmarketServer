package dev.finhacker.smarket.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class UserManager extends User {


    private HashSet<Integer> favouriteSet = new HashSet<>();

    private String managerName;

    public UserManager(String name, String password, String managerName) {
        super(name, password, Role.MANAGER);
        this.managerName = managerName;
    }

    public boolean addFavourite(Integer id) {
        return favouriteSet.add(id);
    }

    public boolean removeFavourite(Integer id) {
        return favouriteSet.remove(id);
    }

}
