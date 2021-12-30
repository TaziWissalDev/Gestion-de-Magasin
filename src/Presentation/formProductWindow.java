package Presentation;

import entities.Produit;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class formProductWindow {

    ProduitAddHandler handler = new ProduitAddHandler(this);
    VBox root = new VBox();
    HBox buttons = new HBox();
    Scene scene = new Scene(root);
    Stage window = new Stage();

    /*controls for container*/
    Label titleLabel = new Label("Nouveau Produit:");

    Label produitDesignationLabel = new Label("Designation:");
    TextField produitDesignationTextFeild = new TextField();

    Label produitPrixLabel = new Label("Prix:");
    TextField produitPrixTextFeild = new TextField();

    Label produitQuantiteLabel = new Label("Quantité:");
    TextField produitQuantiteTextFeild = new TextField();

    Label produitDateLabel = new Label("Date:");
    DatePicker produitDatePicker = new DatePicker();

    Button addProduit = new Button("Ajouter");
    Button cancelProduit = new Button("Annuler");



    private void initwindow(){
        window.setScene(scene);
        window.setWidth(700);window.setHeight(600);
        window.setTitle("Nouveau Produit");
        window.getIcons().add(new Image("file:icon.png"));
        window.initModality(Modality.APPLICATION_MODAL);

    }

    private void addNodesToWindow(){
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(produitDesignationLabel,produitDesignationTextFeild);
        root.getChildren().addAll(produitPrixLabel,produitPrixTextFeild);
        root.getChildren().addAll(produitQuantiteLabel,produitQuantiteTextFeild);
        root.getChildren().addAll(produitDateLabel,produitDatePicker);
        buttons.getChildren().addAll(addProduit,cancelProduit);
        root.getChildren().add(buttons);
    }

    private void addStyleToNodes(){
        scene.getStylesheets().add("css/style.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMinWidth(window.getWidth());
        produitDesignationLabel.getStyleClass().add("labelForm");
        produitPrixLabel.getStyleClass().add("labelForm");
        produitQuantiteLabel.getStyleClass().add("labelForm");
        produitDateLabel.getStyleClass().add("labelForm");
        root.setSpacing(15);
        buttons.setSpacing(20);

    }

    private void addEvents(){
        cancelProduit.setOnAction(event -> {
            window.close();
        });
        addProduit.setOnAction(event -> {
            handler.addProduit();
            /*window.close();*/
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

        });
        window.setOnCloseRequest(event -> {
            event.consume();
        });
    }

    private void addEvents(int id){
        cancelProduit.setOnAction(event -> {
            window.close();
            new ProduitListWindow();
        });
        addProduit.setOnAction(event -> {
            handler.updateProduit(id);
            window.close();
            new ProduitListWindow();

        });
        /*window.setOnCloseRequest(event -> {
            event.consume();
        });*/
    }

    public formProductWindow() {
        initwindow();
        addStyleToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }

    public formProductWindow(Produit p) {
        initwindow();
        produitDesignationTextFeild.setText(p.getDesignation());
        produitQuantiteTextFeild.setText(p.getQuantite()+"");
        produitPrixTextFeild.setText(p.getPrix()+"");
        produitDatePicker.setValue(p.getDate_saisie());
        addProduit.setText("Modifier");
        titleLabel.setText("Modifier un produit");
        window.setTitle("Modification du produit");
        addStyleToNodes();
        addNodesToWindow();
        System.out.println("in construct id==> "+p.toString());
        addEvents(p.getId());
        window.show();
    }
}
