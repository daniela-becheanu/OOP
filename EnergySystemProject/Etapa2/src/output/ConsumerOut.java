package output;

import input.ConsumerInp;

public final class ConsumerOut implements PlayerOut {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public ConsumerOut(final ConsumerInp consumer) {
        this.id = consumer.getId();
        this.isBankrupt = consumer.isBankrupt();
        this.budget = consumer.getInitialBudget();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}
