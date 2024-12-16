package ratemate;

public class DataRow {
    private int gender;
    private int prodYear;
    private double premium;

    public DataRow(int gender, int prodYear, double premium) {
        this.gender = gender;
        this.prodYear = prodYear;
        this.premium = premium;

    }

    public int getgender() {
        return gender;
    }

    public int getprodYear() {
        return prodYear;
    }

    public double getpremium() {
        return premium;
    }

    // Method to return the target class ('high' or 'low') based on premium threshold
    public String getClassLabel() {
        return premium > 6000 ? "high" : "low";
    }

    @Override
    public String toString() {
        return "DataRow{" +
                "gender='" + gender + '\'' +
                ", prodYear='" + prodYear + '\'' +
                ", premium='" + premium + '}';
    }
}
