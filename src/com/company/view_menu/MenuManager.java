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

        String tableHeader = String.format("%-5s%-10s%-10s%-10s%-14s%-10s%-20s%-10s", "ИД", "Телефон", "Модель", "Вес(г)", "Покупатель", "Цена(р)", "Аккумулятор(мАч)", "Дата выхода(г)");

        ConsoleHelper.printlnMessage(tableHeader);

        for (int i = 0; i < places.size(); i++) {

            Place currentPlace = places.get(i);

            String tableCurrentRow = String.format("%-5s%-10s%-10s%-10s%-14s%-10s%-20s%-10s", currentPlace.getId(), currentPlace.getPhoneBrand().getValue(), currentPlace.getModel(), currentPlace.getWeight(), currentPlace.getOwner(), currentPlace.getPrice(),currentPlace.getBattery(),currentPlace.getReleaseDate());

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
            ConsoleHelper.printlnMessage("5. Работа с каталогом");
            ConsoleHelper.printlnMessage("0. Выход");

            action = ConsoleHelper.inputInt("Введите номер пункта меню: ", 0, 5);

            switch (action) {
                case 1: {
                    int planetIndex = ConsoleHelper.inputInt("Введите индекс телефона(0-samsung, 1-honor, 2-nokia, 3-huawei, 4-apple): ", 0, 5);
                    Place.PhoneBrand phoneBrand = Place.PhoneBrand.values()[planetIndex];
                    String model = ConsoleHelper.inputString("Введите модель телефона: ");
                    int weight = ConsoleHelper.inputInt("Введите вес телефона(г): ");
                    String owner = ConsoleHelper.inputString("Введите покупателя: ");
                    double price = ConsoleHelper.inputDouble("Введите цену телефона(р): ");
                    int battery = ConsoleHelper.inputInt("Введите аккумулятор телефона(мАч): ");
                    double releaseDate = ConsoleHelper.inputDouble("Введите дату выхода телефона(г): ");

                    placesManager.addNewPlace(phoneBrand, model, weight, owner, price,battery, releaseDate);
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
                    int innerAction = -1;

                    ConsoleHelper.printlnMessage("    Меню характеристик:");
                    ConsoleHelper.printlnMessage("    1. Изменение цены");
                    ConsoleHelper.printlnMessage("    2. Изменение модели");
                    ConsoleHelper.printlnMessage("    3. Изменение веса");
                    ConsoleHelper.printlnMessage("    4. Изменение покупателя");
                    ConsoleHelper.printlnMessage("    5. Изменение аккумулятора");
                    ConsoleHelper.printlnMessage("    6. Изменение даты выхода");
                    ConsoleHelper.printlnMessage("    0. Выход");

                    innerAction = ConsoleHelper.inputInt("    Введите номер пункта меню: ", 0, 6);

                    switch (innerAction) {
                        case 1: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения цены: ");
                                double price = ConsoleHelper.inputDouble("Введите цену(р): ");

                                placesManager.setNewPlacePriceById(id, price);
                            } catch (Exception e) {
                                ConsoleHelper.printlnMessage(e.getMessage());
                            }
                        }
                        break;

                        case 2: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения модели: ");
                                String model = ConsoleHelper.inputString("Введите модель: ");

                                placesManager.setNewPlaceModelById(id, model);
                            } catch (Exception e) {
                                ConsoleHelper.printlnMessage(e.getMessage());
                            }

                        }
                        break;

                        case 3: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения веса: ");
                                int weight = ConsoleHelper.inputInt("Введите вес: ");

                                placesManager.setNewPlaceWeightById(id, weight);
                            } catch (Exception e) {
                                ConsoleHelper.printlnMessage(e.getMessage());
                            }

                        }
                        break;

                        case 4: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения покупателя: ");
                                String owner = ConsoleHelper.inputString("Введите покупателя: ");

                                placesManager.setNewPlaceOwnerById(id, owner);
                            } catch (Exception e) {
                                ConsoleHelper.printlnMessage(e.getMessage());
                            }

                        }
                        break;

                        case 5: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения аккумулятора: ");
                                int battery = ConsoleHelper.inputInt("Введите аккумулятор: ");

                                placesManager.setNewPlaceBatteryById(id, battery);
                            } catch (Exception e) {
                                ConsoleHelper.printlnMessage(e.getMessage());
                            }

                        }
                        break;

                        case 6: {
                            try {
                                int id = ConsoleHelper.inputInt("Введите ИД телефона для изменения даты выхода: ");
                                double releaseDate = ConsoleHelper.inputInt("Введите дату выхода: ");

                                placesManager.setNewPlaceReleaseDateById(id, releaseDate);
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
                    break;

                }

                case 0: {
                    isRun = false;
                }
                break;
            }
        }

        //endregion
    }

    //endregion
}