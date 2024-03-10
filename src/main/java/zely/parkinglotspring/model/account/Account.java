package zely.parkinglotspring.model.account;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @Id
    private Integer id;
    private String username;
    private String password;

    @Embedded //colocar uma classe a parte para criar caracteristicas em uma entity sem criar uma tabela nova
    private Person person;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    public abstract boolean resetPassword();

    public Account() {
    }

    public Account(String username, String password, Person person, AccountStatus status) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.status = status;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
