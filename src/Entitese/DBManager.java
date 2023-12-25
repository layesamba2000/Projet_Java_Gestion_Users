// DBManager.java
package Entitese;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost/gestion_users";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

    // Méthode pour insérer un utilisateur dans la base de données
    public static void insererUtilisateur(Connection connection, Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateur (email, password, password_Hashed, role) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utilisateur.getEmail());
            preparedStatement.setString(2, utilisateur.getPassword());
            preparedStatement.setString(3, utilisateur.getPassword_Hashed());
            preparedStatement.setString(4, utilisateur.getRole().getNom()); // Supposant que le rôle est une chaîne

            preparedStatement.executeUpdate();
            System.out.println("Utilisateur inséré avec succès dans la base de données.");

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de l'utilisateur dans la base de données", e);
        }
    }


    public static List<Utilisateur> getAllUtilisateurs() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultSet.getInt("id"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setPassword_Hashed(resultSet.getString("password_Hashed"));

                // Ajoutez d'autres champs selon votre modèle de données

                utilisateurs.add(utilisateur);
            }
        }

        return utilisateurs;
    }
}