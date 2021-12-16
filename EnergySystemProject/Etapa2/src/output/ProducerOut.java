package output;

import input.ProducerInp;

import java.util.ArrayList;
import java.util.List;

public final class ProducerOut implements PlayerOut {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private String energyType;
    private int energyPerDistributor;
    private List<MonthlyStats> monthlyStats = new ArrayList<>();

    public ProducerOut(ProducerInp producer) {
        this.id = producer.getId();
        this.maxDistributors = producer.getMaxDistributors();
        this.priceKW = producer.getPriceKW();
        this.energyType = producer.getEnergyType();
        this.energyPerDistributor = producer.getEnergyPerDistributor();
        this.monthlyStats = producer.getMonthlyStats();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
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

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
