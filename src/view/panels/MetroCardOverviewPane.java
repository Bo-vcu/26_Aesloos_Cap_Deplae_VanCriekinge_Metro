package view.panels;


import controller.MetroCardOverviewPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jxl.read.biff.BiffException;
import model.Metrocard;
import model.database.MetrocardDatabase;

import java.io.IOException;
import java.util.ArrayList;


public class MetroCardOverviewPane extends GridPane{
	private MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController();
	private MetrocardDatabase metrocardDatabase = new MetrocardDatabase();
	private TableView<Metrocard> table;
	private ObservableList<Metrocard> metrocards;
	
	
	public MetroCardOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("List of Metro cards:"), 0, 0, 1, 1);

		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		Label lblHeading = new Label("metrocard Inventory");
		lblHeading.setFont(new Font("Arial", 20));

		table = new TableView<>();
		//updateMetroCardList();

		TableColumn<Metrocard, Integer> colid = new TableColumn<>("ticket  nummer");
		colid.setMinWidth(100);
		colid.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Metrocard, String> colYear = new TableColumn<>("vervaldatum");
		colYear.setMinWidth(100);
		colYear.setCellValueFactory(new PropertyValueFactory<>("maand_jaar"));

		TableColumn<Metrocard, Integer> colbeschibaar = new TableColumn<>("beschikbare ritten");
		colbeschibaar.setMinWidth(150);
		colbeschibaar.setCellValueFactory(new PropertyValueFactory<>("aantalBeschikbare"));

		TableColumn<Metrocard, Integer> colverbruikt = new TableColumn<>("verbruikte ritten");
		colverbruikt.setMinWidth(150);
		colverbruikt.setCellValueFactory(new PropertyValueFactory<>("aantalVerbruikte"));

		table.getColumns().addAll(colid, colYear, colbeschibaar, colverbruikt);
		root.getChildren().addAll(lblHeading, table);
		this.add(root,0,1,1,1);

	}

	public void updateMetroCardList(ArrayList<Metrocard> metrocards){
		metrocards = (ArrayList<Metrocard>) FXCollections.observableArrayList(metrocards);
		//System.out.println(metrocards);
		table.setItems((ObservableList<Metrocard>) metrocards);
		table.refresh();
	}

}
