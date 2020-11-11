package sk.stuba.fei.uim.asos.assignment2.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String zip;
    private String city;
    private String street;
    private String streetNumber;

}
