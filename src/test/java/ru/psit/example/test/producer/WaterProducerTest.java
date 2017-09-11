package ru.psit.example.test.producer;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoFramework;
import org.mockito.verification.VerificationMode;
import ru.psit.example.test.store.FantaStore;
import ru.psit.example.test.store.WaterStore;
import ru.psit.example.test.water.AlcoholicBeverage;
import ru.psit.example.test.water.SoftDrink;
import ru.psit.example.test.water.Water;

public class WaterProducerTest  {

    private final static String FANTA = "fanta";
    private final static String JIN = "jin";
    private final static String ANY = "any";

    private final static double PRICE = 1000;

    private static FantaStore fantaStore;
    private static Water waterOne;

    private static Water waterJIN;
    private static Water waterFanta;

    private static WaterProducer waterProducer;


    @BeforeClass
    public static void initData(){

        waterProducer = new WaterProducer();

        fantaStore = new FantaStore();
        waterOne = fantaStore.create(FANTA);

        waterJIN = new AlcoholicBeverage();
        waterJIN.setName(JIN);

        waterFanta = new SoftDrink();
        waterFanta.setName(FANTA);

    }

    @Test
    public void testCreate_fanta_fanta() throws Exception {



        WaterProducer waterProducer = new WaterProducer();
        waterProducer.createStore(FANTA);
        Water waterTwo = waterProducer.order(FANTA);

        Assert.assertEquals("not equal water", waterOne.getName(), waterTwo.getName());

    }

    @Test
    public void testWaterProducerOrder_jin_resiltjin()
    {

        WaterStore waterStore = Mockito.mock(WaterStore.class);
        Mockito.when(waterStore.create(JIN)).thenReturn(waterJIN);

        WaterProducer waterProducer = new WaterProducer();
        waterProducer.getStories().put(JIN, waterStore);

        Water waterResult = waterProducer.order(JIN);

        Assert.assertEquals(JIN, waterResult.getName());

    }

    @Test
    public void testWaterProducerOrder_JinFantaList_resiltJinFanta()
    {

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(FANTA)).thenReturn(waterFanta);

        WaterStore jinStore = Mockito.mock(WaterStore.class);
        Mockito.when(jinStore.create(JIN)).thenReturn(waterJIN);



        waterProducer.getStories().put(FANTA, fantaStore);
        waterProducer.getStories().put(JIN, jinStore);

        Water jinResult = waterProducer.order(JIN);

        Assert.assertEquals(JIN, jinResult.getName());

    }

    @Test(expected=Exception.class)
    public void testWaterProducerOrder_JIN_resiltException() throws Exception {

        Water waterResult = fantaStore.create(ANY);

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(FANTA)).thenReturn(waterFanta);

        WaterStore jinStore = Mockito.mock(WaterStore.class);
        Mockito.when(jinStore.create(JIN)).thenReturn(waterJIN);

        waterProducer.createStore(ANY);

    }


    @Test
    public void testWaterProducerOrder_anyValue_resiltjin() throws Exception {


        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(Mockito.anyString())).thenReturn(waterFanta);

        waterProducer.getStories().put(FANTA, fantaStore);

        Water fantaResult = fantaStore.create(ANY);

        Assert.assertEquals(FANTA, fantaResult.getName());

    }

    @Test
    public void testStubWaterProducerOrder_jin_resiltjin() throws Exception {

        Water water = Mockito.mock(Water.class);

        Mockito.when(water.getPrice()).thenReturn(PRICE);

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(JIN)).thenReturn(water);

        WaterProducer waterProducer = new WaterProducer();
        waterProducer.getStories().put(JIN, fantaStore);

        Water waterResult = waterProducer.order(JIN);

        Assert.assertEquals(PRICE, waterResult.getPrice(), 10);

    }

    @Test
    public void testVerifyCallName_atLeastOnce() throws Exception {

        Water water = Mockito.mock(Water.class);

        Mockito.when(water.getPrice()).thenReturn(PRICE);

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(JIN)).thenReturn(water);

        WaterProducer waterProducer =  Mockito.mock(WaterProducer.class);
        waterProducer.getStories().put(JIN, fantaStore);

        Water waterResult = waterProducer.order(JIN);


        Mockito.verify(waterProducer, Mockito.atLeast(1)).createStore(Mockito.anyString());
        //Mockito.verify(waterProducer, Mockito.atLeast(2)).order(Mockito.anyString());

    }

    @Test
    public void testVerifyCallName_Never() throws Exception {

        Water water = Mockito.mock(Water.class);

        Mockito.when(water.getPrice()).thenReturn(PRICE);

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(JIN)).thenReturn(water);

        WaterProducer waterProducer =  Mockito.mock(WaterProducer.class);
        waterProducer.getStories().put(JIN, fantaStore);

        Water waterResult = waterProducer.order(JIN);


        Mockito.verify(waterProducer, Mockito.never()).createStore(Mockito.anyString());
        //Mockito.verify(waterProducer, Mockito.atLeast(2)).order(Mockito.anyString());

    }

    @Test
    public void testSpyWaterProducerOrder_jin_resiltjin()
    {

        WaterStore fantaStore = Mockito.mock(WaterStore.class);
        Mockito.when(fantaStore.create(Mockito.anyString())).thenReturn(waterFanta);

        WaterProducer waterProducerSpy = Mockito.spy(waterProducer);
        Mockito.when(waterProducerSpy.order(Mockito.anyString())).thenReturn(waterFanta);

        waterProducerSpy.getStories().put(JIN, fantaStore);
        Water waterResult = waterProducerSpy.order(ANY);

        Assert.assertEquals(FANTA, waterResult.getName());

    }
}
