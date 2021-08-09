package com.company.view_menu;

import com.company.catalog_data.Place;
import com.company.catalog_data.PlacesManager;
import com.company.directory_utilities.ConsoleHelper;

import java.util.ArrayList;

public class MenuManager {
    //region Fields

    private PlacesManager placesManager;

    //endregion

    //region Constructor

    public MenuManager(PlacesManager placesManager) {
        this.placesManager = placesManager;
    }

    //endregion

    //region Private Methods

    //вывод на печать места в каталоге
    private void printPlaces() {
        ArrayList<Place> places = placesManager.getPlaces();

        if (places.size() == 0) {
            ConsoleHelper.printlnMessage("Список телефонов");
            ConsoleHelper.printlnMessage("=====");
            return;
        }

        String tableHeader = String.format("%-5s%-10s%-10s%-15s%-14s%-10s", "ИД", "Длина(м)", "Ширина(м)", "Покупатель", "Цена(р)", "Телефон");

        ConsoleHelper.printlnMessage(tableHeader);

        for (int i = 0; i < places.size(); i++) {

            Place currentPlace = places.get(i);

            String tableCurrentRow = String.format("%-5d%-10d%-10d%-15s%-14.2f%-10s", currentPlace.getId(), currentPlace.getLength(), currentPlace.getWidth(), currentPlace.getOwner(), currentPlace.getPrice(), currentPlace.getPlanet().getValue());

            ConsoleHelper.printlnMessage(tableCurrentRow);
        }

        ConsoleHelper.printlnMessage("=====");
    }

    //endregion

    //region Public Methods

    public void execute() throws Exception {
        int action = -1;
        boolean isRun = true;

        while (isRun) {

            printPlaces();

            ConsoleHelper.printlnMessage("Меню:");
            ConsoleHelper.printlnMessage("1. Добавить новую запись в каталоге");
            ConsoleHelper.printlnMessage("2. Удалить телефон по ИД");
            ConsoleHelper.printlnMessage("3. Загрузить запись из файла");
            ConsoleHelper.printlnMessage("4. Сохранить запись в файл");
            ConsoleHelper.printlnMessage("5. Изменить цену телефона по ИД");
            ConsoleHelper.printlnMessage("0. Выход");

            action = ConsoleHelper.inputInt("Введите номер пункта меню: ", 0, 5);

            switch (action) {
                case 1: {
                    int length = ConsoleHelper.inputInt("Введите длину(м): ");
                    int width = ConsoleHelper.inputInt("Введите ширину(м): ");
                    String landlord = ConsoleHelper.inputString("Введите покупателя: ");
                    double price = ConsoleHelper.inputDouble("Введите цену(р): ");

                    int planetIndex = ConsoleHelper.inputInt("Введите индекс планеты(0-Самсунг, 1-Хонор, 2-Яблоко): ", 0, 3);
                    Place.PhoneBrand phoneBrand = Place.PhoneBrand.values()[planetIndex];

                    placesManager.addNewPlace(length, width, landlord, price, phoneBrand);
                }
                break;

                case 2: {
                    try {
                        int id = ConsoleHelper.inputInt("Введите ИД телефона для удаления: ");
                        placesManager.deletePlaceById(id);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 3: {
                    try {
                        String fileName = ConsoleHelper.inputString("Введите имя файла для загрузки данных: ");
                        placesManager.loadFromDataFile(fileName);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 4: {
                    try {
                        String fileName = ConsoleHelper.inputString("Введите имя файла для сохранения данных: ");
                        placesManager.saveToDataFile(fileName);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 5: {
                    try {
                        int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения цены: ");
                        double price = ConsoleHelper.inputDouble("Введите цену(р): ");

                        placesManager.setNewPlacePriceById(id, price);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 0: {
                    isRun = false;
                }
                break;
            }
        }
    }

    //endregion
}