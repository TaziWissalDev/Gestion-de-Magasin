package entities;

import java.time.LocalDate;
import java.util.Date;

public class Produit {
    private int id;
    private String designation;
    private double prix;
    private int quantite;
    private LocalDate date_saisie;
    private double total;

    public double getTotal() {
        return total = this.prix * this.quantite;
    }


    public Produit(int id, String designation, double prix, int quantite, LocalDate date_saisie) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.date_saisie = date_saisie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDate_saisie() {
        return date_saisie;
    }

    public void setDate_saisie(LocalDate date_saisie) {
        this.date_saisie = date_saisie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", date_saisie=" + date_saisie +
                '}';
    }
}
