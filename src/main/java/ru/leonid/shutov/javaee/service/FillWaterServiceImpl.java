package ru.leonid.shutov.javaee.service;

public class FillWaterServiceImpl implements FillWaterService {

    /**
     * Calculates volume of water, remained after the rain, in units.
     *
     * @param array - describes profile of hills
     * @return volume of water, remained after the rain, in units
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    @Override
    public int fillWater(int[] array) {
        long volume = 0;
        long left = 0;
        long right = 0;
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            if (array[low] < array[high]) {
                if (array[low] > left) {
                    left = array[low];
                } else {
                    volume += (left - array[low]);
                }
                low++;
            } else {
                if (array[high] > right) {
                    right = array[high];
                } else {
                    volume += (right - array[high]);
                }
                high--;
            }
        }
        return Math.toIntExact(volume);
    }
}
