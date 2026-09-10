package model;

import java.time.LocalDate;

public class Transaction {

    private int idTransaction;
    private TypeTransaction type;
    private double montant;
    private LocalDate date;
    private Compte compteSource;
    private Compte compteDestination;

    public Transaction(int idTransaction,
                       TypeTransaction type,
                       double montant,
                       LocalDate date,
                       Compte compteSource,
                       Compte compteDestination) {

        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public int getIdTransaction() {
        return this.idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TypeTransaction getType() {
        return this.type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Compte getCompteSource() {
        return this.compteSource;
    }

    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }

    public Compte getCompteDestination() {
        return this.compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }
}