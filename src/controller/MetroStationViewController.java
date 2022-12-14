package controller;

import model.*;
import view.MetroStationView;

import java.util.ArrayList;

public class MetroStationViewController implements Observer {
    private MetroStationView metroStationView;
    private MetroFacade metro;

    public MetroStationViewController(MetroFacade metro, MetroStationView metroStationView) {
        this.metroStationView = metroStationView;
        this.metro = metro;
        this.metro.addObserver(MetroEventEnum.OPEN_METROSTATION, this);
        this.metro.addObserver(MetroEventEnum.BUY_METROCARD, this);
        this.metro.addObserver(MetroEventEnum.CHANGE_GATE, this);
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metro.getMetroCardDList();
        metroStationView.updateMetrocardIDList(ids);
    }

    public String scanMetrocard(Integer checkboxValue, int gateID) {
        Metrocard metrocard = metro.getMetroCardList().get(checkboxValue - 1);
        if (metrocard.getAantalBeschikbare() != 0) {
            metro.getMetroGates().get(gateID - 1).setAantalScannedCards(metro.getMetroGates().get(gateID - 1).getAantalScannedCards() + 1);
            return metro.scanMetroGate(checkboxValue,gateID);
        }
//
//            metrocard.setAantalBeschikbare(metrocard.getAantalBeschikbare()-1);
//            metrocard.setAantalVerbruikte(metrocard.getAantalVerbruikte()+1);
//
//            String[] datum = metrocard.getMaand_jaar().split("/");
//
//            LocalDate localDate;
//
//            if (Integer.parseInt(datum[0])==12){
//                localDate = LocalDate.of(Integer.parseInt(datum[1]) + 2,1, 1);
//            }
//            else {
//                localDate = LocalDate.of(Integer.parseInt(datum[1]) + 1,Integer.parseInt(datum[0]) + 1, 1);
//            }
//
//            System.out.println(localDate);
//            if (LocalDate.now().isBefore(localDate)){
//                return "Card " + checkboxValue + " is scanned";
//            }
//            else {
//                return "Card " + checkboxValue + " is expired";
//            }
//        }
        return "Card " + checkboxValue + " has no available tickets";
    }

    public String walkThroughGate(int gateID) {
        return metro.walkTroughGate(gateID);
    }

    public ArrayList<MetroGate> getAllGates() {
        return metro.getMetroGates();
    }

//    public void changeColor(int finalI1) {
//        metroStationView.setColorInactive(String.valueOf(finalI1));
//    }
}
