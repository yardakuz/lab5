package functions;

import java.io.*;

public class TabulatedFunctions {
    private TabulatedFunctions(){

    }

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount){
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()) throw new IllegalArgumentException();

        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double len = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i) {
            double x = leftX + len * i;
            points[i] = new FunctionPoint(x, function.getFunctionValue(x));
        }
        return new ArrayTabulatedFunction(points);
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out){
        try (DataOutputStream output = new DataOutputStream(out)){
            output.writeInt(function.getPointsCount());

            for(int i = 0; i < function.getPointsCount(); ++i){
                //output.write('(');
                output.writeDouble(function.getPointX(i));
                //output.write(';');
                output.writeDouble(function.getPointY(i));
               // output.write(')');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in){
        try(DataInputStream input = new DataInputStream(in)) {
            int NumberPoints = input.readInt();
            FunctionPoint[] points = new FunctionPoint[NumberPoints];
            for(int i = 0; i < NumberPoints; ++i){
                points[i] = new FunctionPoint(input.readDouble(), input.readDouble());
            }
            return new ArrayTabulatedFunction(points);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out){
        try (BufferedWriter writer = new BufferedWriter(out)) {
            int NumberPoints = function.getPointsCount();
            writer.write(NumberPoints + " ");
            for (int i = 0; i < NumberPoints; i++) {
                writer.write(function.getPointX(i) + " " + function.getPointY(i) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static TabulatedFunction readTabulatedFunction(Reader in){
        try {
            StreamTokenizer st = new StreamTokenizer(in);
            st.nextToken();
            int NumberPoints = (int) st.nval;
            FunctionPoint[] points = new FunctionPoint[NumberPoints];
            for (int i = 0; st.nextToken() != StreamTokenizer.TT_EOF; i++) {
                double x = st.nval;
                st.nextToken();
                double y = st.nval;
                points[i] = new FunctionPoint(x, y);
            }
            return new ArrayTabulatedFunction(points);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
