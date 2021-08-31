package dev.evanhalley.googleprep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MinimumFuelStops {

    public static void main(String[] args) {
        int target = 100;
        int startFuel = 25;
        int[][] stations = { {25, 25}, {50,25}, {75,25} }; //[[10,60],[20,30],[30,30],[60,40]]
        new MinimumFuelStops().minRefuelStops(target, startFuel, stations);
    }

    class Solution {

        static final int POSITION = 0;
        static final int FUEL = 1;

        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int numberOfStations = stations.length;

            // dp represents the amount of fuel it took to get to the i-th station
            long[] dp = new long[numberOfStations + 1];
            dp[0] = startFuel;

            for (int i = 0; i < numberOfStations; ++i)

                for (int j = i; j >= 0; --j)

                    // fuel at the j-th station can get us to the i-th station
                    if (dp[j] >= stations[i][POSITION]) {
                        // we can now reach capacity further with j+1 refueling stops
                        dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][FUEL]);
                    }

            for (int i = 0; i <= numberOfStations; ++i) {

                // allows us to minimize the number of stations
                if (dp[i] >= target) {
                    return i;
                }
            }
            return -1;
        }
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Stop start = new Stop(0);
        return travelToStop(start, startFuel, stations, target, 0);
    }

    public int travelToStop(Stop currentStop, int fuelRemaining, int[][] stations, int target, int numStops) {
        int distanceToTarget = target - currentStop.position;

        if (currentStop.fuel > -1) {
            fuelRemaining += currentStop.fuel;
        }
        // we can reach the taret
        if (fuelRemaining >= distanceToTarget) {
            return numStops;
        }
        PriorityQueue<Stop> nextStops = getNextStops(currentStop.position, distanceToTarget, fuelRemaining, stations, currentStop.index + 1);

        // no more reachable gas statios
        if (nextStops.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        // go to the station that can reach the target
        if (nextStops.peek().canReachTarget()) {
            Stop nextStop = nextStops.remove();
            return travelToStop(nextStops.remove(), fuelRemaining - (nextStop.position - currentStop.position),
                    stations, target, numStops + 1);
        }

        // let's try the min of the next set of reachable gas stations
        List<Integer> results = new ArrayList<>();

        while (!nextStops.isEmpty()) {
            Stop nextStop = nextStops.remove();
            int result = travelToStop(nextStop, fuelRemaining - (nextStop.position - currentStop.position),
                    stations, target, numStops + 1);
            results.add(result);
        }
        int minStops = Integer.MAX_VALUE;
        for (Integer stops : results) {
               minStops = Math.min(minStops, stops);
        }
        return minStops;
    }

    public PriorityQueue<Stop> getNextStops(int currentLocation, int distanceToTarget, int fuelRemaining, int[][] stations, int stationIndex) {
        PriorityQueue<Stop> nextStopQueue = new PriorityQueue<>();

        for (int i = stationIndex; i < stations.length; i++) {
            int[] station = stations[i];
            // we can get to this station, add it to the queu
            if (fuelRemaining >= station[0] - currentLocation) {
                Stop nextStop = new Stop(i, distanceToTarget, fuelRemaining, station[0], station[1]);
                nextStopQueue.add(nextStop);
            } else {
                break;
            }
        }
        return nextStopQueue;
    }

    public static class Stop implements Comparable<Stop> {
        int fuelRemaining;
        int distanceToTarget;
        int position;
        int fuel;
        int index;

        public Stop(int position) {
            this.position = position;
            this.index = -1;
            this.fuel = -1;
        }

        public Stop(int index, int distanceToTarget, int fuelRemaining, int position, int fuel) {
            this.index = index;
            this.distanceToTarget = distanceToTarget;
            this.fuelRemaining = fuelRemaining;
            this.position = position;
            this.fuel = fuel;
        }

        public boolean canReachTarget() {
            return this.fuelRemaining - this.position + this.fuel >= distanceToTarget;
        }

        @Override
        public int compareTo(Stop that) {
            int thisRange = this.fuelRemaining - this.position + this.fuel;
            int thatRange = that.fuelRemaining - that.position + that.fuel;

            if (thisRange >= distanceToTarget && thatRange >= distanceToTarget) {
                return Integer.compare(thisRange, thatRange);
            } else if (thisRange >= distanceToTarget) {
                return -1;
            } else if (thatRange >= distanceToTarget) {
                return 1;
            }
            int distanceToThisStation = this.fuelRemaining - this.position;
            int distanceToThatStation = that.fuelRemaining - that.position;
            return Integer.compare(distanceToThisStation, distanceToThatStation);
        }

        @Override
        public String toString() {
            return "Stop{" +
                    "fuelRemaining=" + fuelRemaining +
                    ", distanceToTarget=" + distanceToTarget +
                    ", position=" + position +
                    ", fuel=" + fuel +
                    ", index=" + index +
                    '}';
        }
    }

/**

 travelToNextStop(location, fuelRemaining, stations, targets, stops):
 int distanceRemaining = target - location

 if (fuelRemaining >= distanceRemaining):
 return stops;

 for station in stations:
 // get neighbors?
 if fuelRemaining >= station.distance:

 if (fuelRemaining - station.position + station.fuel) >= distanceRemaining
 travelToNextTop(station, fuelRemaining - station.position + station.fuel, stations, stops + 1)

 if (fuelRemaining >= station.position)
 travelToNextStop(station, fuelRemaining - station.position, stations, stops + 1

 return -1;


 Algorithm (recursive):

 From our starting point, we prioritize the stations.
 Calculate distance-to-target (dtt)
 If fuelRemaining >= distance-to-target, go to the target
 Get all stations where fuelRemaining - station.position + station.fuel >= distance-to-target, travel to any of these stations
 Get all stations where I can reach given my fuelRemaining.  Travel to all of them, but pick the station that minimizes future stops
 Return -1 (infinity), because we can’t reach the next station or the target


 */

}
