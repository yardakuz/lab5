package functions.meta;

import functions.Function;

public class Shift implements Function {
    private Function func;
    private double xShift;
    private double yShift;

    public Shift(Function curr, double x, double y){
        this.func = curr;
        this.xShift = x;
        this.yShift = y;
    }

    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder() + xShift;
    }

    public double getRightDomainBorder() {
        return func.getRightDomainBorder() + xShift;
    }

    public double getFunctionValue(double x) {
        return func.getFunctionValue(x - xShift) + yShift;
    }
}
