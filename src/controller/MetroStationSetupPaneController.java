package controller;

import model.MetroFacade;
import model.Observer;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import view.panels.MetroStationSetupPane;

import java.io.*;
import java.util.Properties;

public class MetroStationSetupPaneController implements Observer {
    private MetroStationSetupPane metroStationSetupPane;
    private MetroFacade metro;

    public MetroStationSetupPaneController(MetroFacade metro, MetroStationSetupPane metroStationSetupPane) {
        this.metro = metro;
        this.metroStationSetupPane = metroStationSetupPane;
    }

    public void save(LoadSaveStrategy loadSaveStrategy) throws IOException {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        properties.setProperty("database", loadSaveStrategy.getClass().getSimpleName());
        properties.store(new FileOutputStream("src/bestanden/settings.properties"), null);
    }

    public boolean getMetroOpenStatus(){
        return metro.getMetroOpenStatus();
    }



    @Override
    public void update() {

    }
}
