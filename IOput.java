package packageIOput;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import packageMatrix.Matrix;

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
                        float f = Float.parseFloat(numnum);
                        mm.insertElmt(f,i,j);
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

    public void write(Matrix m){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File(nameFile);
            file.createNewFile();
            FileWriter cc = new FileWriter(nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Float.toString(m.ELMT(i,j));
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