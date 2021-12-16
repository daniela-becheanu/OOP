package utils;

import input.ConsumerInp;
import input.DistributorInp;

public final class Contract {
    private int consumerId;
    private int price;
    private int remainedContractMonths;
    private ConsumerInp consumer;
    private DistributorInp distributor;

    public Contract(final int consumerId, final int price, final int remainedContractMonths,
                    final ConsumerInp consumer, final DistributorInp distributor) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
        this.consumer = consumer;
        this.distributor = distributor;
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

    public void setDistributor(final DistributorInp distributor) {
        this.distributor = distributor;
    }

    public DistributorInp getDistributor() {
        return distributor;
    }

    public ConsumerInp getConsumer() {
        return consumer;
    }

    public void setConsumer(final ConsumerInp consumer) {
        this.consumer = consumer;
    }
}
