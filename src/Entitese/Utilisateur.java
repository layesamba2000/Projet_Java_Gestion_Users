package Entitese;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Utilisateur {

    private int id;
    private String email;
    private String password;
    private String password_Hashed;
    private Role role;

    public Utilisateur() {
        this.id = id;
        this.email = email;
        this.password = password;
        this.password_Hashed = password_Hashed;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword_Hashed(String password_Hashed) {
        this.password_Hashed = password_Hashed;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_Hashed() {
        return password_Hashed;
    }

    public Role getRole() {
        return role;
    }

    // Méthode pour saisir un utilisateur avec son rôle à partir de la console
    public static Utilisateur saisirUtilisateurAvecRole(String nomRole) {
        Utilisateur utilisateur = new Utilisateur();
        Scanner scanner = new Scanner(System.in);
        // Demander à l'utilisateur de saisir l'e-mail jusqu'à ce qu'il soit correct
        boolean emailValide = false;
        while (!emailValide) {
            System.out.println("Veuillez saisir l'e-mail de l'utilisateur (doit se terminer par @gmail.com) : ");
            String emailSaisi = scanner.nextLine();

            // Vérifier si l'e-mail se termine par "@gmail.com"
            if (emailSaisi.toLowerCase().endsWith("@gmail.com")) {
                utilisateur.setEmail(emailSaisi);
                emailValide = true;
            } else {
                System.out.println("L'e-mail doit se terminer par @gmail.com. Veuillez réessayer.");
            }
        }

        System.out.println("Veuillez saisir le mot de passe de l'utilisateur : ");
        utilisateur.setPassword(scanner.nextLine());

        // Hacher le mot de passe avec la méthode hashPassword
        String hashedPassword = hashPassword(utilisateur.getPassword());
        utilisateur.setPassword_Hashed(hashedPassword);

        // Appel à la méthode de saisie de rôle avec le nom du rôle
        Role role = Role.saisirRole(nomRole);
        utilisateur.setRole(role);

        return utilisateur;
    }

    // Méthode pour afficher les détails d'un utilisateur
    public static void afficherDetailsUtilisateur(Utilisateur utilisateur) {
        if (utilisateur != null) {
            System.out.println("Email de l'utilisateur : " + utilisateur.getEmail());
           // System.out.println("Mot de passe de l'utilisateur : " + utilisateur.getPassword());
            System.out.println("Mot de passe haché : " + utilisateur.getPassword_Hashed());

            Role role = utilisateur.getRole();
            if (role != null) {
                System.out.println("Nom du rôle : " + role.getNom());
            } else {
                System.out.println("L'utilisateur n'a pas de rôle défini.");
            }
        } else {
            System.out.println("L'utilisateur est null.");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hachage du mot de passe", e);
        }
    }

    public static void afficherTousUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = DBManager.getAllUtilisateurs();
            for (Utilisateur utilisateur : utilisateurs) {
                afficherDetailsUtilisateur(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}