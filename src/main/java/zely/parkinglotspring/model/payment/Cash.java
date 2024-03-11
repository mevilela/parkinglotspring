package zely.parkinglotspring.model.payment;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cash")
public class Cash extends Payment{
}
