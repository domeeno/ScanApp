import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static String User_name = "Domo";
    private static String User_pass = "1234";
    private static String Db_name = "Restaurant";
    static Context mcontext;
    private static Connection Conn = null;
    private static String Server_ip = "192.168.5.14:1433";

    public static Connection Getconnection() {

        Log.i("Android", " MySQL Connect.");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String connString;
        try {
            Log.i("SQL Looking", "Start Looking for server");
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            connString = "jdbc:jtds:sqlserver://" + Server_ip
                    + ";databaseName=" + Db_name + ";user=" + User_name
                    + ";password=" + User_pass + ";";

            Conn = DriverManager.getConnection(connString);
            Log.i("Connection", "open DB Class");
        } catch (Exception e) {
            Toast.makeText(mcontext, e.toString() + "db", Toast.LENGTH_LONG)
                    .show();
            Log.w("Error connection", e.getMessage());

        }
        return Conn;
    }

    public static void close_DB() {

        try {
            Conn.close();
            Log.i("Connection", "Close DB Class");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}