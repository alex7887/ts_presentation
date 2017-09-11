package ru.psit.example.test.store;

import ru.psit.example.test.water.SoftDrink;
import ru.psit.example.test.water.Water;

public class FantaStore implements WaterStore {


    public Water create(String name) {

        SoftDrink softDrink = new SoftDrink();

        softDrink.setName(name);
        softDrink.setPrice(80);
        softDrink.setColor("orange");

        return softDrink;
    }
}
