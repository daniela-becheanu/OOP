package input;

import utils.Contract;

public final class ConsumerInp implements PlayerInp {
    public static final double DEBT_MULTIPLIER = 1.2;
    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private boolean isBankrupt;
    private int remainedContractMonths;
    private int currentPay;
    private Contract currentContract;
    private Contract contractDebt;

    public Contract getContractDebt() {
        return contractDebt;
    }

    public void setContractDebt(final Contract contractDebt) {
        this.contractDebt = contractDebt;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }


    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getCurrentPay() {
        return currentPay;
    }

    public void setCurrentPay(final int currentPay) {
        this.currentPay = currentPay;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(final Contract currentContract) {
        this.currentContract = currentContract;
    }

    /**
     *
     * @param input
     */
    public void pay(final Input input) {
        if (!this.isBankrupt()) {
            if (this.getContractDebt() == null) {
                if (this.getInitialBudget() < this.getCurrentPay()) {
                    this.setContractDebt(this.getCurrentContract());
                } else {
                    this.setInitialBudget(this.getInitialBudget() - this.getCurrentPay());
                }
            } else {
                if (this.getInitialBudget() > this.getContractDebt().getPrice()
                        * DEBT_MULTIPLIER + this.getCurrentPay()) {
                    this.setInitialBudget((int) (this.getInitialBudget() - (this
                            .getContractDebt().getPrice() * DEBT_MULTIPLIER + this
                            .getCurrentPay())));
                    this.getContractDebt().getDistributor().setInitialBudget((int) (this
                            .getContractDebt().getDistributor().getInitialBudget() + this
                            .getContractDebt().getPrice() * DEBT_MULTIPLIER)); //!!!!!!!!!!!!!!!!
                } else {
                    this.setBankrupt(true);
                }
            }
        }
    }
}
