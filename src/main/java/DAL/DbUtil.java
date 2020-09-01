package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/hotel_real";
    private static String user = "root";
    private static String password = "huehue1123";

    private DbUtil() {
        throw new IllegalStateException("Can't init DbUtil instance!");
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    public static Connection getConnection(final String url)
            throws SQLException {
        DbUtil.url = url;
        DbUtil.user = user;
        DbUtil.password = password;
        return getConnection();
    }

    public static boolean executeStament(final String sql) {
        boolean executeResult = false;
        try (Statement statement = DbUtil.getConnection().createStatement()) {
            executeResult = true;
        } catch (final SQLException e) {
            executeResult = false;
        }
        return executeResult;
    }
}
