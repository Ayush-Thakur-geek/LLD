package creationalDesignPattern;

public class SingletonDesignPattern {
    public static void main(String[] args) {
        DbConnection dbConnection1 = DbConnection.createConnection();
        DbConnection dbConnection2 = DbConnection.createConnection();
    }
}

class DbConnection {
    private static DbConnection instance = null;
    private DbConnection() {
        System.out.println("Successfully create a db connection");
    }
    private static DbConnection getInstance() {
        return instance;
    }
    public static DbConnection createConnection() {
        DbConnection dbConnection = getInstance();
        if (dbConnection == null) {
            synchronized (DbConnection.class) {
                if (dbConnection == null) instance = new DbConnection();
            }
        } else System.out.println("No new connection for you");

        return dbConnection;
    }
}
