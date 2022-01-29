package dev.finhacker.smarket.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class UserManager extends User {

    private HashSet<Integer> favouriteSet = new HashSet<>();

    public UserManager(String name, String password, Role role) {
        super(name, password, role);
    }

    public boolean addFavourite(Integer id) {
        return favouriteSet.add(id);
    }

    public boolean removeFavourite(Integer id) {
        return favouriteSet.remove(id);
    }

}
