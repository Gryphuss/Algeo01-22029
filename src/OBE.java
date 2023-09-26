import java.util.*;

public class OBE {
    public static void Gauss(Matrix m){

        int rowLead = 0;
        for(int i=0;i<m.getCol();i++){
            boolean col0 = true;
            for(int j=rowLead;j<m.getRow();j++){
                if(m.mem[j][i]!=0){
                    m.swapRow(rowLead, j);
                    col0 = false;
                    break;
                }
            }
            if(col0) continue;
            m.bagiRow(rowLead, m.mem[rowLead][i]);
            for(int j=rowLead+1;j<m.getRow();j++){
                m.tambahRow(j,rowLead,-m.mem[j][i]);
            }
            rowLead++;
        }
    }

    public static void GaussJordan(Matrix m){
        Gauss(m);
        for(int i = m.getRow()-1;i>=0;i--){
            int colLead = m.findColLead1(i);
            if(colLead==-1) continue;
            for(int j=i-1;j>=0;j--){
                m.tambahRow(j, i, -m.mem[j][colLead]);
            }
        }
    }
}
