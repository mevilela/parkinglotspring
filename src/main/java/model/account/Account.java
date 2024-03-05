package model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public abstract class Account {

    @Id
    private Integer id;
    private String username;
    private String password;

    private Person person;

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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
