package output;

import input.DistributorInp;
import java.util.List;

public final class DistributorOut implements PlayerOut {
    private int id;
    private int budget;
    private boolean isBankrupt;
    private List<ContractOut> contracts;

    public DistributorOut(final DistributorInp d) {
        this.id = d.getId();
        this.budget = d.getInitialBudget();
        this.isBankrupt = d.isBankrupt();
        this.contracts = d.getContracts();
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
}
