package ru.psit.example.test.producer;

import ru.psit.example.test.store.FantaStore;
import ru.psit.example.test.store.JinStore;
import ru.psit.example.test.store.WaterStore;
import ru.psit.example.test.store.WhiskyStore;
import ru.psit.example.test.water.Water;

import java.util.HashMap;
import java.util.Map;


public class WaterProducer {

    private Map<String, WaterStore> stories = new HashMap<String, WaterStore>();

    public void createStore(String name) throws Exception{

        WaterStore waterStore = null;


        if (name.equals("fanta"))
        {
            waterStore = new FantaStore();


        }else if(name.equals("whisky")){

            waterStore = new WhiskyStore();

        }
        else if (name.equals("jin"))
        {

            waterStore = new JinStore();

        }else
        {
            throw new Exception("Didn't drink's type");
        }

        stories.put(name, waterStore);

    }

    public Water order(String name)
    {
        if (stories.containsKey(name))
        {
            WaterStore waterStore = stories.get(name);
            return waterStore.create(name);
        }

        return null;

    }

    public Map<String, WaterStore> getStories() {
        return stories;
    }

    public void setStories(Map<String, WaterStore> stories) {
        this.stories = stories;
    }
}
