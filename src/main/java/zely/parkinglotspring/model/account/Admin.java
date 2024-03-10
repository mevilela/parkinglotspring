package zely.parkinglotspring.model.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")public class Admin extends Account{

    @Override
    public boolean resetPassword() {
        return false;
    }
}
