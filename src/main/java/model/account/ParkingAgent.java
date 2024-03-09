package model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingAgent extends Account{

    @Override
    public boolean resetPassword() {
        return false;
    }
}
