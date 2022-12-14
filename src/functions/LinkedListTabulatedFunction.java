package functions;

import java.io.Serializable;

public class LinkedListTabulatedFunction implements TabulatedFunction, Serializable {
    private static class FunctionNode {

        private FunctionPoint point;
        private FunctionNode prev;
        private FunctionNode next;
    }

    private int NumberPoints;
    private FunctionNode head;

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        if(leftX >= rightX || pointsCount < 2) throw new IllegalArgumentException();

        else
        {
            this.head = new FunctionNode();
            this.head.next = head;
            this.head.prev = head;
            double len = (rightX - leftX) / (pointsCount - 1);
            for (int i = 0; i < pointsCount; ++i) {
                this.addNodeByIndex(i).point = new FunctionPoint(leftX + len * i, 0);
            }
            this.NumberPoints = pointsCount;
        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values)
    {
        if (leftX >= rightX || values.length < 2) throw new IllegalArgumentException();
        else
        {
            this.head = new FunctionNode();
            this.head.next = head;
            this.head.prev = head;
            double len = (rightX - leftX) / (values.length - 1);
            for (int i = 0; i < values.length; ++i) {
                this.addNodeByIndex(i).point = new FunctionPoint(leftX + len * i, values[i]);
            }
            this.NumberPoints = values.length;
        }
    }

    public LinkedListTabulatedFunction(FunctionPoint array[])
    {
        if(array.length < 2) throw new IllegalArgumentException();
        for (int i = 0; i < array.length - 1; ++i)
        {
            if(array[i].getX() > array[i+1].getX()) throw  new IllegalArgumentException();
        }
        this.NumberPoints = array.length;
        this.head = new FunctionNode();
        this.head.prev = head;
        this.head.next = head;
        for (int i = 0; i < array.length; ++i)
        {
            this.addNodeByIndex(i).point = new FunctionPoint(array[i].getX(), array[i].getY());
        }
    }

    public FunctionNode getNodeByIndex(int index)       //метод, возвращающий ссылку на объект элемента списка по его номеру
    {
        FunctionNode current = head;
        while (index-- >= 0) current = current.next;
        return current;
    }

    public FunctionNode addNodeToTail()     //метод, добавляющий новый элемент в конец списка и возвращающий ссылку на объект этого элемента.
    {
        FunctionNode node = new FunctionNode();
        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
        ++NumberPoints;
        return node;
    }

    public FunctionNode addNodeByIndex (int index)          //метод, добавляющий новый эл-т списка в указ. позицию и возвращающий ссылку на объект этого эл-та
    {
        FunctionNode node = new FunctionNode();
        FunctionNode current = getNodeByIndex(index);
        node.next = current;
        node.prev = current.prev;
        current.prev.next = node;
        current.prev = node;
        ++NumberPoints;
        return node;
    }

    public FunctionNode insertAfterNode(FunctionNode current)
    {
        FunctionNode node = new FunctionNode();
        node.prev = current;
        node.next = current.next;
        current.next.prev = node;
        current.next = node;
        ++NumberPoints;
        return node;
    }


    public FunctionNode deleteNodeByIndex (int index)
    {
        FunctionNode node = getNodeByIndex(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        --NumberPoints;
        return node;
    }

    public double getLeftDomainBorder()                 //метод, возвращающий левую крайнюю границу массива точек
    {
        return this.head.next.point.getX();
    }

    public double getRightDomainBorder()                //метод, возвращаюший правую крайнюю границу массива точек
    {
        return this.head.prev.point.getX();
    }

    public double getFunctionValue(double x)       //метод, возвращающий значение функции в точке x
    {
        if ((this.head.prev.point.getX() > x) || (this.head.next.point.getX() < x)) return Double.NaN;

        FunctionNode curr = head.next;
        while (curr != head)
        {
            if (curr.point.getX() == x)
                return curr.point.getY();

            if (x >= curr.point.getX() && x <= curr.next.point.getX())
            {
                FunctionPoint left = curr.point;
                FunctionPoint right = curr.next.point;
                double k = (right.getY() - left.getY()) / (right.getX() - left.getX());
                double b = right.getY() - k * right.getX();
                return k * x + b;
            }
            curr = curr.next;
        }
        return Double.NaN;
    }

    public int getPointsCount()        //метод, возвращающий количество точек
    {
        return NumberPoints;
    }

    public FunctionPoint getPoint(int index)       //метод, возвращающий ссылку на объект, описывающий точку с данным номером
    {
        if(index < 0 || index >= NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return getNodeByIndex(index).point;
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException    //метод, заменяющий точку с данным индексом на другую
    {
        if(index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else
        {
            FunctionNode curr = getNodeByIndex(index);
            if (index == 0) {
                if (point.getX() < getNodeByIndex(index + 1).point.getX()) {
                    curr.point = point;
                }
                else throw new InappropriateFunctionPointException();
            } else if (index == NumberPoints) {
                if (point.getX() > getNodeByIndex(index - 1).point.getX()) {
                    curr.point = point;
                }
                else throw new InappropriateFunctionPointException();
            } else {
                if ((point.getX() > getNodeByIndex(index - 1).point.getX()) && (point.getX() < getNodeByIndex(index + 1).point.getX())) {
                    curr.point = point;
                }
                else throw new InappropriateFunctionPointException();
            }
        }
    }

    public double getPointX(int index)          //метод, возвращающий значение абсциссы точки с данным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return getPoint(index).getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException      //метод,  изменяющий значение абсциссы точки с указанным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else setPoint(index, new FunctionPoint(x, this.getNodeByIndex(index).point.getY()));
    }

    public double getPointY(int index)          //метод, возвращающий значение ординаты точки с данным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else return getPoint(index).getY();
    }

    public void setPointY(int index, double y) throws InappropriateFunctionPointException      //метод,  изменяющий значение ординаты точки с указанным номером
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();

        else setPoint(index, new FunctionPoint(this.getNodeByIndex(index).point.getX(), y));
    }

    public void deletePoint(int index)      //метод, удаляющий заданную точку
    {
        if (index < 0 || index > NumberPoints) throw new FunctionPointIndexOutOfBoundsException();
        else if (this.NumberPoints < 3) throw new IllegalStateException();
        else
        {
            deleteNodeByIndex(index);
            --NumberPoints;
        }
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException               //метод, добавляющий новую точку
    {
       FunctionNode pos = head;
       FunctionNode curr = head.next;
       while (curr != head)
       {
           if (point.getX() == curr.point.getX()) throw new InappropriateFunctionPointException();
           if (point.getX() > curr.point.getX())
           {
               pos = curr;
           }
           curr = curr.next;
       }
        insertAfterNode(pos).point = point;
    }
    public void print()
    {
        FunctionNode node = head;
        System.out.println();

        while ((node = node.next) != head)
        {
            System.out.println("(" + node.point.getX() + " ; " + node.point.getY() + ")");
        }

    }
}
