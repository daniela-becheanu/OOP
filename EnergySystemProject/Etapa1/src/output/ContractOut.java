package output;

import utils.Contract;

public final class ContractOut {
    private int consumerId;
    private int price;
    private int remainedContractMonths;

    public ContractOut(final Contract contract) {
        this.consumerId = contract.getConsumerId();
        this.price = contract.getPrice();
        this.remainedContractMonths = contract.getRemainedContractMonths();
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
