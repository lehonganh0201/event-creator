package JDBCManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    public static Connection getConnection(){
        Connection connection=null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url="jdbc:mySQL://localhost:1433/eventcreatordb";
            String userName="root";
            String password="123";

            connection=DriverManager.getConnection(url,userName,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection c){
        try {
            if (c!=null){
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
