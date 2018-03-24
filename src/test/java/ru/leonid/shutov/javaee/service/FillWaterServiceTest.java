package ru.leonid.shutov.javaee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillWaterServiceTest {
    private FillWaterService service;

    @BeforeEach
    void setUp() {
        service = new FillWaterServiceImpl();
    }

    @Test
    void fillWaterWithoutHillsTest() {
        int[] array = {3, 3, 3, 3, 3};
        long volume = service.fillWater(array);
        assertEquals(0, volume);
    }

    @Test
    void fillWaterWithOneHoleTest() {
        int[] array = {2, 0, 2};
        long volume = service.fillWater(array);
        assertEquals(2, volume);
    }


    @Test
    void fillWaterWithOneLongerHoleTest() {
        int[] array = {2, 0, 1, 0, 2};
        long volume = service.fillWater(array);
        assertEquals(5, volume);
    }


    @Test
    void fillWaterWithTwoHolesTest() {
        int[] array = {2, 0, 3, 1, 5};
        long volume = service.fillWater(array);
        assertEquals(4, volume);
    }

    @Test
    void fillWaterRightEdgeTest() {
        int[] array = {3, -1, -1};
        long volume = service.fillWater(array);
        assertEquals(0, volume);
    }

    @Test
    void fillWaterLeftEdgeTest() {
        int[] array = {-5, -1, 1};
        long volume = service.fillWater(array);
        assertEquals(0, volume);
    }

    @Test
    void fillWaterLargeInputTest() {
        int[] array = {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
        long volume = service.fillWater(array);
        assertEquals((long)Integer.MAX_VALUE-Integer.MIN_VALUE, volume);
    }
}