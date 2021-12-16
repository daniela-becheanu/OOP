package input;

import java.util.List;

public final class InitialData {
    private List<ConsumerInp> consumers;
    private List<DistributorInp> distributors;

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
}
