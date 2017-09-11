package ru.psit.example.test.store;

import ru.psit.example.test.water.AlcoholicBeverage;
import ru.psit.example.test.water.Water;

public class WhiskyStore implements WaterStore {


    public Water create(String name) {

        AlcoholicBeverage alkbeverage = new AlcoholicBeverage();

        alkbeverage.setName(name);
        alkbeverage.setPrice(1200);
        alkbeverage.setAbv(40);

        return alkbeverage;

    }
}
