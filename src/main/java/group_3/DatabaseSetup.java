
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author <your group number>
 */
public class DatabaseSetup {

    public static void initialize() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            // PERSON TABLE
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS person (
                    id SERIAL PRIMARY KEY,
                    full_name VARCHAR(100),
                    dob DATE,
                    email VARCHAR(120) UNIQUE,
                    phone VARCHAR(20),
                    username VARCHAR(50) UNIQUE,
                    password VARCHAR(100),
                    role VARCHAR(30)
                );
            """);

            // EVENT TABLE
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS event (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(120),
                    type VARCHAR(50),
                    location VARCHAR(120),
                    status VARCHAR(50),
                    start_date DATE,
                    end_date DATE,
                    image BYTEA
                );
            """);

            // SESSION TABLE
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS session (
                    id SERIAL PRIMARY KEY,
                    event_id INT REFERENCES event(id),
                    title VARCHAR(120),
                    description TEXT,
                    session_datetime TIMESTAMP,
                    venue VARCHAR(120),
                    capacity INT
                );
            """);

            // TICKET TABLE
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS ticket (
                    id SERIAL PRIMARY KEY,
                    attendee_id INT REFERENCES person(id),
                    session_id INT REFERENCES session(id),
                    type VARCHAR(50),
                    price NUMERIC,
                    qr_code BYTEA,
                    status VARCHAR(20)
                );
            """);

            System.out.println("✅ Tables initialized.");
        }
        catch (Exception e) {
            System.out.println("❌ Failed to initialize tables.");
            e.printStackTrace();
        }
    }
}
