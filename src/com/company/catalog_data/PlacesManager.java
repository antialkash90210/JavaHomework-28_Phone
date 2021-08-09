package com.company.catalog_data;

import java.io.*;
import java.util.ArrayList;

public class PlacesManager {
    //region Fields in the directory

    //Поля в справочнике и id
    private int currentId;
    private ArrayList<Place> places;

    //endregion

    //region Constructor

    //конструктор листа каталога
    public PlacesManager() {
        currentId = 0;
        places = new ArrayList<>();
    }

    //endregion

    //region Private methods by identifier

    //найти индекс по идентификатору
    private int findIndexById(int id) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    //получить максимальный идентификатор
    private int getMaxId() {
        int maxId = 0;
        for (int i = 0; i < places.size(); i++) {

            int currentId = places.get(i).getId();

            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId;
    }

    //endregion

    //region Public methods for working with a catalog sheet

    //добавить новое место
    public void addNewPlace(int width, int length, String landlord, double price, Place.PhoneBrand phoneBrand) {
        currentId++;

        Place place = new Place(currentId, width, length, landlord, price, phoneBrand);

        places.add(place);
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    //удалить место по идентификатору
    public void deletePlaceById(int id) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        places.remove(index);
    }

    //установить цену нового места по идентификатору
    public void setNewPlacePriceById(int id, double price) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        if (price < 0 || price > 150) {
            throw new Exception("Некорректная цена");
        }

        findPlace.setPrice(price);
    }

    //поля по умолчанию дня тестирования
    public void seedPlaces() {
        currentId = 0;
        places = new ArrayList<>();

        addNewPlace(75, 160, "Саша", 20.5, Place.PhoneBrand.samsung);
        addNewPlace(80, 170, "Паша", 23.0, Place.PhoneBrand.honor);
        addNewPlace(90, 180, "Маша", 44.5, Place.PhoneBrand.apple);
    }

    //сохранить в файл данных
    public void saveToDataFile(String fileName) throws Exception {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(places);
        } catch (Exception e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    //загрузить из файла данных
    public void loadFromDataFile(String fileName) throws Exception {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

            places = (ArrayList<Place>) inputStream.readObject();

            currentId = getMaxId();
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    //endregion
}



















