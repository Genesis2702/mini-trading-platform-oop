public interface Tradeable {
    String getSymbol();

    double getCurrentPriceValue();

    boolean isAvailableForTrading();

    default String getTradingInfo() {
        String availableForTrading = isAvailableForTrading() ? "AVAILABLE" : "UNAVAILABLE";
        return  getSymbol() + " @ " + String.format("%.2f", getCurrentPriceValue()) + " [" + availableForTrading + "]";
    }
}
