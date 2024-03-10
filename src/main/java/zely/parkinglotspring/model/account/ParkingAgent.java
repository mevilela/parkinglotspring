package zely.parkinglotspring.model.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("parkingAgent")
public class ParkingAgent extends Account{

    @Override
    public boolean resetPassword() {
        return false;
    }
}
