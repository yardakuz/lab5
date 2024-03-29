package functions;

import java.io.Serializable;
import java.util.Objects;

public class FunctionPoint implements Serializable, Cloneable {
    private double x;       //значчение x в точке
    private double y;       //значение y в точке
    public FunctionPoint(double x, double y)    //конструктор со значениями
    {
        this.x = x;
        this.y = y;
    }
    public FunctionPoint(FunctionPoint point)       //конструктор копирования
    {
        this.x = point.getX();
        this.y = point.getY();
    }
    public FunctionPoint()          //конструктор по умолчанию
    {
        this.x = 0;
        this.y = 0;
    }

    public double getX()
    {
        return x;
    }       //геттер для x

    public double getY()
    {
        return y;
    }       //геттер для y

    public void setX(double currX){
        this.x = currX;
    }

    public void setY(double currY){
        this.y = currY;
    }

    @Override
    public String toString(){
        return ("(" + this.x + ";" + this.y + ")");
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof FunctionPoint && ((FunctionPoint) o).getX() == this.getX() && ((FunctionPoint) o).getY() == this.getY());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.x*29, this.y);
        //Double x1 = x, y1 = y;
        //return x1.hashCode()*31+y1.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        FunctionPoint curr = new FunctionPoint(this.x, this.y);
        return curr;
        //return super.clone();
    }

}