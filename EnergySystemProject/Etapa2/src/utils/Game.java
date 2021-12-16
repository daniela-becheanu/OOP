package utils;

import input.ConsumerInp;
import input.DistributorChanges;
import input.DistributorInp;
import input.Input;
import input.MonthlyUpdates;
import input.ProducerChanges;
import input.ProducerInp;
import output.MonthlyStats;
import choice.ChoiceStrategyFactory;

import java.util.ArrayList;
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
     * Method called so that the distributors choose their initial producers
     * @param distributors
     * @param producers
     */
    public void chooseInitialProducers(final List<DistributorInp> distributors,
                                       final List<ProducerInp> producers) {
        ChoiceStrategyFactory factory = ChoiceStrategyFactory.getInstance();

        for (DistributorInp d : distributors) {
            d.setProducers(factory.createStrategy(ChoiceStrategyFactory.StrategyType.valueOf(d
                    .getProducerStrategy()), d).getProducersList(producers));
        }
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
     * Method that computes the production cost of all distributors
     * @param distributors
     */
    public void computeProductionCost(final List<DistributorInp> distributors) {
        for (DistributorInp distributor : distributors) {
            distributor.computeProductionCost();
        }
    }

    /**
     * Method that computes the prices of all distributors' contracts
     * @param distributors
     */
    public void computePricesDistributors(final List<DistributorInp> distributors) {
        for (DistributorInp distributor : distributors) {
            distributor.computePrice();
        }
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
     * Removes the finished contracts of all distributors
     * @param distributors
     */
    void removeContracts(final List<DistributorInp> distributors) {
        for (DistributorInp d : distributors) {
            d.removeContracts();
        }
    }

    /**
     * Method used to set the field where it is decided if an energy type is renewable or not
     * @param producers the producers whose fields need to be set
     */
    public void renewableEnergy(final List<ProducerInp> producers) {
        for (ProducerInp p : producers) {
            if (p.getEnergyType().equals("WIND") || p.getEnergyType().equals("SOLAR") || p
                    .getEnergyType().equals("HYDRO")) {
                p.setRenewableEnergy(true);
            }
        }
    }

    /**
     * Makes the monthly updates required
     * @param i the month when the updates take place
     * @param input
     */
    public void monthlyUpdates(final int i, final Input input) {
        MonthlyUpdates updates = input.getMonthlyUpdates().get(i);

        for (ConsumerInp consumer : updates.getNewConsumers()) {
            input.getInitialData().getConsumers().add(consumer);
        }
        for (DistributorChanges dChanges : updates.getDistributorChanges()) {
            for (DistributorInp d : input.getInitialData().getDistributors()) {
                if (d.getId() == dChanges.getId()) {
                    d.setInitialInfrastructureCost(dChanges.getInfrastructureCost());
                }
            }
        }
    }

    /**
     * Makes the monthly updates for producers
     * @param input
     * @param i the month the update needs to be made
     */
    void monthlyUpdatesProducers(final Input input, int i) {
        for (ProducerChanges pChanges : input.getMonthlyUpdates().get(i).getProducerChanges()) {
            for (ProducerInp p : input.getInitialData().getProducers()) {
                if (p.getId() == pChanges.getId()) {
                    p.setEnergyPerDistributor(pChanges.getEnergyPerDistributor());
                    p.setChanges(input);
                }
            }
        }
    }

    /**
     * Actualizes the producers for the distributors that need it, by removing the distributor
     * for the producers' lists and setting another list of producers for the distributor, using
     * Strategy
     * @param input
     */
    void actualizeProducers(final Input input) {
        ChoiceStrategyFactory factory = ChoiceStrategyFactory.getInstance();
        for (DistributorInp d : input.getInitialData().getDistributors()) {
            if ((!d.isBankrupt()) && d.getChanged() == 1) {
                for (ProducerInp p : input.getInitialData().getProducers()) {
                    p.getDistributors().remove(d);
                    p.deleteObserver(d);
                }
                d.setProducers(factory.createStrategy(ChoiceStrategyFactory.StrategyType
                        .valueOf(d.getProducerStrategy()), d).getProducersList(input
                        .getInitialData().getProducers()));
                d.setChanged(0);
            }
        }
    }

    /**
     * Creates the distributorsIds list for the monthly stat of that current month
     * @param producers
     * @param i the month
     */
    void setMonthlyStats(final List<ProducerInp> producers, final int i) {
        for (ProducerInp p : producers) {
            List<Integer> listIds = new ArrayList<>();
            for (DistributorInp d : p.getDistributors()) {
                listIds.add(d.getId());
            }
            listIds = listIds.stream().sorted().collect(Collectors.toList());
            MonthlyStats monthlyStats = new MonthlyStats(i + 1, listIds);
            p.getMonthlyStats().add(monthlyStats);
        }
    }

    /**
     * Starts and runs all turns of the game
     * @param input
     */
    public void start(final Input input) {
        renewableEnergy(input.getInitialData().getProducers());

        chooseInitialProducers(input.getInitialData().getDistributors(), input.getInitialData()
                .getProducers());

        computeProductionCost(input.getInitialData().getDistributors());

        computePricesDistributors(input.getInitialData().getDistributors());

        addIncome(input.getInitialData().getConsumers());

        chooseContract(input);

        consumersPayment(input);

        distributorsPayment(input);

        removeContracts(input.getInitialData().getDistributors());

        for (int i = 0; i < input.getNumberOfTurns(); ++i) {
            monthlyUpdates(i, input);

            computePricesDistributors(input.getInitialData().getDistributors());

            addIncome(input.getInitialData().getConsumers());

            chooseContract(input);

            removeContracts(input.getInitialData().getDistributors());

            consumersPayment(input);

            distributorsPayment(input);

            monthlyUpdatesProducers(input, i);

            actualizeProducers(input);

            computeProductionCost(input.getInitialData().getDistributors());

            setMonthlyStats(input.getInitialData().getProducers(), i);
        }
    }
}
