package service;

import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import exception.CompteInexistantException;
import exception.MontantNegatifException;

import java.util.HashMap;

public class CompteService {

    // Stocke tous les comptes de la banque (Clé: numeroCompte, Valeur: Objet Compte)
    private HashMap<String, Compte> tousLesComptes;

    public CompteService() {
        this.tousLesComptes = new HashMap<>();
    }

    // 1. Créer un Compte Courant
    public CompteCourant creerCompteCourant(String numeroCompte, double soldeInitial, Client client) throws MontantNegatifException {
        if (soldeInitial < 0) {
            throw new MontantNegatifException("Le solde initial ne peut pas être négatif.");
        }

        CompteCourant nouveauCompte = new CompteCourant(numeroCompte, soldeInitial);

        // Enregistrement dans le service global et association au client
        tousLesComptes.put(numeroCompte, nouveauCompte);
        client.ajouterCompte(nouveauCompte);

        return nouveauCompte;
    }

    // 2. Créer un Compte Épargne
    public CompteEpargne creerCompteEpargne(String numeroCompte, double soldeInitial, Client client) throws MontantNegatifException {
        if (soldeInitial < 0) {
            throw new MontantNegatifException("Le solde initial ne peut pas être négatif.");
        }

        CompteEpargne nouveauCompte = new CompteEpargne(numeroCompte, soldeInitial);

        tousLesComptes.put(numeroCompte, nouveauCompte);
        client.ajouterCompte(nouveauCompte);

        return nouveauCompte;
    }

    // 3. Rechercher un compte
    public Compte rechercherCompte(String numeroCompte) throws CompteInexistantException {
        Compte compte = tousLesComptes.get(numeroCompte);

        if (compte == null) {
            throw new CompteInexistantException("Aucun compte trouvé avec le numéro : " + numeroCompte);
        }

        return compte;
    }

    // 4. Clôturer / Supprimer un compte
    public void cloturerCompte(String numeroCompte, Client client) throws CompteInexistantException {
        // On vérifie que le compte existe bien avant de supprimer
        Compte compte = rechercherCompte(numeroCompte);

        // Suppression de la HashMap globale et de la HashMap du client
        tousLesComptes.remove(numeroCompte);
        client.getComptes().remove(numeroCompte);
    }

    // Getter pour accéder à la liste complète si besoin
//    public HashMap<String, Compte> getTousLesComptes() {
//        return tousLesComptes;
//    }
}