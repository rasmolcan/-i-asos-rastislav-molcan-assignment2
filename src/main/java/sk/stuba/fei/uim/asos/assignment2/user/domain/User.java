package sk.stuba.fei.uim.asos.assignment2.user.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractUser<Long> {

    private String surname;
    private String lastname;
    private String identificationNumber;
    private String email;
    private Address permanentAddress;
    private Address correspondenceAddress;
    private List<Object> contracts;

    public User() {
        super();
        this.contracts = new ArrayList<>();
    }

    public User(Long id) {
        this();
        this.id = id;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
        if (this.correspondenceAddress == null) {
            this.correspondenceAddress = permanentAddress;
        }
    }
}
