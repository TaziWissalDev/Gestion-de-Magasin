package DAO;

import entities.Produit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IProduitDAOImpl implements IProduitDAO {
    Connexion cnx;

    public IProduitDAOImpl() {
        cnx = new Connexion();
    }

    @Override
    public void add(Produit p) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "insert into produits(designation,Quantite,prix,date_saisie) values (?,?,?,?)";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, p.getDesignation());
            pst.setLong(2, p.getQuantite());
            pst.setDouble(3, p.getPrix());
            pst.setDate(4, Date.valueOf(p.getDate_saisie()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "delete from produits where id = ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
            Produit p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produit getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "select * from produits where id= ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            Produit p;
            if (rs.next())
                return new Produit(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), (rs.getDate(5)).toLocalDate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produitList = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;

        try {
            String sql = "select * from produits";
            pst = cnx.getConnexion().prepareStatement(sql);
            rs = pst.executeQuery();
            Produit p;
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), (rs.getDate(5)).toLocalDate());
                produitList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produitList;
    }

    @Override
    public void update(Produit p) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "update produits set designation = ? and Quantite = ? and prix = ? and date_saisie = ? where id = ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, p.getDesignation());
            pst.setLong(2, p.getQuantite());
            pst.setDouble(3, p.getPrix());
            pst.setDate(4, Date.valueOf(p.getDate_saisie()));
            pst.setLong(5, p.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produit> getAll(String des) {
        List<Produit> produitList = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "select * from produits where designation like ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            Produit p;
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), (rs.getDate(5)).toLocalDate());
                produitList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produitList;
    }
}
