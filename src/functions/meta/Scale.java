package functions.meta;

import functions.Function;

public class Scale implements Function {
    private Function func;
    private double xScale;
    private double yScale;

    public Scale(Function curr1, double x, double y){
        this.func = curr1;
        this.xScale = x;
        this.yScale = y;
    }

    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder() * xScale;
    }

    public double getRightDomainBorder() {
        return func.getRightDomainBorder() * xScale;
    }

    public double getFunctionValue(double x) {
        return func.getFunctionValue(x/xScale) * yScale;
    }
}
