// Product class
class Car {
    private String make;
    private String model;
    private int year;
    private String color;

    public Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}

// Builder interface
interface CarBuilder {
    Car build();
}

// Concrete builder class
class CarBuilderImpl implements CarBuilder {
    private String make;
    private String model;
    private int year;
    private String color;

    public CarBuilderImpl setMake(String make) {
        this.make = make;
        return this;
    }

    public CarBuilderImpl setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilderImpl setYear(int year) {
        this.year = year;
        return this;
    }

    public CarBuilderImpl setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public Car build() {
        return new Car(make, model, year, color);
    }
}

// Director class (optional)
class CarManufacturer {
    private CarBuilder carBuilder;

    public CarManufacturer(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car constructCar(String make, String model, int year, String color) {
        return carBuilder.setMake(make)
                .setModel(model)
                .setYear(year)
                .setColor(color)
                .build();
    }
}

public class Main {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilderImpl();
        CarManufacturer carManufacturer = new CarManufacturer(carBuilder);

        Car car = carManufacturer.constructCar("Toyota", "Camry", 2022, "Red");
        System.out.println(car);
    }
}
