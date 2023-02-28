import functions.*;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InappropriateFunctionPointException {
//    FunctionPoint point1 = new FunctionPoint(1, 1);
//    Object point2 = point1.clone();
//        System.out.println(point1.equals(point1));
//        System.out.println(point2.toString());
//        System.out.println(point1.toString());
//        System.out.println(point1.hashCode() == point2.hashCode());
//        System.out.println(point2.equals(point1));
//        FunctionPoint testpoint1 = new FunctionPoint(1, 2);
//        FunctionPoint testpoint2 = new FunctionPoint(1.1, 2);
//        System.out.println(testpoint1.hashCode());
//        System.out.println(testpoint2.hashCode());
//
//    ArrayTabulatedFunction fa1 = new ArrayTabulatedFunction(0, 10, 10);
//    fa1.setPointX(1, 1);
//    //f1.print();
//    Object fa2 = fa1.clone();
//    //((ArrayTabulatedFunction) f2).print();
//        System.out.println(fa1.equals(fa1));
//        System.out.println(fa2.equals(fa2));
//        System.out.println(fa1.equals(fa2));
//        System.out.println(fa1.hashCode());
//        System.out.println(fa2.hashCode());
//        System.out.println(fa2.toString());

    LinkedListTabulatedFunction fl1 = new LinkedListTabulatedFunction(0, 10, 5);
    Object fl2 = fl1.clone();
    System.out.println(fl1.toString());
        //System.out.println(fl2.toString());
        System.out.println(fl1.equals(fl2));
        System.out.println(fl1.hashCode());
        System.out.println(fl2.hashCode());



//        FunctionPoint point1 = new FunctionPoint(1, 1);
//        Object point2 = point1.clone();
//        point1.setX(2);
//        System.out.println(point1.toString());
//        System.out.println(point2.toString());

    }
}