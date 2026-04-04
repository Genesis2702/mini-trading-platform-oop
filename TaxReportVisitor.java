public class TaxReportVisitor implements InstrumentVisitor {
    private double totalTaxLiability;
    private double stockTax;
    private double bondTax;
    private double optionTax;
    private double futureTax;

    public void visit(Stock stock) {
        totalTaxLiability += 0.15 * stock.getCurrentPriceValue();
        stockTax += 0.15 * stock.getCurrentPriceValue();
    }

    public void visit(Bond bond) {
        totalTaxLiability += 0.3 * bond.getCurrentPriceValue() * bond.getCouponRate() / 100;
        bondTax += 0.3 * bond.getCurrentPriceValue() * bond.getCouponRate() / 100;
    }

    public void visit(Option option) {
        totalTaxLiability += 0.2 * option.getCurrentPriceValue();
        optionTax += 0.2 * option.getCurrentPriceValue();
    }

    public void visit(Future future) {
        totalTaxLiability += 0.2 * future.getCurrentPriceValue();
        futureTax += 0.2 * future.getCurrentPriceValue();
    }

    public double getTotalTaxLiability() {
        return totalTaxLiability;
    }

    public String getReport() {
        return "Report[Stock tax= " + String.format("%.2f", stockTax) + ", Bond tax=" + String.format("%.2f", bondTax) + ", Option tax=" + String.format("%.2f", optionTax) + ", Future tax=" + String.format("%.2f", futureTax) + ", Total tax liability=" + String.format("%.2f", totalTaxLiability) + "]";
    }
}
