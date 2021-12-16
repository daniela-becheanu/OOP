package output;

import input.ConsumerInp;
import input.DistributorInp;
import input.PlayerInp;
import input.ProducerInp;

public final class OutFactory {
    private static OutFactory instance = null;

    private OutFactory() {
    }

    /**
     * Method called to obtain the Singleton instance
     * @return
     */
    public static OutFactory getInstance() {
        if (instance == null) {
            instance = new OutFactory();
        }
        return instance;
    }

    public enum OutType {
        distributor, consumer, producer
    }

    /**
     * Method called to create an output object, depending on its type
     * @param outType
     * @param playerInput
     * @return
     */
    public PlayerOut createOut(final OutType outType, final PlayerInp playerInput) {
        return switch (outType) {
            case consumer -> new ConsumerOut((ConsumerInp) playerInput);
            case distributor -> new DistributorOut((DistributorInp) playerInput);
            case producer -> new ProducerOut((ProducerInp) playerInput);
        };
    }
}
