package main;

import Entitese.DBManager;
import Entitese.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static Entitese.Utilisateur.afficherDetailsUtilisateur;
import static Entitese.Utilisateur.saisirUtilisateurAvecRole;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Saisi et ajout Role
        String nomRole = "";
        //Role roleSaisi = saisirRole(nomRole);
        //afficherDetailsRole(roleSaisi);


        System.out.println("Bienvenue dans notre application:");
        System.out.println("  ");


        Scanner scanner = new Scanner(System.in);
        int choix = -1;


            while (choix < 1 || choix > 4) {
                System.out.println("1. les Roles");
                System.out.println("2. Ajouter Des Utilisateurs");
                System.out.println("3. Liste des Utilisateurs");
                System.out.println("4. Quitter");
                System.out.println(" ");
                System.out.print("Votre choix (1, 2, 3) : ");
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                } else {
                    System.out.println("Veuillez saisir un nombre valide.");
                    // Réinitialisez la valeur pour forcer la répétition de la boucle

                }

            }


            switch (choix) {
                case 1:
                    System.out.println("1. Admin");
                    System.out.println("2. Utilisateur");
                    System.out.println("3. Autre");
                    break;
                case 2:
                    //Saisi et affiche utilisateur

                    Utilisateur utilisateurSaisi = saisirUtilisateurAvecRole(nomRole);
                    afficherDetailsUtilisateur(utilisateurSaisi);

                    try (Connection connection = DBManager.getConnection()) {
                        // ... (restez inchangé)
                        // Appel à la méthode d'insertion de l'utilisateur
                        DBManager.insererUtilisateur(connection, utilisateurSaisi);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    Utilisateur.afficherTousUtilisateurs();
                    break;
                case 4:
                    break;
                default:
                    throw new IllegalStateException("Choix invalide");
            }


    }

    }


