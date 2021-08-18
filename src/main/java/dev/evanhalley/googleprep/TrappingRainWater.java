package dev.evanhalley.googleprep;

import java.util.LinkedList;
import java.util.List;

public class TrappingRainWater {

    private static class Peak {
        private final int index;
        private final int height;

        public Peak(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Peak{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }

    /**
     * [0,1,0,2,1,0,1,3,2,1,2,1]
     * [4,2,0,3,2,5]
     * @param args
     */

    public static void main(String[] args) {
        LinkedList<Peak> peakQueue = new LinkedList<>();
        int[] heights = { 5,4,1,2 };
        //1/2/3/2
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        //trappingRainWater.findPeaks(heights, 0, peakQueue);
        //int value = trappingRainWater.calculateTrappedRainWater(heights, peakQueue);
        trappingRainWater.calculateTrappedWater(heights, 0);
       // System.out.println(value);
    }

        /*
    1) get a pair of peaks
2) if we are at the end (nextPeak = null), we are done caluclating
3) Determine the floor of the two peaks (ceiling))
4) For the given pair of peaks, iterate through all of the heights beteen those peaks, calculating the trapped water
  ceiling - height @ i, aggregate this value
5) repeat for each pair of peaks
     */

    public int calculateTrappedRainWater(int[] heights, LinkedList<Peak> peakQueue) {
        int value = 0;

        while (!peakQueue.isEmpty()) {
            Peak peak = peakQueue.remove();
            Peak nextPeak = peakQueue.peek();

            if (nextPeak == null) {
                break;
            }

            int ceiling = Math.min(peak.height, nextPeak.height);

            for (int i = peak.index + 1; i < nextPeak.index; i++) {

                if (heights[i] < ceiling) {
                    value += ceiling - heights[i];
                }
            }
        }
        return value;
    }

    public int calculateTrappedWater(int[] heights, int index) {
        int i = index;
        Peak left = null;
        Peak right = null;
        Peak possiblePeak = null;

        // * [0,1,0,2,1,0,1,3,2,1,2,1]

        while (!(left != null && right != null)) {
            int height = heights[i];

            // we see the first peak
            if (left == null && heights.length > i + 1 && height > heights[i + 1]) {
                left = new Peak(i, height);
            }

            else if (left != null) {

                // we hit a peak thats not right after another peak and its taller
                if (left.index + 1 < i && height > left.height) {
                    right = new Peak(i, height);
                }

                // peak thats the same height as the last peak/height (or greater)
                else if (left.index + 1 == i && height >= left.height) {
                    left = new Peak(i, height);
                }

                else if (height > 0 && (height <= left.height && left.index + 1 < i)
                        && (possiblePeak == null || height > possiblePeak.height)) {

                    if (i == heights.length - 1) {
                        right = new Peak(i, height);
                    } else {
                        possiblePeak = new Peak(i, height);
                    }
                }
            }
            i++;
        }
        return 1;
    }

    public void findPeaks(int[] heights, int index, LinkedList<Peak> peakQueue) {

        if (index >= heights.length - 1) {
            return;
        }

        Peak possiblePeak = null;
        boolean reachedEnd = false;

        for (int i = index; i < heights.length; i++) {
            int height = heights[i];

            // we see the first peak
            if (peakQueue.isEmpty() && heights.length > i + 1 && height > heights[i + 1]) {
                peakQueue.add(new Peak(i, height));
                continue;
            }

            if (!peakQueue.isEmpty()) {

                // we hit a peak thats not right after another peak and its taller
                if (peakQueue.peekLast().index + 1 < i && height > peakQueue.peekLast().height) {
                    peakQueue.add(new Peak(i, height));
                    possiblePeak = null;
                    continue;
                }

                // peak thats the same height as the last peak/height (or greater)
                if (peakQueue.peekLast().index + 1 == i && height >= peakQueue.peekLast().height) {
                    peakQueue.add(new Peak(i, height));
                    possiblePeak = null;
                    continue;
                }

                if (height > 0 && (height <= peakQueue.peekLast().height && peakQueue.peekLast().index + 1 < i)
                        && (possiblePeak == null || height > possiblePeak.height)) {
                    possiblePeak = new Peak(i, height);
                    continue;
                }

                // if we are at the end of the array and the last height is less than the last peak, stop running the for loop
                if (i == heights.length - 1 && height <= peakQueue.peekLast().height && possiblePeak == null) {
                    reachedEnd = true;
                }
            }
        }

        if (possiblePeak != null) {
            peakQueue.add(possiblePeak);
        }

        if (!reachedEnd && peakQueue.peekLast() != null) {
            findPeaks(heights, peakQueue.peekLast().index, peakQueue);
        }
    }

}
