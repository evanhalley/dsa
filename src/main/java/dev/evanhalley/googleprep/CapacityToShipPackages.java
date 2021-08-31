package dev.evanhalley.googleprep;

public class CapacityToShipPackages {

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 10));
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4}, 3));
        System.out.println(shipWithinDays(new int[]{1,2,3,1,1}, 4));
    }

    static int shipWithinDays(int[] weights, int days) {
        int lower = 0;
        int upper = 0;

        // find the upper and lower bounds
        for (int weight : weights) {
            lower = Math.max(lower, weight);
            upper += weight;
        }
        int ans = upper;

        while (lower <= upper) {
            int mid = (upper + lower) / 2;
            int totalWeight = 0;
            int currentDay = 1;

            for (int weight : weights) {
                // passed the mid, start a new day
                if (totalWeight + weight > mid) {
                    /*
                     * Sum of the weight for the day exceeds the mid, cound as a day then start
                     * a sum for a new day
                     */
                    currentDay++;
                    totalWeight = 0;
                }
                totalWeight += weight;
            }

            // we used more days than the requirement allows so our mid was to low, raise the lower bound to mid + 1
            if (currentDay > days) {
                lower = mid + 1;
            } else {
                ans = Math.min(ans, mid);
                upper = mid - 1;
            }
        }
        return ans;
    }


}
