package functions.meta;

import functions.Function;

public class Composition implements Function {
    private Function func1;
    private Function func2;

    public Composition(Function curr1, Function curr2){
        this.func1 = curr1;
        this.func2 = curr2;
    }

    public double getLeftDomainBorder() {
        return func1.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return func1.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return func1.getFunctionValue(func2.getFunctionValue(x));
    }
}
