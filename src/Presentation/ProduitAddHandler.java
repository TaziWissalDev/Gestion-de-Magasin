package Presentation;

import DAO.IProduitDAOImpl;
import entities.Produit;

import java.time.LocalDate;

public class ProduitAddHandler {

    formProductWindow formProduit = null;
    IProduitDAOImpl pdao = new IProduitDAOImpl();

    public ProduitAddHandler(formProductWindow formProduit) {
        this.formProduit = formProduit;
    }
    
    public void addProduit(){
        String designation = formProduit.produitDesignationTextFeild.getText();
        int qte = Integer.valueOf(formProduit.produitQuantiteTextFeild.getText());
        double prix = Double.valueOf(formProduit.produitPrixTextFeild.getText());
        LocalDate date = formProduit.produitDatePicker.getValue();

        Produit p =new Produit(0,designation,prix,qte,date);

        pdao.add(p);
    }

    public void updateProduit(int id){
        String designation = formProduit.produitDesignationTextFeild.getText();
        int qte = Integer.valueOf(formProduit.produitQuantiteTextFeild.getText());
        double prix = Double.valueOf(formProduit.produitPrixTextFeild.getText());
        LocalDate date = formProduit.produitDatePicker.getValue();

        Produit p =new Produit(id,designation,prix,qte,date);

        pdao.update(p);
    }


}
