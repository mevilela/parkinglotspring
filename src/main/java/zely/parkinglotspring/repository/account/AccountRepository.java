package zely.parkinglotspring.repository.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import zely.parkinglotspring.model.account.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

}
