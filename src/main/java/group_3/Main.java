package group_3;
import group_3.util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.setUpDatabase();
        System.out.println("Set up database successfully");
    }
}