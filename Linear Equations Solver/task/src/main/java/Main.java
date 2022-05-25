import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String exportfilename;
    public static void main(String[] args) {
        String infilename = "";
        for(int i = 0; i < args.length; i+=2) {
            if(args[i].equals("-in")) {
                infilename = args[i+1];
            }
            if(args[i].equals("-out")){
                exportfilename = args[i+1];
                //System.out.println("Saved to file " + args[i+1]);
            }
        }
        //importfile("in.txt"); exportfile("out.txt");
        importfile(infilename); exportfile(exportfilename);
    }
    private static void importfile(String filename){
        try {
            File myObj = new File(filename);
            String path = myObj.getCanonicalPath();
            File filepath = new File(path);

            Scanner myReader = new Scanner(myObj);
            int numofrows = 0;
            int numofcolumns = 0;
            List<List<String>> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] Data = data.split(" ");
                if(Data.length == 2){
                    int numofvars = Integer.parseInt(Data[0]);
                    numofcolumns = numofvars + 1;
                    numofrows = Integer.parseInt(Data[1]);
                }else {
                    List<String> lst = new ArrayList<>(Arrays.asList(Data));
                    list.add(lst);
                }
            }
            new Matrix(list, numofrows, numofcolumns);
            myReader.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void exportfile(String filename){
        try {
            FileWriter myWriter = new FileWriter(filename);
            String[] solution = Matrix.getMatrixSolution();
            //System.out.println(Arrays.toString(solution));
            for(int i = 0; i < solution.length; i++) {
                myWriter.write(solution[i] + "\n");
            }
            myWriter.close();
            System.out.println("Writer closed");
            System.out.println("Saved to file " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}