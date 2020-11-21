package sk.stuba.fei.uim.asos.assignment2.user.endpoint;

import sk.stuba.fei.uim.asos.assignment2.user.domain.Address;
import sk.stuba.fei.uim.asos.assignment2.ws.Address3;
import sk.stuba.fei.uim.asos.assignment2.ws.User;

public class Converter {
    public Converter() {
    }
    public User convertToGeneratedUser(sk.stuba.fei.uim.asos.assignment2.user.domain.User oldUser) {
        Address3 corraddress = new Address3();
        Address3 permAddress = new Address3();

        corraddress.setCity(oldUser.getCorrespondenceAddress().getCity());
        corraddress.setStreet(oldUser.getCorrespondenceAddress().getStreet());
        corraddress.setStreetNumber(oldUser.getCorrespondenceAddress().getStreetNumber());
        corraddress.setZip(oldUser.getCorrespondenceAddress().getZip());

        permAddress.setCity(oldUser.getPermanentAddress().getCity());
        permAddress.setZip(oldUser.getPermanentAddress().getZip());
        permAddress.setStreetNumber(oldUser.getPermanentAddress().getStreetNumber());
        permAddress.setStreet(oldUser.getPermanentAddress().getStreet());

        User user = new User();
        user.setSurname(oldUser.getSurname());
        user.setLastname(oldUser.getLastname());
        user.setId(oldUser.getId());
        user.setEmail(oldUser.getEmail());
        user.setIdentificationNumber(oldUser.getIdentificationNumber());
        user.setCorrespondenceAddress(corraddress);
        user.setPermanentAddress(permAddress);
        user.getContracts().addAll(oldUser.getContracts());

        return user;
    }
    public sk.stuba.fei.uim.asos.assignment2.user.domain.User convertToImplementedUser(User oldUser) {
        Address corraddress = new Address();
        Address permAddress = new Address();

        corraddress.setCity(oldUser.getCorrespondenceAddress().getCity());
        corraddress.setStreet(oldUser.getCorrespondenceAddress().getStreet());
        corraddress.setStreetNumber(oldUser.getCorrespondenceAddress().getStreetNumber());
        corraddress.setZip(oldUser.getCorrespondenceAddress().getZip());

        permAddress.setCity(oldUser.getPermanentAddress().getCity());
        permAddress.setZip(oldUser.getPermanentAddress().getZip());
        permAddress.setStreetNumber(oldUser.getPermanentAddress().getStreetNumber());
        permAddress.setStreet(oldUser.getPermanentAddress().getStreet());

        sk.stuba.fei.uim.asos.assignment2.user.domain.User user = new sk.stuba.fei.uim.asos.assignment2.user.domain.User();
        user.setSurname(oldUser.getSurname());
        user.setLastname(oldUser.getLastname());
        user.setId(oldUser.getId());
        user.setEmail(oldUser.getEmail());
        user.setIdentificationNumber(oldUser.getIdentificationNumber());
        user.setPermanentAddress(permAddress);
        user.setCorrespondenceAddress(corraddress);
        return user;
    }

    public sk.stuba.fei.uim.asos.assignment2.user.domain.User convertToImplementedUserWithoutId(User oldUser) {
        Address corraddress = new Address();
        Address permAddress = new Address();

        corraddress.setCity(oldUser.getCorrespondenceAddress().getCity());
        corraddress.setStreet(oldUser.getCorrespondenceAddress().getStreet());
        corraddress.setStreetNumber(oldUser.getCorrespondenceAddress().getStreetNumber());
        corraddress.setZip(oldUser.getCorrespondenceAddress().getZip());

        permAddress.setCity(oldUser.getPermanentAddress().getCity());
        permAddress.setZip(oldUser.getPermanentAddress().getZip());
        permAddress.setStreetNumber(oldUser.getPermanentAddress().getStreetNumber());
        permAddress.setStreet(oldUser.getPermanentAddress().getStreet());

        sk.stuba.fei.uim.asos.assignment2.user.domain.User user = new sk.stuba.fei.uim.asos.assignment2.user.domain.User();
        user.setSurname(oldUser.getSurname());
        user.setLastname(oldUser.getLastname());
        user.setId(oldUser.getId());
        user.setEmail(oldUser.getEmail());
        user.setIdentificationNumber(oldUser.getIdentificationNumber());
        user.setPermanentAddress(permAddress);
        user.setCorrespondenceAddress(corraddress);
        return user;
    }

}
