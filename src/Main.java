import functions.*;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
    FunctionPoint point1 = new FunctionPoint(1, 1);
    Object point2 = point1.clone();
        System.out.println(point1.equals(point1));
        System.out.println(point2.toString());
        System.out.println(point1.toString());
        System.out.println(point1.hashCode() == point2.hashCode());
        System.out.println(point2.equals(point1));
    }
}