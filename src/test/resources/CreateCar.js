function execute(car) {
	var result = car.createCar();
	result.name = car.name;
	result.brand = car.brand;
	result.capacity = car.capacity;
	return result;
}