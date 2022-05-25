import java.util.Arrays;
import java.util.List;
import static java.lang.Math.round;

public class Matrix {
    public static Complex[][] matrix;
    public static int numofrows;
    public static int numofcolumns;
    public static String str = "";
    Matrix(List<List<String>> list, int numofrows, int numofcolumns){
        Matrix.numofrows = numofrows;
        Matrix.numofcolumns = numofcolumns;
        matrix = new Complex[numofrows][numofcolumns];
        formMatrix(list);
        calculations();
        //System.out.println(Arrays.deepToString(matrix));
    }
    private void formMatrix(List<List<String>> list){
        for(int i = 0; i < numofrows; i++){
            for (int j = 0; j < numofcolumns; j++){
                matrix[i][j] = new Complex(list.get(i).get(j));
            }
        }
    }
    private void calculations(){
        System.out.println("Start solving the equation");
        matrix = LinearEquation.solveLinearEquation(matrix, numofrows);
    }
    private static Complex[] MatrixSoln(){
        Complex[] solution = new Complex[numofcolumns - 1];
        for(int i = 0; i < numofrows; i++){
            for(int j = 0; j < numofcolumns -1; j++){
                if(Complex.roundCompare(matrix[i][j], 1)){
                    solution[j] = matrix[i][numofcolumns - 1];
                }
            }
        }
        System.out.println("The solution is: " + Arrays.toString(solution));
        return solution;
    }
    public static String[] getMatrixSolution() {
        if(str.equals("")){
            return Arrays.stream(MatrixSoln()).map(Complex::toString).toArray(String[]::new);
        }
        return new String[]{str};
    }

    public static void checkSolutions(Complex[][] matrix){

        int numofsigeqns = 0;
        for(int i = 0; i < numofrows; i++){
            int numofzeroes = 0;
            for(int j = 0; j < numofcolumns; j++){
                if(Complex.Compare(matrix[i][j], 0)){
                    numofzeroes++;
                }
            }
            if(numofzeroes != numofcolumns){
                numofsigeqns++;
            }
        }
        //System.out.println(numofsigeqns);
        if(numofsigeqns < numofcolumns - 1){
            //System.out.println(numofsigeqns);
            System.out.println("Infinitely many solutions");
            str = "Infinitely many solutions";
        }

        for(Complex[] rows : matrix){
            int numofzeroes = 0;
            for(int i = 0; i < rows.length; i++){
                if(Complex.Compare(rows[i], 0)){
                    numofzeroes++;
                }
            }
            if(numofzeroes == rows.length - 1 && !Complex.Compare(rows[rows.length - 1], 0)){
                System.out.println("No solutions");
                str = "No solutions";
                break;
            }
        }

    }
}
