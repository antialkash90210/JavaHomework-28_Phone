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
    public void addNewPlace(Place.PhoneBrand phoneBrand, int length, int width, String owner, double price) {
        currentId++;

        Place place = new Place(currentId,phoneBrand,length, width, owner, price);

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

        addNewPlace(Place.PhoneBrand.samsung,20,100,"Саша",30);
        addNewPlace(Place.PhoneBrand.honor,30,150,"Паша",40);

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
