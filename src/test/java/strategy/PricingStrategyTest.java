package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PricingStrategyTest {

    @Test
    void testCarPricingStrategy() {

        PricingStrategy strategy = new CarPricingStrategy();

        double total = strategy.calculatePrice(50.0, 3);

        assertEquals(150.0, total);
    }

    @Test
    void testMotorcyclePricingStrategy() {

        PricingStrategy strategy = new MotorcyclePricingStrategy();

        double total = strategy.calculatePrice(40.0, 2);

        assertEquals(80.0, total);
    }

    @Test
    void testTruckPricingStrategy() {

        PricingStrategy strategy = new TruckPricingStrategy();

        double total = strategy.calculatePrice(100.0, 4);

        assertEquals(400.0, total);
    }

    @Test
    void testVanPricingStrategy() {

        PricingStrategy strategy = new VanPricingStrategy();

        double total = strategy.calculatePrice(75.0, 5);

        assertEquals(375.0, total);
    }

}