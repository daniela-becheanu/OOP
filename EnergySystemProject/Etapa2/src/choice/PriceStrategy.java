package choice;

import input.DistributorInp;
import input.ProducerInp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class PriceStrategy implements ChoiceStrategy {
    private DistributorInp distributor;

    public PriceStrategy(DistributorInp distributor) {
        this.distributor = distributor;
    }

    @Override
    public List<ProducerInp> getProducersList(final List<ProducerInp> producers) {
        List<ProducerInp> sortedProducers = producers.stream().sorted(Comparator
                .comparing(ProducerInp::getPriceKW)
                .thenComparing(ProducerInp::getEnergyPerDistributor, Comparator.reverseOrder())
                .thenComparingInt(ProducerInp::getId)).collect(Collectors.toList()).stream()
                .filter(producer -> producer.getDistributors().size() < producer
                        .getMaxDistributors()).collect(Collectors.toList());

        int energyNeeded = 0;
        List<ProducerInp> chosenProducers = new ArrayList<>();

        for (ProducerInp p : sortedProducers) {
            if (energyNeeded < distributor.getEnergyNeededKW()) {
                energyNeeded += p.getEnergyPerDistributor();
                chosenProducers.add(p);
                p.getDistributors().add(distributor);
                p.addObserver(distributor);
            } else {
                break;
            }
        }
        return chosenProducers;
    }
}
