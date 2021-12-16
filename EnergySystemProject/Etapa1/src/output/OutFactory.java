package output;

import input.ConsumerInp;
import input.DistributorInp;
import input.PlayerInp;

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
        distributor, consumer
    }

    /**
     * Method called to create an output object, depending on its type
     * @param outType
     * @param input
     * @return
     */
    public PlayerOut createOut(final OutType outType, final PlayerInp input) {
        return switch (outType) {
            case consumer -> new ConsumerOut((ConsumerInp) input);
            case distributor -> new DistributorOut((DistributorInp) input);
        };
    }
}
