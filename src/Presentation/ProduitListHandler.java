package Presentation;

import DAO.IProduitDAOImpl;
import entities.Produit;

import java.util.List;

public class ProduitListHandler {
    static ProduitListWindow listWindow = null;
    IProduitDAOImpl pdao = new IProduitDAOImpl();

    public ProduitListHandler(ProduitListWindow listWindow){
        this.listWindow = listWindow;
    }

    ProduitListHandler(){}

    public void getProduits(){
        List<Produit> liste = pdao.getAll();

        listWindow.produitObservaleList.addAll(liste);
        calculerTotal();
    }

    public void calculerTotal(){
        double total=0;
        for(Produit p:listWindow.produitObservaleList){
            total+=p.getTotal();
        }
        listWindow.totalLabelValue.setText(total+"");
    }

    public void deleteProduit(long id){
        pdao.delete(id);
        initTableView();
    }

    public void initTableView(){
        listWindow.produitsTableView.getItems().clear();
        getProduits();
        calculerTotal();
    }



}
