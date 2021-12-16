package choice;

import input.DistributorInp;

public final class ChoiceStrategyFactory {
    private static ChoiceStrategyFactory instance = null;

    /**
     * Method called to obtain the Singleton instance
     * @return
     */
    public static ChoiceStrategyFactory getInstance() {
        if (instance == null) {
            instance = new ChoiceStrategyFactory();
        }
        return instance;
    }

    /**
     * Strategy types for distributors to choose their producers
     */
    public enum StrategyType {
        GREEN, PRICE, QUANTITY
    }

    /**
     * Creates a strategy of the given type for a given distributor
     * @param strategyType
     * @param distributor
     * @return
     */
    public ChoiceStrategy createStrategy(StrategyType strategyType, DistributorInp distributor) {
        return switch (strategyType) {
            case GREEN -> new GreenStrategy(distributor);
            case PRICE -> new PriceStrategy(distributor);
            case QUANTITY -> new QuantityStrategy(distributor);
            default -> throw new IllegalStateException("Unexpected value: " + strategyType);
        };
    }


}
