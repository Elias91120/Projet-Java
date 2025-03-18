package application;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {
    private int id;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
    private String language;

    // Getters and setters

    public User(int id, String name, String email, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = formatTimestamp(createdAt);
        this.updatedAt = formatTimestamp(updatedAt);
    }

    // ✅ Méthode pour formater les timestamps en String lisible
    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(timestamp);
        }
        return "N/A"; // Si null, retourne "N/A"
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = formatTimestamp(createdAt); }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = formatTimestamp(updatedAt); }

    public String language() { return language(); }
    public void setlanguage(String language) { this.language = language; }

    @Override
    public String toString() {
        return "User [ID=" + id + ", Name=" + name + ", Email=" + email + ", CreatedAt=" + createdAt + ", UpdatedAt=" + updatedAt + "]";
    }
}
