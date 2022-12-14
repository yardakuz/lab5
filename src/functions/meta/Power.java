package functions.meta;

import functions.Function;

public class Power implements Function {

    private Function func;
    double power;

    public Power(Function curr1, double n){
        this.func = curr1;
        this.power = n;
    }
    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return func.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(func.getFunctionValue(x), power);
    }
}
