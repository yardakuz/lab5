package functions.meta;

import functions.Function;

public class Sum implements Function {
    private Function func1;
    private Function func2;

    public Sum(Function curr1, Function curr2) {
        this.func1 = curr1;
        this.func2 = curr2;
    }


    public double getLeftDomainBorder() {
        return Math.max(func1.getLeftDomainBorder(), func2.getLeftDomainBorder());
    }

    public double getRightDomainBorder() {
        return Math.min(func1.getRightDomainBorder(), func2.getRightDomainBorder());
    }

    public double getFunctionValue(double x) {
        return (func1.getFunctionValue(x) + func2.getFunctionValue(x));
    }
}
