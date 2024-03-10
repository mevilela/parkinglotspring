package zely.parkinglotspring.model.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("customer")
public class Customer extends Account{

    @Override
    public boolean resetPassword() {
        return false;
    }
}
