package ru.restaurant_voting;

public class Profiles {
    public static final String
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;// from Tests

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {// from Tests
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }
}