import java.util.*;

public class RiskAnalyzer<T extends Instrument> {
    private final List<T> instruments = new ArrayList<>();

    public void add(T instrument) {
        instruments.add(instrument);
    }

    public double averageRisk() {
        double avgRiskScore = 0.0;
        for (T instrument : instruments) {
            avgRiskScore += instrument.riskScore();
        }
        return avgRiskScore / instruments.size();
    }

    public T highestRisk() {
        T highestInstrument = null;
        double highestRiskScore = -1e9;
        for (T instrument : instruments) {
            if (instrument.riskScore() > highestRiskScore) {
                highestInstrument = instrument;
                highestRiskScore = instrument.riskScore();
            }
        }
        return highestInstrument;
    }

    public T lowestRisk() {
        T lowestInstrument = null;
        double lowestRiskScore = 1e9;
        for (T instrument : instruments) {
            if (instrument.riskScore() < lowestRiskScore) {
                lowestInstrument = instrument;
                lowestRiskScore = instrument.riskScore();
            }
        }
        return lowestInstrument;
    }

    public List<T> getAboveRiskThreshold(double threshold) {
        List<T> aboveRiskThreshold = new ArrayList<>();
        for (T instrument : instruments) {
            if (instrument.riskScore() > threshold) {
                aboveRiskThreshold.add(instrument);
            }
        }
        return aboveRiskThreshold;
    }
}
