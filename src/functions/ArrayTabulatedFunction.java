package functions;

import java.io.Serializable;

public class ArrayTabulatedFunction implements TabulatedFunction, Serializable, Cloneable {
    private FunctionPoint[] points;         //массив точек

    private int NumberPoints;           //кол-во точек в массиве

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount)         //конструктор для заданных границ x и количества точек
    {
        if(leftX >= rightX || pointsCount < 2)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.points = new FunctionPoint[pointsCount + 10];
            double len = (rightX - leftX) / (pointsCount - 1);
            for (int i = 0; i < pointsCount; ++i) {
                this.points[i] = new FunctionPoint(leftX + len * i, 0);
            }
            this.NumberPoints = pointsCount;
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values)             //конструктор для заданных границ x и массива из значений y
    {
        if (leftX >= rightX || values.length < 2)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.points = new FunctionPoint[values.length + 10];
            double len = (rightX - leftX) / (values.length - 1);
            for (int i = 0; i < values.length; ++i) {
                this.points[i] = new FunctionPoint(leftX + len * i, values[i]);
            }
            this.NumberPoints = values.length;
        }
    }


    public ArrayTabulatedFunction(FunctionPoint array[]) {
        if(array.length < 2) throw new IllegalArgumentException();
        for (int i = 0; i < array.length - 1; ++i)
        {
            if(array[i].getX() > array[i+1].getX()) throw  new IllegalArgumentException();
        }
        this.NumberPoints = array.length;
        this.points = new FunctionPoint[array.length];
        for (int i = 0; i < array.length; ++i)
        {
            points[i] = array[i];
        }
    }


    public double getLeftDomainBorder()                 //метод, возвращающий левую крайнюю границу массива точек
    {
        return this.points[0].getX();
    }

    public double getRightDomainBorder()                //метод, возвращаюший правую крайнюю границу массива точек
    {
        return this.points[NumberPoints - 1].getX();
    }

    public double getFunctionValue(double x)           //метод, возвращающий значение функции в точке x
    {
        if ((this.points[0].getX() > x) || (this.points[NumberPoints - 1].getX() < x)) return Double.NaN;
        int i = 0;
        while (this.points[i].getX() < x) ++i;
        double a, b;
        double x1 = this.points[i - 1].getX();
        double x2 = this.points[i].getX();
        double y1 = this.points[i - 1].getY();
        double y2 = this.points[i].getY();
        a = (y2 - y1)/(x2 - x1);
        b = y1 - x1*a;
        return x * a + b;
    }

    public int getPointsCount()        //метод, возвращающий количество точек
    {
        return NumberPoints;
    }

    public FunctionPoint getPoint(int index)       //метод, возвращающий ссылку на объект, описывающий точку с данным номером
    {
        if(index < 0 || index >= NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return this.points[index];
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException    //метод, заменяющий точку с данным индексом на другую
    {
        if(index < 0 || index > NumberPoints)
        {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else
        {
            if (index == 0) {
                if (point.getX() < this.points[index + 1].getX()) {
                    points[index] = new FunctionPoint(point);
                }
                else throw new InappropriateFunctionPointException();
            } else if (index == NumberPoints) {
                if (point.getX() > this.points[index - 1].getX()) {
                    points[index] = new FunctionPoint(point);
                }
                else throw new InappropriateFunctionPointException();
            } else {
                if ((point.getX() > this.points[index - 1].getX()) && (point.getX() < this.points[index + 1].getX())) {
                    points[index] = new FunctionPoint(point);
                }
                else throw new InappropriateFunctionPointException();
            }
        }
    }

    public double getPointX(int index)          //метод, возвращающий значение абсциссы точки с данным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return this.points[index].getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException      //метод,  изменяющий значение абсциссы точки с указанным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else setPoint(index, new FunctionPoint(x, this.points[index].getY()));
    }

    public double getPointY(int index)          //метод, возвращающий значение ординаты точки с данным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return this.points[index].getY();
    }

    public void setPointY(int index, double y)      //метод,  изменяющий значение ординаты точки с указанным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else this.points[index] = new FunctionPoint(this.points[index].getX(), y);
    }

    public void deletePoint(int index)      //метод, удаляющий заданную точку
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();
        else if (this.NumberPoints < 3)
        {
            throw new IllegalStateException();
        }
        else
        {
            System.arraycopy(points, index + 1, points, index, this.points.length - index - 1);
            --NumberPoints;
        }
    }

    public void gotoBigger()        //функция, добавляющая массиву точек 5 ячеек
    {
        FunctionPoint[] old = new FunctionPoint[points.length];
        System.arraycopy(points, 0, old, 0, points.length);

        points = new FunctionPoint[points.length + 5];
        System.arraycopy(old, 0, points, 0, old.length);
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException               //метод, добавляющий новую точку
    {
        if (point.getX() < getLeftDomainBorder())
        {
            if (getPointsCount() == points.length) gotoBigger();
            System.arraycopy(points, 0, points, 1, getPointsCount());
            setPoint(0, point);
            NumberPoints = getPointsCount() + 1;

            return;
        }
        else if(point.getX() == getLeftDomainBorder()) throw new InappropriateFunctionPointException();
        else if (point.getX() > getLeftDomainBorder() && point.getX() < getRightDomainBorder())
        {
            for (int i = 0; i < getPointsCount(); ++i)
            {
                if (point.getX() == getPointX(i)) throw new InappropriateFunctionPointException();
                else if (point.getX() > getPointX(i) && point.getX() < getPointX(i + 1))
                {
                    if (getPointsCount() == points.length) gotoBigger();
                    System.arraycopy(points, i + 1, points, i + 2, getPointsCount() - i - 1);
                    setPoint(i + 1, point);
                    NumberPoints = getPointsCount() + 1;

                    return;
                }
            }
        }

        else if (point.getX() == getRightDomainBorder()) throw new InappropriateFunctionPointException();
        else if(point.getX() > getRightDomainBorder())
        {
            if (getPointsCount() == points.length) gotoBigger();
            setPoint(NumberPoints, point);
            NumberPoints = getPointsCount() + 1;
        }
    }

    @Override
    public String toString(){
        String str = "{ ";
        for(int i = 0; i < NumberPoints; ++i){
            str += points[i].toString();
            if(i != NumberPoints - 1) str += ", ";
        }
        str += " }";
        return str;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof TabulatedFunction)) return false;
        if (o instanceof ArrayTabulatedFunction){
            if(((ArrayTabulatedFunction) o).NumberPoints != this.NumberPoints) return false;
            for(int i = 0; i < NumberPoints; ++i){
                if(!((ArrayTabulatedFunction) o).getPoint(i).equals(this.getPoint(i))) return false;
            }
            return true;
        }
        else {
            if(((TabulatedFunction) o).getPointsCount() != this.getPointsCount()) return false;
            for(int i = 0; i < NumberPoints; ++i){
                if(!((TabulatedFunction) o).getPoint(i).equals(this.getPoint(i))) return false;
            }
            return true;
        }
    }

    @Override
    public int hashCode(){
        int result = this.NumberPoints;
        for(int i = 0; i < this.NumberPoints; ++i){
            result += this.getPoint(i).hashCode() * 29;
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        //int size = this.getPointsCount();
        FunctionPoint array[] = new FunctionPoint[this.NumberPoints];
        for(int i = 0; i < this.NumberPoints; ++i){
            array[i] = new FunctionPoint(this.getPoint(i));
        }
        ArrayTabulatedFunction curr = new ArrayTabulatedFunction(array);
        return curr;
        //return super.clone();
    }

    public void print() {
        for (int i = 0; i < NumberPoints; i++) {
            System.out.println("(" + points[i].getX() + " ; " + points[i].getY() + ")");
        }
    }
}
