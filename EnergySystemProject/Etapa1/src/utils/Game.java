package utils;

import input.ConsumerInp;
import input.CostsChanges;
import input.DistributorInp;
import input.Input;
import input.MonthlyUpdates;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Game {
    private static Game instance = null;

    private Game() { }

    /**
     * Method called to obtain the Singleton instance
     * @return
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Sorts the distributors in ascending order of their prices, the second criterion being their
     * id
     * @param distributors
     * @return
     */
    public List<DistributorInp> sortPrices(final List<DistributorInp> distributors) {
        List<DistributorInp> sortedDistributors;
        sortedDistributors = distributors.stream().sorted(Comparator.comparingInt(DistributorInp
                ::getPrice).thenComparingInt(DistributorInp::getId)).collect(Collectors.toList())
                .stream().filter(distributor -> !distributor.isBankrupt()).collect(Collectors
                        .toList());
        return sortedDistributors;
    }

    /**
     * Adds the monthly income to all the consumers
     * @param consumers
     */
    public void addIncome(final List<ConsumerInp> consumers) {
        for (ConsumerInp consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setInitialBudget(consumer.getInitialBudget() + consumer
                        .getMonthlyIncome());
            }
        }
    }

    /**
     * Called for the consumers to choose the cheapest contract
     * @param input
     */
    public void chooseContract(final Input input) {
        for (ConsumerInp consumer : input.getInitialData().getConsumers()) {
            if ((!consumer.isBankrupt()) && (consumer.getCurrentContract() == null
                    || consumer.getCurrentContract().getRemainedContractMonths() == 0)) {
                DistributorInp minDistr = sortPrices(input.getInitialData().getDistributors())
                        .get(0);
                Contract newContract = new Contract(consumer.getId(),
                        minDistr.getPrice(), minDistr.getContractLength(), consumer, minDistr);
                minDistr.getContractsInput().add(newContract);
                consumer.setCurrentContract(newContract);
                newContract.setRemainedContractMonths(minDistr.getContractLength());
                consumer.setCurrentPay(minDistr.getPrice());
            }
        }
    }

    /**
     * Called to make the payment of all consumers
     * @param input
     */
    public void consumersPayment(final Input input) {
        for (ConsumerInp consumer : input.getInitialData().getConsumers()) {
            consumer.pay(input);
            consumer.getCurrentContract().setRemainedContractMonths(consumer.getCurrentContract()
                            .getRemainedContractMonths() - 1);
        }
    }

    /**
     * Removes the finished contracts of all distributors
     * @param distributors
     */
    void removeContracts(final List<DistributorInp> distributors) {
        for (DistributorInp d : distributors) {
            d.removeContracts();
        }
    }

    /**
     * Called to make the payment of all distributors
     * @param input
     */
    public void distributorsPayment(final Input input) {
        for (DistributorInp distributor : input.getInitialData().getDistributors()) {
            distributor.pay(input);
        }
        for (ConsumerInp consumer : input.getInitialData().getConsumers()) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentContract().getDistributor().getContractsInput().remove(consumer
                        .getCurrentContract());
            }
        }
    }

    /**
     * Makes the monthly updates required
     * @param i the month when the updates take place
     * @param input
     */
    public void monthlyUpdates(final int i, final Input input) {
        for (DistributorInp distributor : input.getInitialData()
                .getDistributors()) {
            distributor.computePrice();
        }
        if (i != -1 && input.getMonthlyUpdates().get(i) != null) {
            MonthlyUpdates updates = input.getMonthlyUpdates().get(i);
            for (CostsChanges costs : updates.getCostsChanges()) {
                DistributorInp distributor = input.getInitialData().getDistributors().get(costs
                        .getId());
                distributor.setInitialInfrastructureCost(costs.getInfrastructureCost());
                distributor.setInitialProductionCost(costs.getProductionCost());
                distributor.computePrice();
            }
            if (updates.getCostsChanges().size() == 0) {
                for (DistributorInp distributor : input.getInitialData().getDistributors()) {
                    distributor.computePrice();
                }
            }
            for (ConsumerInp consumer : updates.getNewConsumers()) {
                input.getInitialData().getConsumers().add(consumer);
            }
        }
    }

    /**
     * Starts and runs all turns of the game
     * @param input
     */
    public void start(final Input input) {
        for (int i = -1; i < input.getNumberOfTurns(); ++i) {
/*            System.out.println("-------------------RUNDA " + i + "----------------------");*/
            monthlyUpdates(i, input);

            addIncome(input.getInitialData().getConsumers());

            chooseContract(input);

            removeContracts(input.getInitialData().getDistributors());

            consumersPayment(input);

            distributorsPayment(input);
            /*for (ConsumerInp c : input.getInitialData().getConsumers()) {
                System.out.println("consumatorul " + c.getId() + " are bugetul " + c.getInitialBudget()
                        + " si e abonat la distribuitorul " + c.getCurrentContract().getDistributor().getId()
                        + " iar pretul contractului la care e abonat este " + c.getCurrentContract().getPrice());
            }
            for (DistributorInp d : input.getInitialData().getDistributors()) {
                System.out.println("distibuitorul " + d.getId() + " are bugetul " + d.getInitialBudget());
            }*/
        }
    }
}
