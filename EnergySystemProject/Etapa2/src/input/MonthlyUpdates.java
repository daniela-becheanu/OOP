package input;

import java.util.List;

public final class MonthlyUpdates {
    private List<ConsumerInp> newConsumers;
    private List<DistributorChanges> distributorChanges;
    private List<ProducerChanges> producerChanges;

    public List<ConsumerInp> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<ConsumerInp> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(final List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
