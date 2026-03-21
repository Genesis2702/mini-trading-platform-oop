public class TaxReportVisitor implements InstrumentVisitor {
    private double totalTaxLiability;

    public void visit(Stock stock) {
        totalTaxLiability += 0.15 * stock.getCurrentPriceValue();
    }

    public void visit(Bond bond) {
        totalTaxLiability += 0.3 * bond.getCurrentPriceValue() * bond.getCouponRate() / 100;
    }

    public void visit(Option option) {
        totalTaxLiability += 0.2 * option.getCurrentPriceValue();
    }

    public void visit(Future future) {
        totalTaxLiability += 0.2 * future.getCurrentPriceValue();
    }

    public double getTotalTaxLiability() {
        return totalTaxLiability;
    }

    public String getReport() {
        return "Report[Total tax liability=" + totalTaxLiability + "]";
    }
}
