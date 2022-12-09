package view.panels;

import controller.MetroStationSetupPaneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

public class MetroStationSetupPane extends GridPane {
    private MetroStationSetupPaneController metroStationSetupPaneController;
    private MetrocardDatabase metrocardDatabase;
    private String strategy;

    public MetroStationSetupPane() {
        VBox root = new VBox();
        this.metrocardDatabase=new MetrocardDatabase();
        root.setPadding(new Insets(5, 5, 5, 5));
        RadioButton button1 = new RadioButton("excel");
        button1.setUserData(new MetroCardsExcelLoadSaveStrategy());
        RadioButton button2 = new RadioButton("txt");
        button2.setUserData(new MetrocardsTekstLoadSaveStrategy());
        Button saveButton = new Button("save");
        this.add(saveButton, 50, 50, 1, 1);
        saveButton.setOnAction(event -> metroStationSetupPaneController.save());

        ToggleGroup group = new ToggleGroup();
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);

        button1.setSelected(true);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (group.getSelectedToggle()!=null){
                    group.getSelectedToggle().getUserData().toString();
                }
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (group.getSelectedToggle()!=null){
                    metrocardDatabase.setLoadSaveStrategy((LoadSaveStrategy) group.getSelectedToggle().getUserData());
                }
            }
        });
        root.getChildren().addAll(button1,button2);
        this.add(root,1,1);



    }



}
