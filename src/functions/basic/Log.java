package functions.basic;

import functions.Function;

public class Log implements Function {

    private double base;

    public Log(double currBase){
        this.base = currBase;
    }
    public double getLeftDomainBorder() {
        return 0;
    }

    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    public double getFunctionValue(double x) {
        return Math.log(x) / Math.log(base);
    }
}
