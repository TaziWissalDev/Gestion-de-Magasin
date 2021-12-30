package DAO;

import entities.Client;
import entities.Produit;

import java.util.List;

public interface IClientDAO extends IDAO<Client>{
    public List<Client> getAll(String nom);
}
