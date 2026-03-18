public class Option extends Derivative {
    private final double strikePrice;
    private final boolean isCall;
    private final int expiryDays;

    public Option(String symbol, String name, double currentPrice, double strikePrice, boolean isCall, int expiryDays) {
        super(symbol, name, currentPrice);
        this.strikePrice = strikePrice;
        this.isCall = isCall;
        this.expiryDays = expiryDays;
    }

    @Override
    public double riskScore() {
        return 8.5;
    }
    
    public boolean isInTheMoney(double spotPrice) {
        if (isCall) {
            return spotPrice > strikePrice;
        }
        return spotPrice < strikePrice;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public boolean isCall() {
        return this.isCall;
    }

    public int getExpiryDays() {
        return expiryDays;
    }
}
