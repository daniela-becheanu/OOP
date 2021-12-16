package input;

import java.util.List;

public final class InitialData {
    private List<ConsumerInp> consumers;
    private List<DistributorInp> distributors;
    private List<ProducerInp> producers;

    public List<ConsumerInp> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<ConsumerInp> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorInp> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<DistributorInp> distributors) {
        this.distributors = distributors;
    }

    public List<ProducerInp> getProducers() {
        return producers;
    }

    public void setProducers(final List<ProducerInp> producers) {
        this.producers = producers;
    }
}
