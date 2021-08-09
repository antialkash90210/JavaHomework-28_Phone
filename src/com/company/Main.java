package com.company;

import com.company.catalog_data.PlacesManager;
import com.company.view_menu.MenuManager;

public class Main {

    public static void main(String[] args) throws Exception {
        PlacesManager placesManager = new PlacesManager();
        placesManager.seedPlaces();

        MenuManager menuManager = new MenuManager(placesManager);
        menuManager.execute();
    }
}