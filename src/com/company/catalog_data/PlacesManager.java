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
    public void addNewPlace(Place.PhoneBrand phoneBrand, String model, int weight, String owner, double price, int battery, double releaseDate) {
        currentId++;

        Place place = new Place(currentId,phoneBrand,model, weight, owner, price, battery, releaseDate);

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


    //region changing characteristics

    //установить цену нового места по идентификатору
    public void setNewPlacePriceById(int id, double price) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        if (price < 0 || price > 100000) {
            throw new Exception("Некорректная цена");
        }

        findPlace.setPrice(price);
    }

    //установить модель нового места по идентификатору
    public void setNewPlaceModelById(int id, String model) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        findPlace.setModel(model);
    }

    //установить вес нового места по идентификатору
    public void setNewPlaceWeightById(int id, int weight) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        findPlace.setWeight(weight);
    }

    //установить покупателя нового места по идентификатору
    public void setNewPlaceOwnerById(int id, String owner) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        findPlace.setOwner(owner);
    }

    //установить аккумулятор нового места по идентификатору
    public void setNewPlaceBatteryById(int id, int battery) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        findPlace.setBattery(battery);
    }

    //установить дату выхода нового места по идентификатору
    public void setNewPlaceReleaseDateById(int id, double releaseDate) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        if (releaseDate < 0 || releaseDate > 10000) {
            throw new Exception("Некорректная");
        }

        findPlace.setReleaseDate(releaseDate);
    }

    //endregion


    //поля по умолчанию дня тестирования
    public void seedPlaces() {
        currentId = 0;
        places = new ArrayList<>();

        addNewPlace(Place.PhoneBrand.samsung,"Galaxy",100,"Александр",30000,3000,2019);
        addNewPlace(Place.PhoneBrand.honor,"X10",150,"Павел",40000,4000,2020);

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
