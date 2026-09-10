package service;

import model.Client;
import java.util.HashMap;

public class ClientService {

    private HashMap<Integer, Client> tousLesClients;

    public ClientService() {
        this.tousLesClients = new HashMap<>();
    }

    // 1. Créer et enregistrer un nouveau client
    public Client creerClient(int idClient, String nom, String prenom, String email, String motDePasse) {
        Client client = new Client(idClient, nom, prenom, email, motDePasse);
        tousLesClients.put(idClient, client);
        return client;
    }

    // 2. Rechercher un client par son ID
    public Client rechercherClient(int idClient) {
        return tousLesClients.get(idClient); // Retourne null si le client n'existe pas
    }

    // 3. Modifier les informations d'un client
    public void modifierClient(int idClient, String nouveauNom, String nouveauPrenom, String nouveauEmail) {
        Client client = rechercherClient(idClient);
        if (client != null) {
            client.setNom(nouveauNom);
            client.setPrenom(nouveauPrenom);
            client.setEmail(nouveauEmail);
        }
    }

    // Getter pour récupérer tous les clients (utile pour le gestionnaire)
    public HashMap<Integer, Client> getTousLesClients() {
        return tousLesClients;
    }
}