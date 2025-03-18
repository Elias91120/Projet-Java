package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /** 🔹 Ajouter un utilisateur avec date automatique */
    public void createUser(String name, String email) {
        String query = "INSERT INTO users (name, email, createdAt, updatedAt) VALUES (?, ?, NOW(), NOW())";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Utilisateur ajouté avec succès : " + name + " - " + email);
            } else {
                System.out.println("Échec de l'ajout de l'utilisateur.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL (ajout) : " + e.getMessage());
        }
    }

    /** 🔹 Récupérer tous les utilisateurs */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, email, createdAt, updatedAt FROM users";
        
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL (getAllUsers) : " + e.getMessage());
        }
        return users;
    }

    /** 🔹 Mettre à jour un utilisateur */
    public void updateUser(int id, String name, String email) {
        String query = "UPDATE users SET name = ?, email = ?, updatedAt = NOW() WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);
    
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Utilisateur mis à jour : " + name + " - " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL (update) : " + e.getMessage());
        }
    }
    
    /** 🔹 Supprimer un utilisateur */
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
    
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé (ID : " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL (delete) : " + e.getMessage());
        }
    }
    
    /** 🔹 Rechercher un utilisateur par nom ou email */
    public List<User> searchUsers(String keyword) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, email, createdAt, updatedAt FROM users WHERE name LIKE ? OR email LIKE ?";
        
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL (search) : " + e.getMessage());
        }
        return users;
    }
}
