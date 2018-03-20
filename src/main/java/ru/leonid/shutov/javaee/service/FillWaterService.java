package ru.leonid.shutov.javaee.service;

public interface FillWaterService {

    /**
     * Calculates volume of water, remained after the rain, in units.
     *
     * @param array - describes profile of hills (landscape)
     * @return volume of water, remained after the rain, in units
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    int fillWater(int[] array);
}
