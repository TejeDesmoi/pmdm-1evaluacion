package edu.iesam.teslaapp.feature.carslist.domain

class getCarByIdUseCase(carRepository: CarRepository) {
    operator fun invoke(id: String): Car? { return carRepository.findById(id) }
}