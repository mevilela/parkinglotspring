package model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer extends Account{

    @Id
    private Integer Id;

    @Override
    public boolean resetPassword() {
        return false;
    }
}
