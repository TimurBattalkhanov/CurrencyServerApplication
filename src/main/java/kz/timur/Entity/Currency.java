package kz.timur.Entity;

public class Currency {

    private int id;
    private long date;
    private double value;

    public Currency(){
    }

    public Currency(long date, double value) {
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
