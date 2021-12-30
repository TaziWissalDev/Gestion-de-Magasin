package Presentation;

import entities.Produit;
import DAO.IProduitDAOImpl;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;
import java.util.List;

public class ProduitListWindow {

    ProduitListHandler handler = new ProduitListHandler(this);

    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);

    Label titleLabel = new Label("Liste des produits");
    HBox totalHbox = new HBox();
    Label totalLabel = new Label("Total");
    Label totalLabelValue = new Label("0.0");
    TableColumn<Produit, Long> idColumn = new TableColumn<>("Id");
    TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
    TableColumn<Produit, Double> prixColumn = new TableColumn<>("Prix");
    TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Qte");
    TableColumn<Produit, Double> totalColumn = new TableColumn<>("Total");
    TableColumn<Produit, Date> dateColumn = new TableColumn<>("Date");
    TableView<Produit> produitsTableView = new TableView<>();

    ObservableList<Produit> produitObservaleList = FXCollections.observableArrayList();


    private void addComumnsToTableView(){
        produitsTableView.getColumns().addAll(idColumn,designationColumn,prixColumn,qteColumn,totalColumn,dateColumn);
        produitsTableView.setItems(produitObservaleList);
    }

    private void addNodesToPane(){
        totalHbox.getChildren().addAll(totalLabel,totalLabelValue);
        root.getChildren().addAll(titleLabel,produitsTableView,totalHbox);
    }

    private void updateColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(100);
        designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
        designationColumn.setPrefWidth(250);
        prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
        prixColumn.setPrefWidth(150);
        qteColumn.setCellValueFactory(new PropertyValueFactory("quantite"));
        qteColumn.setPrefWidth(150);
        totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
        totalColumn.setPrefWidth(150);

        dateColumn.setCellValueFactory(new PropertyValueFactory("date_saisie"));
        dateColumn.setPrefWidth(100);
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("css/style.css");
        titleLabel.getStyleClass().add("labelTitle");
        totalLabel.getStyleClass().add("labelTotal");
        totalLabelValue.getStyleClass().add("labelTotal");
        totalHbox.getStyleClass().add("boxTotal");
        produitsTableView.getStyleClass().add("table-row-cell");
        produitsTableView.setMinHeight(500);
        titleLabel.setMinWidth(window.getWidth());
        totalHbox.setSpacing(15);
    }

    public void addEvents(){
        // When user right-click on Circle
       /* produitsTableView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(produitsTableView, event.getScreenX(), event.getScreenY());
            }
        });

        updateProduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("developping update window here...");
            }
        });

        deleteProduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("****>"+event);
                /*handler.deleteProduit(Integer.valueOf(idColumn.getText()));*/
      /*      }
        });
        */
    }
public void setTableRow(){
    produitsTableView.setRowFactory(
            new Callback<TableView<Produit>, TableRow<Produit>>() {
                public TableRow<Produit> call(TableView<Produit> produitsTableView) {
                    final TableRow<Produit> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem updateProduit = new MenuItem("Modifier");
                    final MenuItem deleteProduit = new MenuItem("Supprimer");
                    contextMenu.getItems().addAll(updateProduit,deleteProduit);
                    deleteProduit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("---->"+row.getItem());
                            handler.deleteProduit(Integer.valueOf(row.getItem().getId()));
                            produitsTableView.getItems().remove(row.getItem());
                        }
                    });

                    updateProduit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("===>"+row.getItem());
                            Produit p =new Produit(row.getItem().getId(),row.getItem().getDesignation(),row.getItem().getPrix(),row.getItem().getQuantite(),row.getItem().getDate_saisie());

                            new formProductWindow(p);
                            window.close();

                        }
                    });

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(contextMenu)
                                    .otherwise((ContextMenu)null));
                    return row;
                }
            });
}



    public void initwindow() {
        window.setScene(scene);
        window.setWidth(1000);
        window.setHeight(650);
        window.setTitle("Liste Produits");
        window.getIcons().add(new Image("file:icon.png"));
        window.initModality(Modality.APPLICATION_MODAL);

    }

    public ProduitListWindow() {
        initwindow();
        addComumnsToTableView();
        addNodesToPane();
        updateColumns();
        addStylesToNodes();
        addEvents();
        setTableRow();
        handler.getProduits();
        handler.calculerTotal();
        window.show();
    }
}
