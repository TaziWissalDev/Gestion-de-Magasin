package Presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindows extends Application {

    BorderPane root = new BorderPane();
    Scene scene = new Scene(root);
    /*others scenes*/
    MenuItem newProductMenuItem = new MenuItem("Nouveau");
    MenuItem listProductMenuItem = new MenuItem("Liste");

    MenuItem newClientMenuItem = new MenuItem("Nouveau");
    MenuItem listClientMenuItem = new MenuItem("Liste");

    MenuItem helpMenuItem = new MenuItem("?");

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void createMenu(){
        MenuBar menuBar = new MenuBar();
        Menu produitsMenu = new Menu("Produits");
        Menu clientsMenu = new Menu("Clients");
        Menu ventesMenu = new Menu("Ventes");
        Menu paiementsMenu = new Menu("Paiements");
        Menu inventairesMenu = new Menu("Inventaires");
        Menu helpMenu = new Menu("Help");

        produitsMenu.getItems().addAll(newProductMenuItem,listProductMenuItem);
        clientsMenu.getItems().addAll(newClientMenuItem,listClientMenuItem);
        helpMenu.getItems().add(helpMenuItem);

        menuBar.getMenus().addAll(produitsMenu,clientsMenu,ventesMenu,paiementsMenu,inventairesMenu,helpMenu);
        root.setTop(menuBar);
    }
    
    
    private void addStyleToNodes(){
        scene.getStylesheets().add("css/style.css");
        root.getStyleClass().add("mainWindow");
    }

    private void addEvents(){
        newProductMenuItem.setOnAction(event -> {
            new formProductWindow();
        });

        listProductMenuItem.setOnAction(event -> {
            new ProduitListWindow();
        });
    }

    @Override
    public void start(Stage window) throws Exception {
        addEvents();
        addStyleToNodes();
        window.setScene(scene);
        window.setWidth(1100);window.setHeight(700);
        window.setTitle("Gestion de Magasin");
        createMenu();
        window.getIcons().add(new Image("file:icon.png"));
        window.show();
    }
}
