package sk.stuba.fei.uim.asos.assignment2.insurance.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.InsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.user.service.UserService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "ContractsServicePortType", targetNamespace = "contracts")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public class ContractsEndPoint implements ContractsServicePortType {

    private InsuranceContractService insuranceContractService;
    private UserService userService;

    public ContractsEndPoint(InsuranceContractService insuranceContractService, UserService userService) {
        this.insuranceContractService = insuranceContractService;
        this.userService = userService;
    }


    @Override
    public Contracts getAll() {
        Contracts cn = new Contracts();
        cn.getCon().addAll(insuranceContractService.getAll());
        return cn;
    }

    @Override
    public InsuranceContract createContract(InsuranceContracts createContractRequest) {
        HouseholdInsuranceContract householdInsuranceContract = createContractRequest.getHouseholdInsuranceContract();
        TravelInsuranceContract travelInsuranceContract = createContractRequest.getTravelInsuranceContract();
        InjuryInsuranceContract injuryInsuranceContract = createContractRequest.getInjuryInsuranceContract();
        PropertyInsuranceContract propertyInsuranceContract = createContractRequest.getPropertyInsuranceContract();


        if(householdInsuranceContract == null && travelInsuranceContract == null && injuryInsuranceContract == null && propertyInsuranceContract == null){
            return null;
        }

        if(householdInsuranceContract != null){
            validateHouseholdInsuranceContract(householdInsuranceContract);
            addContractToUser(householdInsuranceContract);
            InsuranceContract household = insuranceContractService.create(householdInsuranceContract);
            return household;
        }
        if(travelInsuranceContract != null){
            addContractToUser(injuryInsuranceContract);
            InsuranceContract insurance = insuranceContractService.create(travelInsuranceContract);
            return insurance;
        }
        if(propertyInsuranceContract != null){
            validatePropertyInsuranceContract(propertyInsuranceContract);
            addContractToUser(propertyInsuranceContract);
            InsuranceContract propertyInsurance = insuranceContractService.create(propertyInsuranceContract);
            return propertyInsurance;
        }
        if(injuryInsuranceContract != null){
            validateInjuryInsuranceContract(injuryInsuranceContract);
            addContractToUser(injuryInsuranceContract);
            InsuranceContract injuryInsurance = insuranceContractService.create(injuryInsuranceContract);
            return injuryInsurance;
        }

        return null;
    }

    @Override
    public Contracts getByUserId(GetByIdRequest contractsByUserIdRequest) {
        Contracts contracts = new Contracts();
        contracts.getCon().addAll(insuranceContractService.getByUserId(contractsByUserIdRequest.getUserId()));
        return contracts;
    }

    @Override
    public InsuranceContract updateContract(InsuranceContracts updateContractRequest) {
        HouseholdInsuranceContract householdInsuranceContract = updateContractRequest.getHouseholdInsuranceContract();
        TravelInsuranceContract travelInsuranceContract = updateContractRequest.getTravelInsuranceContract();
        InjuryInsuranceContract injuryInsuranceContract = updateContractRequest.getInjuryInsuranceContract();
        PropertyInsuranceContract propertyInsuranceContract = updateContractRequest.getPropertyInsuranceContract();


        if(householdInsuranceContract == null && travelInsuranceContract == null && injuryInsuranceContract == null && propertyInsuranceContract == null){
            return null;
        }
        if(householdInsuranceContract != null){
            validateHouseholdInsuranceContract(householdInsuranceContract);
            InsuranceContract household = insuranceContractService.update(householdInsuranceContract);
            return household;
        }
        if(travelInsuranceContract != null){
            validateTravelInsuranceContract(travelInsuranceContract);
            InsuranceContract insurance = insuranceContractService.update(travelInsuranceContract);
            return insurance;
        }
        if(propertyInsuranceContract != null){
            validatePropertyInsuranceContract(propertyInsuranceContract);
            InsuranceContract propertyInsurance = insuranceContractService.update(propertyInsuranceContract);
            return propertyInsurance;
        }
        if(injuryInsuranceContract != null){
            validateInjuryInsuranceContract(injuryInsuranceContract);
            InsuranceContract injuryInsurance = insuranceContractService.update(injuryInsuranceContract);
            return injuryInsurance;
        }
        return null;
    }


    public boolean validateHouseholdInsuranceContract(HouseholdInsuranceContract householdInsuranceContract){
        if(householdInsuranceContract.getCreatedAt() == null){
            throw new IllegalArgumentException("Attribute createdAt is required!");
        }
        if(householdInsuranceContract.getStartedAt() == null){
            throw new IllegalArgumentException("Attribute startedAt is required!");
        }
        if(householdInsuranceContract.getEndedAt() == null){
            throw new IllegalArgumentException("Attribute endedAt is required!");
        }
        if(householdInsuranceContract.getPropertyType() == null){
            throw new IllegalArgumentException("Attribute propertyType is required!");
        }
        if(householdInsuranceContract.getPropertyAddress() == null){
            throw new IllegalArgumentException("Attribute propertyAddress is required!");
        }
        Long insurer_id = householdInsuranceContract.getInsurer();
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(insurer_id);
        if(implUser == null){
            throw new IllegalArgumentException("Insurer has not been created!");
        }
        return true;
    }
    public boolean validateInjuryInsuranceContract(InjuryInsuranceContract injuryInsuranceContract){

        if(injuryInsuranceContract.getCreatedAt() == null){
            throw new IllegalArgumentException("Attribute createdAt is required!");
        }
        if(injuryInsuranceContract.getStartedAt() == null){
            throw new IllegalArgumentException("Attribute startedAt is required!");
        }
        if(injuryInsuranceContract.getEndedAt() == null){
            throw new IllegalArgumentException("Attribute endedAt is required!");
        }
        if(injuryInsuranceContract.getInjuryInsuranceContractAreaType() == null){
            throw new IllegalArgumentException("Attribute injuryInsuranceContractAreaType is required!");
        }

        Long insurer_id = injuryInsuranceContract.getInsurer();
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(insurer_id);
        if(implUser == null){
            throw new IllegalArgumentException("Insurer has not been created!");
        }

        return true;
    }

    public boolean validatePropertyInsuranceContract(PropertyInsuranceContract propertyInsuranceContract){

        if(propertyInsuranceContract.getCreatedAt() == null){
            throw new IllegalArgumentException("Attribute createdAt is required!");
        }
        if(propertyInsuranceContract.getStartedAt() == null){
            throw new IllegalArgumentException("Attribute startedAt is required!");
        }
        if(propertyInsuranceContract.getEndedAt() == null){
            throw new IllegalArgumentException("Attribute endedAt is required!");
        }
        if(propertyInsuranceContract.getPropertyType() == null){
            throw new IllegalArgumentException("Attribute propertyType is required!");
        }
        if(propertyInsuranceContract.getPropertyAddress() == null){
            throw new IllegalArgumentException("Attribute propertyAddress is required!");
        }

        Long insurer_id = propertyInsuranceContract.getInsurer();
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(insurer_id);
        if(implUser == null){
            throw new IllegalArgumentException("Insurer has not been created!");
        }

        return true;
    }

    public boolean validateTravelInsuranceContract(TravelInsuranceContract travelInsuranceContract){

        if(travelInsuranceContract.getCreatedAt() == null){
            throw new IllegalArgumentException("Attribute createdAt is required!");
        }
        if(travelInsuranceContract.getStartedAt() == null){
            throw new IllegalArgumentException("Attribute startedAt is required!");
        }
        if(travelInsuranceContract.getEndedAt() == null){
            throw new IllegalArgumentException("Attribute endedAt is required!");
        }
        if(travelInsuranceContract.getTravelInsuranceContractType() == null){
            throw new IllegalArgumentException("Attribute travelInsuranceContractType is required!");
        }

        Long insurer_id = travelInsuranceContract.getInsurer();
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(insurer_id);
        if(implUser == null){
            throw new IllegalArgumentException("Insurer has not been created!");
        }

        return true;
    }

    public boolean addContractToUser(InsuranceContract insuranceContract){

        Long insurer_id = insuranceContract.getInsurer();
        sk.stuba.fei.uim.asos.assignment2.user.domain.User implUser = userService.getOne(insurer_id);
        if(implUser == null){
            throw new IllegalArgumentException("Insurer has not been created!");
        }
        implUser.getContracts().add(insuranceContract);
        userService.update(implUser);
        return true;
    }

}
