package DB;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
 
public class OracleTest 
{
    public static void main(String args[])
    {
        Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
        	String sql = "MERGE INTO TEST03.TBL_LT_INF T3 "
 				   + "USING TEST02.TBL_LT_INF T2 "
 				   + "ON (T3.FA_ID = T2.FA_ID AND T3.LT_ID = T2.LT_ID AND T3.PROD_ID = T2.PROD_ID) "
 				   + "WHEN MATCHED THEN "
 				   + "UPDATE SET T3.FL_ID = T3.FL_ID, T3.OP_ID = T3.OP_ID, T3.TIMEKEY = T3.TIMEKEY, T3.CHG_TM = T3.CHG_TM, T3.CRT_TM = T3.CRT_TM "
 				   + "WHEN NOT MATCHED THEN "
 				   + "INSERT(T3.FA_ID, T3.LT_ID, T3.PROD_ID, T3.FL_ID, T3.OP_ID, T3.TIMEKEY, T3.CHG_TM, T3.CRT_TM) "
 				   + "VALUES(T2.FA_ID, T2.LT_ID, T2.PROD_ID, T2.FL_ID, T2.OP_ID, T2.TIMEKEY, T2.CHG_TM, T2.CRT_TM)";
        	
        	conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
			System.out.println("1. JDBC Driver �ε� �Ϸ�");			
		    System.out.println("2. Oracle ���� �Ϸ�");		    
		    System.out.println(sql);
		    
			pstm = conn.prepareStatement(sql);
			pstm.executeQuery();
			System.out.println("3. SQL ���� �Ϸ�");
			
			pstm.close();
		    conn.close();
        } catch (SQLException sqle) {
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();
            
        }finally{
            // DB ������ �����Ѵ�.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
    }
}

