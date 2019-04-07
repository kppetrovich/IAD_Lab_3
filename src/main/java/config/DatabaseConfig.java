package config;

public class DatabaseConfig {
    public static final String URL = "jdbc:postgresql://localhost/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String DRIVER_NAME = "org.postgresql.Driver";

    private DatabaseConfig() { }
}
