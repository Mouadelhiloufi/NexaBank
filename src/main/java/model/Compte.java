package model;

import java.util.HashSet;

public abstract class Compte {

    private String numeroCompte;
    private double solde;
    private HashSet<Transaction> historiqueTransactions;

    public Compte(String numeroCompte, double solde) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.historiqueTransactions = new HashSet<>();
    }


    public String getNumeroCompte() {
        return this.numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getSolde() {
        return this.solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public HashSet<Transaction> getHistoriqueTransactions() {
        return historiqueTransactions;
    }

    public void setHistoriqueTransactions(HashSet<Transaction> historiqueTransactions) {
        this.historiqueTransactions = historiqueTransactions;
    }
}