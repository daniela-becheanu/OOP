package output;

import java.util.ArrayList;
import java.util.List;

public final class Output {
    private List<PlayerOut> consumers = new ArrayList<>();
    private List<PlayerOut> distributors = new ArrayList<>();

    public List<PlayerOut> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<PlayerOut> consumers) {
        this.consumers = consumers;
    }

    public List<PlayerOut> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<PlayerOut> distributors) {
        this.distributors = distributors;
    }
}
