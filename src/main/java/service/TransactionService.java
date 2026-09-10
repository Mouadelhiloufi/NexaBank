package service;

import model.Compte;
import model.Transaction;
import model.TypeTransaction;
import exception.MontantNegatifException;
import exception.SoldeInsuffisantException;
import exception.FichierException;
import utils.FichierUtil;

import java.time.LocalDate;

public class TransactionService{
    private int compteurIdTransaction=1;

    public void deposer(Compte compte, double montant) throws MontantNegatifException, FichierException {
        if(montant<=0){
            throw new MontantNegatifException("Le montant doit être supérieur à zéro.");
        }
        compte.setSolde(compte.getSolde() + montant);

        Transaction t = new Transaction(
                compteurIdTransaction++,
                TypeTransaction.DEPOT,
                montant,
                LocalDate.now(),
                compte,
                null
        );
        compte.getHistoriqueTransactions().add(t);

        // Sauvegarde dans le fichier .txt
        String ligneReleve = LocalDate.now() + " | DEPOT | +" + montant + " EUR | Solde: " + compte.getSolde() + " EUR";
        FichierUtil.enregistrerTransaction(compte.getNumeroCompte(), ligneReleve);
    }

    public void retirer(Compte compte, double montant) throws MontantNegatifException, SoldeInsuffisantException, FichierException {
        if (montant <= 0) {
            throw new MontantNegatifException("Le montant du retrait doit être supérieur à zéro.");
        }

        if (compte.getSolde() < montant) {
            throw new SoldeInsuffisantException("Solde insuffisant pour effectuer ce retrait. Solde actuel : " + compte.getSolde());
        }

        // Modification du solde
        compte.setSolde(compte.getSolde() - montant);

        // Création de la transaction
        Transaction t = new Transaction(
                compteurIdTransaction++,
                TypeTransaction.RETRAIT,
                montant,
                LocalDate.now(),
                compte,
                null
        );

        compte.getHistoriqueTransactions().add(t);

        // Sauvegarde dans le fichier .txt
        String ligneReleve = LocalDate.now() + " | RETRAIT | -" + montant + " EUR | Solde: " + compte.getSolde() + " EUR";
        FichierUtil.enregistrerTransaction(compte.getNumeroCompte(), ligneReleve);
    }

    public void virer(Compte source, Compte destination, double montant) throws MontantNegatifException, SoldeInsuffisantException, FichierException {
        // Le retrait gère déjà la vérification du solde et du montant
        this.retirer(source, montant);
        this.deposer(destination, montant);
    }
}