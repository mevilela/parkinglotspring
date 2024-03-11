package zely.parkinglotspring.model.payment;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("credit_card")
public class CreditCard extends Payment{
}
