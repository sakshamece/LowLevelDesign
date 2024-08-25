import java.util.*;
import java.util.concurrent.TimeUnit;

class ParkingLot {
    private final List<List<Map<String, Vehicle>>> floors;
    private final int totalFloors;
    private final int rows;
    private final int spotsPerRow;

    public ParkingLot(int totalFloors, int rows, int spotsPerRow) {
        this.totalFloors = totalFloors;
        this.rows = rows;
        this.spotsPerRow = spotsPerRow;
        this.floors = new ArrayList<>();
        for (int i = 0; i < totalFloors; i++) {
            List<Map<String, Vehicle>> floor = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                floor.add(new HashMap<>());
            }
            floors.add(floor);
        }
    }

    public boolean park(Vehicle vehicle, int floor, int row) {
        if (isValidFloor(floor) || !isValidRow(row)) {
            System.out.println("Invalid floor or row. Please select a valid floor and row.");
            return false;
        }

        Map<String, Vehicle> rowMap = floors.get(floor).get(row);
        if (rowMap.size() < spotsPerRow) {
            rowMap.put(vehicle.getNumberPlate(), vehicle);
            System.out.println(vehicle.getType() + " parked successfully at floor " + floor + ", row " + row + ".");
            return true;
        } else {
            System.out.println("This row is already full. Please select another row on floor " + floor + ".");
            return false;
        }
    }

    public boolean leave(Vehicle vehicle) {
        FloorRowPair pair = findVehicleLocation(vehicle);
        if (pair == null) {
            System.out.println(vehicle.getType() + " not found.");
            return false;
        }

        pair.rowMap.remove(vehicle.getNumberPlate());
        double hours = calculateParkingHours(vehicle);
        double cost = vehicle.calculateCost(hours);
        System.out.println(vehicle.getType() + " left successfully from floor " + pair.floor + ". Total cost: " + cost);
        return true;
    }

    private FloorRowPair findVehicleLocation(Vehicle vehicle) {
        for (int i = 0; i < totalFloors; i++) {
            for (int j = 0; j < rows; j++) {
                Map<String, Vehicle> rowMap = floors.get(i).get(j);
                if (rowMap.containsKey(vehicle.getNumberPlate())) {
                    return new FloorRowPair(i, j, rowMap);
                }
            }
        }
        return null;
    }

    public long calculateParkingHours(Vehicle vehicle) {
        long currentTime = System.currentTimeMillis();
        long parkTime = vehicle.getParkTime();
        long duration = currentTime - parkTime;

        // Convert duration to hours and return the ceiling value
        return (long) Math.ceil((double) duration / (1000 * 60 * 60));
    }


    public int availableSpots(int floor) {
        if (isValidFloor(floor)) {
            return 0;
        }
        return floors.get(floor).stream()
                .mapToInt(row -> spotsPerRow - row.size())
                .sum();
    }

    public void clear() {
        for (List<Map<String, Vehicle>> floor : floors) {
            for (Map<String, Vehicle> row : floor) {
                row.clear();
            }
        }
    }

    private boolean isValidFloor(int floor) {
        return floor < 0 || floor >= totalFloors;
    }

    private boolean isValidRow(int row) {
        return row >= 0 && row < rows;
    }

    private static class FloorRowPair {
        final int floor;
        final int row;
        final Map<String, Vehicle> rowMap;

        FloorRowPair(int floor, int row, Map<String, Vehicle> rowMap) {
            this.floor = floor;
            this.row = row;
            this.rowMap = rowMap;
        }
    }
}
