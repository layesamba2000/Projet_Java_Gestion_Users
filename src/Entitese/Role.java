package Entitese;

import java.util.Scanner;

// Role.java
public class Role {
    private int id;
    private String nom;

    public Role(String nom) {
        setNom(nom);
    }

    public Role(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (isValidRole(nom)) {
            this.nom = nom.toLowerCase(); // Convertir en minuscules pour éviter les problèmes de casse
        } else {
            throw new IllegalArgumentException("Nom de rôle non valide : " + nom);
        }
    }

    private boolean isValidRole(String nom) {
        return nom != null && (nom.equalsIgnoreCase("admin") || nom.equalsIgnoreCase("utilisateur") || nom.equalsIgnoreCase("autre"));
    }

    // Méthode pour saisir un rôle à partir de la console
    // Méthode pour saisir un rôle à partir de la console
    public static Role saisirRole(String nomRole) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez choisir un rôle parmi les options suivantes :");
        System.out.println("1. admin");
        System.out.println("2. utilisateur");
        System.out.println("3. autre");

        int choix = -1;
        while (choix < 1 || choix > 3) {
            System.out.print("Votre choix (1, 2, 3) : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
            } else {
                System.out.println("Veuillez saisir un nombre valide.");
                scanner.next(); // Clear the input buffer
            }
        }


        switch (choix) {
            case 1:
                nomRole = "admin";
                break;
            case 2:
                nomRole = "utilisateur";
                break;
            case 3:
                nomRole = "autre";
                break;
            default:
                throw new IllegalStateException("Choix invalide");
        }

        try {
            Role role = new Role(nomRole);
            System.out.println("Rôle saisi avec succès.");
            return role;
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return null;
        }
    }
    // Méthode pour afficher les détails d'un rôle
    public static void afficherDetailsRole(Role role) {
        if (role != null) {
            System.out.println("ID du rôle : " + role.getId());
            System.out.println("Nom du rôle : " + role.getNom());
        } else {
            System.out.println("Le rôle est null.");
        }
    }
}

