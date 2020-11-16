package sk.stuba.fei.uim.asos.assignment2.insurance.service;

import sk.stuba.fei.uim.asos.assignment2.utils.AtomicIntegerContractCounter;
import sk.stuba.fei.uim.asos.assignment2.ws.InsuranceContract;
import sk.stuba.fei.uim.asos.assignment2.ws.TravelInsuranceContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InsuranceContractService implements IInsuranceContractService<InsuranceContract, Integer> {

    private AtomicIntegerContractCounter atomicIntegerContractCounter;
    private List<InsuranceContract> contracts = new ArrayList<InsuranceContract>();

    public InsuranceContractService(AtomicIntegerContractCounter atomicIntegerContractCounter) {
        this.atomicIntegerContractCounter = atomicIntegerContractCounter;
        initContracts(contracts);
    }

    private void initContracts(List<InsuranceContract> contracts) {

        TravelInsuranceContract travelInsuranceContract = new TravelInsuranceContract();
        travelInsuranceContract.setId(atomicIntegerContractCounter.getCount().getAndIncrement());
        contracts.add(travelInsuranceContract);
    }

    @Override
    public InsuranceContract create(InsuranceContract contract) {
        if(contracts == null){
            return null;
        }
        contract.setId(atomicIntegerContractCounter.getCount().getAndIncrement());
        contracts.add(contract);
        return contract;
    }

    @Override
    public InsuranceContract update(InsuranceContract contract) {

        if(contract == null){
            return null;
        }
        InsuranceContract oldContract = contracts.stream()
                .filter(contract1 -> contract.getId() == contract1.getId())
                .findAny()
                .orElse(null);

        if(oldContract == null){
            return null;
        }
        contracts.remove(oldContract);
        contracts.add(contract);
        return contract;
    }

    @Override
    public List<InsuranceContract> getAll() {
        return contracts;
    }

    @Override
    public List<InsuranceContract> getByUserId(Long userId) {
        List<InsuranceContract> insuranceContractsbySpecificId = contracts.stream()
                .filter(insuranceContract -> userId.equals(insuranceContract.getInsurer()))
                .collect(Collectors.toList());
        return insuranceContractsbySpecificId;
    }
}
