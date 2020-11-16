package sk.stuba.fei.uim.asos.assignment2.user.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.InsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.user.service.UserService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;
import sk.stuba.fei.uim.asos.assignment2.ws.UsersServicePortType;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;
import java.util.stream.Collectors;

@WebService(name = "UsersServicePortType", targetNamespace = "contracts")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public class UsersEndPoint implements UsersServicePortType {

    private UserService userService;
    private Converter converter;

    public UsersEndPoint(UserService userService, Converter converter) {
        this.userService = userService;
        this.converter = converter;
    }


    @Override
    public Users getAll() {
        Users users = new Users();
        List<User> usersList = userService.getAll().stream().map(user -> {
            User newUser = converter.convertToGeneratedUser(user);
            return newUser;
        }).collect(Collectors.toList());
        users.getAbstractUser().addAll(usersList);
        return users;
    }

    @Override
    public User createUser(User createUserRequest) {
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser =  userService.add(converter.convertToImplementedUserWithoutId(createUserRequest));
        User user = converter.convertToGeneratedUser(implUser);
        return user;
    }

    @Override
    public User getUserById(GetUserByIdRequest getUserByIdRequest) {

        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(getUserByIdRequest.getUserId());
        User user = converter.convertToGeneratedUser(implUser);
        return user;
    }

    @Override
    public User updateUser(User updateUserRequest) {
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.update(converter.convertToImplementedUser(updateUserRequest));
        return updateUserRequest;
    }


}
