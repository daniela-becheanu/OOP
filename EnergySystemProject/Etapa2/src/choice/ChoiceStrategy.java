package choice;

import input.ProducerInp;

import java.util.List;

public interface ChoiceStrategy {
    /**
     * Method called to assign the list of producers of an distributor
     * @param producers
     * @return
     */
    List<ProducerInp> getProducersList(List<ProducerInp> producers);
}
