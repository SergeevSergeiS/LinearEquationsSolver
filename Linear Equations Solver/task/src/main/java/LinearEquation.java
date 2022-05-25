public class LinearEquation {

    public static Complex[][] solveLinearEquation(Complex[][] matrix, int numofrows){
        for(int i = 0; i < numofrows; i++){
            printarr(matrix);
            Row row = new Row();
            matrix = row.rowManipulation(matrix, i);
        }
        if(Matrix.str.equals("")) {
            for (int i = numofrows - 1; i > 0; i--) {
                matrix = backSub(matrix, i);
            }
            if(Row.columnSwaps.size() != 0){
                matrix = Row.swapback(matrix);
            }
        }
        Matrix.checkSolutions(matrix);
        return matrix;
    }

    private static Complex[][] backSub(Complex[][] matrix, int i){
        Complex[] row = matrix[i];
        for(int a = i - 1; a >= 0; a--) {
            Complex[] prevrow = matrix[a];
            Complex[] temprow = new Complex[row.length];
            Complex coeff = Complex.Multiplication(-1, prevrow[i]);
            for (int n = 0; n < row.length; n++) {
                temprow[n] = Complex.Multiplication(row[n], coeff);
            }
            for (int n = 0; n < row.length; n++) {
                prevrow[n] = Complex.Addition(prevrow[n], temprow[n]);
            }
            matrix[a] = prevrow;
            System.out.println(coeff + " * R" + (i + 1) + " + R" + (a+1) + " -> R" + (a+1));
        }
        return matrix;
    }

    public static void printarr(Complex[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(String.format("%.1f", matrix[i][j].real) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}