import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class ParkingLot {
    private final Map<Integer, Map<Integer, Map<String, Vehicle>>> floors;
    private final int totalFloors;
    private final int rows;
    private final int spotsPerRow;

    public ParkingLot(int totalFloors, int rows, int spotsPerRow) {
        this.totalFloors = totalFloors;
        this.rows = rows;
        this.spotsPerRow = spotsPerRow;
        this.floors = new HashMap<>();
        for (int i = 0; i < totalFloors; i++) {
            this.floors.put(i, new HashMap<>());
        }
    }

    public boolean park(Vehicle vehicle, int floor, int row) {
        if (isValidFloor(floor)) {
            Map<Integer, Map<String, Vehicle>> floorMap = getFloorMap(floor);
            if (floorMap.computeIfAbsent(row, k -> new HashMap<>()).size() < spotsPerRow) {
                String numberPlate = vehicle.getNumberPlate();
                floorMap.get(row).put(numberPlate, vehicle);
                System.out.println(vehicle.getType() + " parked successfully at floor " + floor + ", row " + row + ".");
                return true;
            } else {
                System.out.println("This floor row is already full. Please select another row on floor " + floor + ".");
                return false;
            }
        } else {
            System.out.println("Invalid floor. Please select a valid floor.");
            return false;
        }
    }

    public boolean leave(Vehicle vehicle) {
        FloorRowPair pair = findVehicleLocation(vehicle);
        if (pair != null) {
            Vehicle ownerVehicle = pair.floorMap.get(pair.row).remove(vehicle.getNumberPlate());
            double hours = calculateParkingHours(ownerVehicle);
            double cost = ownerVehicle.calculateCost(hours);
            System.out.println(vehicle.getType() + " left successfully from floor " + pair.floor + ". Total cost: " + cost);
            return true;
        } else {
            System.out.println(vehicle.getType() + " not found.");
            return false;
        }
    }

    private FloorRowPair findVehicleLocation(Vehicle vehicle) {
        for (int i = 0; i < totalFloors; i++) {
            Map<Integer, Map<String, Vehicle>> floorMap = floors.get(i);
            if (floorMap != null) {
                for (int j = 0; j < rows; j++) {
                    Map<String, Vehicle> rowMap = floorMap.get(j);
                    if (rowMap != null && rowMap.containsKey(vehicle.getNumberPlate())) {
                        return new FloorRowPair(i, j, floorMap);
                    }
                }
            }
        }
        return null;
    }

    public double calculateParkingHours(Vehicle vehicle) {
        FloorRowPair pair = findVehicleLocation(vehicle);
        if (pair != null) {
            Vehicle ownerVehicle = pair.floorMap.get(pair.row).get(vehicle.getNumberPlate());
            long currentTime = System.currentTimeMillis();
            long parkTime = ownerVehicle.getParkTime();
            long duration = currentTime - parkTime;
            return TimeUnit.MILLISECONDS.toHours(duration);
        }
        return 0;
    }

    public int availableSpots(int floor) {
        int count = 0;
        Map<Integer, Map<String, Vehicle>> floorMap = floors.get(floor);
        if (floorMap != null) {
            for (int r = 0; r < rows; r++) {
                Map<String, Vehicle> rowMap = floorMap.get(r);
                count += (rowMap == null ? spotsPerRow : spotsPerRow - rowMap.size());
            }
        }
        return count;
    }

    public void clear() {
        for (int i = 0; i < totalFloors; i++) {
            getFloorMap(i).clear();
        }
    }

    private boolean isValidFloor(int floor) {
        return floor >= 0 && floor < totalFloors;
    }

    private Map<Integer, Map<String, Vehicle>> getFloorMap(int floor) {
        return floors.computeIfAbsent(floor, k -> new HashMap<>());
    }

    private static class FloorRowPair {
        final int floor;
        final int row;
        final Map<Integer, Map<String, Vehicle>> floorMap;

        FloorRowPair(int floor, int row, Map<Integer, Map<String, Vehicle>> floorMap) {
            this.floor = floor;
            this.row = row;
            this.floorMap = floorMap;
        }
    }
}

