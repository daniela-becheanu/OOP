package input;

import utils.Contract;
import output.ContractOut;

import java.util.ArrayList;
import java.util.List;

public final class DistributorInp implements PlayerInp {
    public static final double PROFIT_MULTIPLIER = 0.2;
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private List<Contract> contractsInput = new ArrayList<>();
    private int price;
    private boolean bankrupt;
    private List<ContractOut> contracts = new ArrayList<>();

    public List<ContractOut> getContracts() {
        return contracts;
    }

    public void setContracts(final List<ContractOut> contracts) {
        this.contracts = contracts;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public List<Contract> getContractsInput() {
        return contractsInput;
    }


    public void setContractsInput(final List<Contract> contractsInput) {
        this.contractsInput = contractsInput;
    }

    /**
     * Computes the price of the current contract
     */
    public void computePrice() {
        int profit = (int) Math.round(Math.floor(PROFIT_MULTIPLIER * initialProductionCost));
        if (contractsInput.size() != 0) {
            price = (int) (Math.round(Math.floor(initialInfrastructureCost / contractsInput.size())
                    + initialProductionCost + profit));
        } else {
            price = initialInfrastructureCost + initialProductionCost + profit;
        }

    }

    /**
     * Method called to remove the expired contracts
     */
    public void removeContracts() {
        contractsInput.removeIf(contract -> contract.getRemainedContractMonths() == 0);
    }

    /**
     * Method called so that a consumer pays its rate
     * @param input
     */
    public void pay(final Input input) {
        if (this.isBankrupt()) {
            for (ConsumerInp consumer : input.getInitialData().getConsumers()) {
                if (consumer.getContractDebt() != null && consumer.getContractDebt()
                        .getDistributor().getId() == this.getId()) {
                    consumer.setContractDebt(null);
                }
              /*  if (consumer.getCurrentContract() != null && consumer.getCurrentContract()
                        .getDistributor().getId() == this.getId()) {
                    consumer.setCurrentContract(null);
                }*/
            }
        } else {
            this.setInitialBudget(this.getInitialBudget()
                    - this.getInitialInfrastructureCost()
                    - this.getInitialProductionCost()
                    * this.getContractsInput().size());
            for (ConsumerInp consumer : input.getInitialData().getConsumers()) {
                if ((!consumer.isBankrupt()) && (consumer.getContractDebt() == null)
                        && consumer.getCurrentContract().getDistributor().getId() == this
                        .getId()) {
                    this.setInitialBudget((this.getInitialBudget()
                            + consumer.getCurrentContract().getPrice()));
                }
            }
        }
        if (this.getInitialBudget() < 0) {
            this.setBankrupt(true);
        }
    }
}
