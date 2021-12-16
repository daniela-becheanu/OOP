package output;

import input.DistributorInp;

import java.util.List;

public final class DistributorOut implements PlayerOut {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private List<ContractOut> contracts;


    public DistributorOut(final DistributorInp d) {
        this.id = d.getId();
        this.energyNeededKW = d.getEnergyNeededKW();
        this.contractCost = d.getPrice();
        this.budget = d.getInitialBudget();
        this.isBankrupt = d.isBankrupt();
        this.contracts = d.getContracts();
        this.producerStrategy = d.getProducerStrategy();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public List<ContractOut> getContracts() {
        return contracts;
    }

    public void setContracts(final List<ContractOut> contracts) {
        this.contracts = contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
