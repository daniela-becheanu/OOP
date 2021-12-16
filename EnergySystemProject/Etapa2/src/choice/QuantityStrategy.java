package choice;

import input.DistributorInp;
import input.ProducerInp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class QuantityStrategy implements ChoiceStrategy {
    private DistributorInp distributor;

    public QuantityStrategy(DistributorInp distributor) {
        this.distributor = distributor;
    }

    @Override
    public List<ProducerInp> getProducersList(final List<ProducerInp> producers) {
        List<ProducerInp> sortedProducers = producers.stream().sorted(Comparator
                .comparing(ProducerInp::getEnergyPerDistributor).reversed()
                .thenComparingInt(ProducerInp::getId)).collect(Collectors.toList())
                .stream().filter(producer -> producer.getDistributors().size() < producer
                        .getMaxDistributors()).collect(Collectors.toList());
        int energyNeeded = 0;
        List<ProducerInp> chosenProducers = new ArrayList<>();

        for (ProducerInp p : sortedProducers) {
            if (energyNeeded < distributor.getEnergyNeededKW()) {
                energyNeeded += p.getEnergyPerDistributor();
                chosenProducers.add(p);
                p.addObserver(distributor);
                p.getDistributors().add(distributor);
            } else {
                break;
            }
        }
        return chosenProducers;
    }
}
