package sk.stuba.fei.uim.asos.assignment2.insurance.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.InsuranceContractService;
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

    public ContractsEndPoint(InsuranceContractService insuranceContractService) {
        this.insuranceContractService = insuranceContractService;
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
            InsuranceContract household = insuranceContractService.create(householdInsuranceContract);
            return household;
        }
        if(travelInsuranceContract != null){
            InsuranceContract insurance = insuranceContractService.create(travelInsuranceContract);
            return insurance;
        }
        if(propertyInsuranceContract != null){
            InsuranceContract propertyInsurance = insuranceContractService.create(propertyInsuranceContract);
            return propertyInsurance;
        }
        if(injuryInsuranceContract != null){
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
            InsuranceContract household = insuranceContractService.update(householdInsuranceContract);
            return household;
        }
        if(travelInsuranceContract != null){
            InsuranceContract insurance = insuranceContractService.update(travelInsuranceContract);
            return insurance;
        }
        if(propertyInsuranceContract != null){
            InsuranceContract propertyInsurance = insuranceContractService.update(propertyInsuranceContract);
            return propertyInsurance;
        }
        if(injuryInsuranceContract != null){
            InsuranceContract injuryInsurance = insuranceContractService.update(injuryInsuranceContract);
            return injuryInsurance;
        }
        return null;
    }


}
