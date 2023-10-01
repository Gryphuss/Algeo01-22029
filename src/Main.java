import java.util.*;
import Aplikasi.*;
import IO.*;
import Matriks.*;

public class Main {
    public static int mainMenu(){
        Scanner obj = new Scanner(System.in);
        System.out.println("------------MAIN MENU BEAULO------------");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Scalling Image");
        System.out.println("8. Keluar Kalkulator");
        System.out.print("->");
        int pilihan = obj.nextInt();
        while(pilihan<1 || pilihan>8){
            System.out.println("Input tidak sesuai! Silahkan coba lagi.");
            System.out.print("->");
            pilihan = obj.nextInt();
        }

        return pilihan;
    }

    public static void main(String[] args){
        System.out.println("Selamat Datang di Kalkulator Matrix Beaulo");
        while(true){
            int menu = mainMenu();
            if(menu==1){
                SPL.menuSPL();
            }else if(menu==2){

            }else if(menu==3){

            }else if(menu==4){
                Interpolasi.mainMenu();
            }else if(menu==5){
                Bicubic.bicubic();
            }else if(menu==6){
                RLB.menuRLB();
            }else if(menu==7){

            }else{
                System.out.println("Terima Kasih Telah Menggunakan Layanan");
                System.out.println("--------Kalkulator BEAULO------------");
                break;
            }
        }
    }
}
