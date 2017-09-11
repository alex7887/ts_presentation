package ru.psit.example.test.store;

import ru.psit.example.test.water.AlcoholicBeverage;
import ru.psit.example.test.water.Water;

public class JinStore implements WaterStore{

    public Water create(String name) {
        AlcoholicBeverage alkbeverage = new AlcoholicBeverage();

        alkbeverage.setName(name);
        alkbeverage.setPrice(1500);
        alkbeverage.setAbv(40);

        return alkbeverage;
    }
}
