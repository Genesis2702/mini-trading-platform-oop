import java.time.LocalDateTime;

public abstract class Instrument implements Tradeable, Priceable {
    private final String symbol;
    private String name;
    private double currentPrice;
    private LocalDateTime lastUpdated;

    public Instrument(String symbol, String name, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public abstract double riskScore();

    public abstract String assetClass();

    public abstract void accept(InstrumentVisitor visitor);
    
    public void updatePrice(double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException();
        }
        currentPrice = newPrice;
        lastUpdated = LocalDateTime.now();
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPriceValue() {
        return currentPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[symbol=" + symbol + ", price=" + String.format("%.2f", currentPrice) + ", risk=" + String.format("%.2f", riskScore()) + "]";
    }

    @Override
    public double getPriceChange(double previousPrice) {
        return currentPrice - previousPrice;
    }

    @Override
    public double getPriceChangePercent(double previousPrice) {
        return (currentPrice - previousPrice) * 100 / previousPrice;
    }

    @Override
    public boolean isAvailableForTrading() {
        return true;
    }
}
