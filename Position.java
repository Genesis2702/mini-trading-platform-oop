public class Position {
    private final Instrument instrument;
    private int quantity;
    private double averageCostBasis;

    public Position(Instrument instrument, int quantity, double averageCostBasis) {
        this.instrument = instrument;
        this.quantity = quantity;
        this.averageCostBasis = averageCostBasis;
    }

    public double marketValue() {
        return quantity * instrument.getCurrentPriceValue();
    }

    public double unrealizedPnL() {
        return marketValue() - quantity * averageCostBasis;
    }

    public void addQuantity(int qty, double costBasis) {
        this.averageCostBasis = (this.quantity * this.averageCostBasis + qty * costBasis) / (this.quantity + qty);
        this.quantity += qty;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAverageCostBasis() {
        return averageCostBasis;
    }

    @Override
    public String toString() {
        return "Position[symbol=" + getInstrument().getSymbol() + ", qty=" + quantity + ", value=" + String.format("%.2f", marketValue()) + ", pnl=" + String.format("%.2f", unrealizedPnL()) + "]";
    }
}
