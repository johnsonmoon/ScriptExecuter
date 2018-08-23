package xuyihao.script.executor.test.entity;

/**
 * Create by johnsonmoon at 2018/8/22 17:17.
 */
public class Car {
	private String name;
	private String brand;
	private Integer capacity;

	public static Car create() {
		return new Car();
	}

	public static Car create(String name, String brand, Integer capacity) {
		Car car = new Car();
		car.name = name;
		car.brand = brand;
		car.capacity = capacity;
		return car;
	}

	public Car createCar() {
		return Car.create();
	}

	public Car createCar(String name, String brand, Integer capacity) {
		return Car.create(name, brand, capacity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Car{" +
				"name='" + name + '\'' +
				", brand='" + brand + '\'' +
				", capacity=" + capacity +
				'}';
	}
}
