package input;

import java.util.List;

public final class MonthlyUpdates {
    private List<ConsumerInp> newConsumers;
    private List<CostsChanges> costsChanges;

    public List<ConsumerInp> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<ConsumerInp> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<CostsChanges> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final List<CostsChanges> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
