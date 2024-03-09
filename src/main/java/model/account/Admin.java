package model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin extends Account{

    @Override
    public boolean resetPassword() {
        return false;
    }
}
