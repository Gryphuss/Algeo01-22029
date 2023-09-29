package IO;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import Matriks.*;

public class IOput{
    public void read(Matrix mm){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
            Scanner readFile = new Scanner(file);
            int i,j;
            i = 0;
            j = 0;
            mm.setRow(1);
            mm.setCol(1);
            while (readFile.hasNext()) {
                String cc = readFile.nextLine();
                String[] mString = cc.split("\n");
                for(String num : mString){
                    String [] mmString = num.split(" ");
                    for(String numnum : mmString){
                        double f = Double.parseDouble(numnum);
                        mm.mem[i][j] = f;
                        j++;
                    }
                    mm.setCol(j);
                    j = 0;
                    i++;
                }
                mm.setRow(i); 
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    public void readKeyboard(Matrix m){
        for(int i = 0 ; i < m.getRow(); i ++){
            int j;
            j = 0;
            Scanner obj = new Scanner(System.in);
            String strRow = obj.nextLine();
            String[] Row = strRow.split(" ");
            for(String num : Row){
                double d = Double.parseDouble(num);
                m.mem[i][j] = d;
                j ++ ;
            }
        }
    }

    public Matrix setBicubic(Matrix m){
        if(inputMode());
    }

    public void writeMatrixString(Matrix m, String str){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File(nameFile);
            file.createNewFile();
            FileWriter cc = new FileWriter(nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Double.toString(m.mem[i][j]);
                    cc.write(num);
                    cc.write(" ");
                }
                cc.write("\n");
            }
            cc.write(str);
            cc.write("\n");
            cc.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public void writeMatrix(Matrix m){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File(nameFile);
            file.createNewFile();
            FileWriter cc = new FileWriter(nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Double.toString(m.mem[i][j]);
                    cc.write(num);
                    cc.write(" ");
                }
                cc.write("\n");
            }
            cc.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
