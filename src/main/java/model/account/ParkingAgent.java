package model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingAgent extends Account{

    @Id
    private Integer id;

    @Override
    public boolean resetPassword() {
        return false;
    }
}
