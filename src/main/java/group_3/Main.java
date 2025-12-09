public class Main {
    public static void main(String[] args) {
        if (DatabaseConnection.testConnection()) {
            System.out.println("✅ Connected to Supabase database!");
        } else {
            System.out.println("❌ Failed to connect!");
        }
    }
}