package main;

import model.*;
import service.*;
import utils.FichierUtil;

import java.util.Scanner;

public class Main {

    // Instanciation des services partagés
    private static ClientService clientService = new ClientService();
    private static CompteService compteService = new CompteService();
    private static TransactionService transactionService = new TransactionService();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Données de démonstration pour tester l'application
        initialiserDonneesDeTest();

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n========== WELCOME TO NEXABANK ==========");
            System.out.println("1. Espace Client");
            System.out.println("2. Espace Gestionnaire / Banquier");
            System.out.println("0. Quitter");
            System.out.print("Faites votre choix : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine(); // Consommer le saut de ligne

                switch (choix) {
                    case 1:
                        gererEspaceClient();
                        break;
                    case 2:
                        gererEspaceGestionnaire();
                        break;
                    case 0:
                        System.out.println("Merci d'avoir utilisé NexaBank. À bientôt !");
                        break;
                    default:
                        System.out.println("Choix invalide !");
                }
            } else {
                System.out.println("Veuillez saisir un nombre valide.");
                scanner.nextLine();
            }
        }
    }

    // -------------------------------------------------------------
    // ESPACE CLIENT
    // -------------------------------------------------------------
    private static void gererEspaceClient() {
        System.out.print("\nEntrez votre ID Client (ex: 1) : ");
        int idClient = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.rechercherClient(idClient);
        if (client == null) {
            System.out.println("Client introuvable !");
            return;
        }

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- ESPACE CLIENT : " + client.getPrenom() + " " + client.getNom() + " ---");
            System.out.println("1. Consulter mes comptes et soldes");
            System.out.println("2. Effectuer un dépôt");
            System.out.println("3. Effectuer un retrait");
            System.out.println("4. Effectuer un virement");
            System.out.println("5. Consulter un relevé bancaire (.txt)");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1:
                        for (Compte c : client.getComptes().values()) {
                            System.out.println("Compte N° " + c.getNumeroCompte() + " | Solde: " + c.getSolde() + " EUR");
                        }
                        break;

                    case 2:
                        System.out.print("Numéro du compte : ");
                        String numDepot = scanner.nextLine();
                        Compte cDepot = compteService.rechercherCompte(numDepot);
                        System.out.print("Montant à déposer : ");
                        double mtDepot = scanner.nextDouble();
                        scanner.nextLine();

                        transactionService.deposer(cDepot, mtDepot);
                        System.out.println("Dépôt effectué avec succès !");
                        break;

                    case 3:
                        System.out.print("Numéro du compte : ");
                        String numRetrait = scanner.nextLine();
                        Compte cRetrait = compteService.rechercherCompte(numRetrait);
                        System.out.print("Montant à retirer : ");
                        double mtRetrait = scanner.nextDouble();
                        scanner.nextLine();

                        transactionService.retirer(cRetrait, mtRetrait);
                        System.out.println("Retrait effectué avec succès !");
                        break;

                    case 4:
                        System.out.print("Numéro du compte source : ");
                        String numSrc = scanner.nextLine();
                        Compte cSrc = compteService.rechercherCompte(numSrc);

                        System.out.print("Numéro du compte destination : ");
                        String numDest = scanner.nextLine();
                        Compte cDest = compteService.rechercherCompte(numDest);

                        System.out.print("Montant du virement : ");
                        double mtVir = scanner.nextDouble();
                        scanner.nextLine();

                        transactionService.virer(cSrc, cDest, mtVir);
                        System.out.println("Virement effectué avec succès !");
                        break;

                    case 5:
                        System.out.print("Numéro du compte : ");
                        String numRel = scanner.nextLine();
                        FichierUtil.lireReleve(numRel);
                        break;

                    case 0:
                        System.out.println("Retour...");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    // -------------------------------------------------------------
    // ESPACE GESTIONNAIRE
    // -------------------------------------------------------------
    private static void gererEspaceGestionnaire() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- ESPACE GESTIONNAIRE BANCAIRE ---");
            System.out.println("1. Créer un nouveau compte");
            System.out.println("2. Modifier les informations d'un client");
            System.out.println("3. Clôturer un compte");
            System.out.println("4. Consulter le relevé d'un compte");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1:
                        System.out.print("ID du Client : ");
                        int idC = scanner.nextInt();
                        scanner.nextLine();
                        Client client = clientService.rechercherClient(idC);

                        if (client == null) {
                            System.out.println("Client introuvable.");
                            break;
                        }

                        System.out.print("Type de compte (1: Courant, 2: Epargne) : ");
                        int type = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Numéro de compte unique : ");
                        String numCompte = scanner.nextLine();

                        System.out.print("Solde initial : ");
                        double soldeInit = scanner.nextDouble();
                        scanner.nextLine();

                        if (type == 1) {
                            compteService.creerCompteCourant(numCompte, soldeInit, client);
                        } else {
                            compteService.creerCompteEpargne(numCompte, soldeInit, client);
                        }
                        System.out.println("Compte créé avec succès !");
                        break;

                    case 2:
                        System.out.print("ID du Client à modifier : ");
                        int idModif = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nouveau nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Nouveau prénom : ");
                        String prenom = scanner.nextLine();
                        System.out.print("Nouveau email : ");
                        String email = scanner.nextLine();

                        clientService.modifierClient(idModif, nom, prenom, email);
                        System.out.println("Informations mises à jour !");
                        break;

                    case 3:
                        System.out.print("ID du Client : ");
                        int idCl = scanner.nextInt();
                        scanner.nextLine();
                        Client cCl = clientService.rechercherClient(idCl);

                        System.out.print("Numéro du compte à clôturer : ");
                        String numCl = scanner.nextLine();

                        compteService.cloturerCompte(numCl, cCl);
                        System.out.println("Compte clôturé avec succès !");
                        break;

                    case 4:
                        System.out.print("Numéro du compte : ");
                        String numRel = scanner.nextLine();
                        FichierUtil.lireReleve(numRel);
                        break;

                    case 0:
                        System.out.println("Retour...");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    // Données d'exemple pré-chargées
    private static void initialiserDonneesDeTest() {
        try {
            Client c1 = clientService.creerClient(1, "El Hiloufi", "Mouad", "mouad@example.com", "pass123");
            compteService.creerCompteCourant("CC-1001", 1500.0, c1);
            compteService.creerCompteEpargne("CE-2001", 5000.0, c1);
        } catch (Exception e) {
            System.out.println("Erreur d'initialisation : " + e.getMessage());
        }
    }
}