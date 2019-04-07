package config;

public class DatabaseConfig {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orbis";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

    private DatabaseConfig() { }
}
