import java.util.*;

public class Portfolio implements Observable<String> {
    private final String portfolioId;
    private final String ownerName;
    private final List<Position> positions;
    private final List<Observer<String>> observers;

    public Portfolio(String portfolioId, String ownerName) {
        this.portfolioId = portfolioId;
        this.ownerName = ownerName;
        this.positions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addPosition(Instrument inst, int qty, double costBasis) {
        for (Position position : positions) {
            if (position.getInstrument().getSymbol().equals(inst.getSymbol())) {
                position.addQuantity(qty, costBasis);
                notifyObservers("ADDED: " + inst.getSymbol() + " x" + qty);
                return;
            }
        }
        positions.add(new Position(inst, qty, costBasis));
        notifyObservers("ADDED: " + inst.getSymbol() + " x" + qty);
    }

    public void removePosition(String symbol) throws PositionNotFoundException {
        for (Position position : positions) {
            if (position.getInstrument().getSymbol().equals(symbol)) {
                positions.remove(position);
                notifyObservers("REMOVED: " + symbol);
                return;
            }
        }
        throw new PositionNotFoundException(symbol);
    }

    public double totalMarketValue() {
        double sum = 0;
        for (Position pos : positions) {
            sum += pos.marketValue();
        }
        return sum;
    }

    public double totalUnrealizedPnL() {
        double sum = 0;
        for (Position position : positions) {
            sum += position.unrealizedPnL();
        }
        return sum;
    }

    public Position getPosition(String symbol) throws PositionNotFoundException {
        for (Position position : positions) {
            if (position.getInstrument().getSymbol().equals(symbol)) {
                return position;
            }
        }
        throw new PositionNotFoundException(symbol);
    }

    public List<Position> getPositionsSortedByValue() {
        List<Position> sortedPositions = new ArrayList<>(positions);
        sortedPositions.sort((a, b) -> Double.compare(b.marketValue(), a.marketValue()));
        return sortedPositions;
    }

    public Map<String, Double> allocationByAssetClass() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public void revalueAll(PricingStrategy strategy) {
        for (Position position : positions) {
            double newPrice = strategy.calculateFairValue(position.getInstrument());
            position.getInstrument().updatePrice(newPrice);
        }
        notifyObservers("REVALUED: " + strategy.strategyName());
    }

    @Override
    public void addObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer<String> observer : observers) {
            observer.onEvent(event);
        }
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
