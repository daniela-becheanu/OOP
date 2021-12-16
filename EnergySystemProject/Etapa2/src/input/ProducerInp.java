package input;

import output.MonthlyStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public final class ProducerInp extends Observable implements PlayerInp {
    private int id;
    private  String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private List<MonthlyStats> monthlyStats = new ArrayList<>(maxDistributors);
    private boolean renewableEnergy;
    private List<DistributorInp> distributors = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public boolean isRenewableEnergy() {
        return renewableEnergy;
    }

    public void setRenewableEnergy(final boolean renewableEnergy) {
        this.renewableEnergy = renewableEnergy;
    }

    public List<DistributorInp> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<DistributorInp> distributors) {
        this.distributors = distributors;
    }

    /**
     * Sets the current producer as changed and notifies its observers (distributors)
     * @param input
     */
    public void setChanges(Input input) {
        setChanged();
        notifyObservers(input);
    }

}
