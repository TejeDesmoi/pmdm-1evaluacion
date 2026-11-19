package edu.iesam.teslaapp.feature.carslist.data

import edu.iesam.teslaapp.feature.carslist.data.local.CarXmlLocalDataSource
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.CarRepository

class CarDataRepository(
    private val localDataSource: CarXmlLocalDataSource,
    private val remoteDataSource: CarApiRemoteDataSource
): CarRepository {
    override fun save(cars: List<Car>) {
        localDataSource.save(cars)
    }

    override fun findAll(): List<Car> {
        return localDataSource.findAll()
    }

    override fun findById(id: String): Car? {
        return remoteDataSource.findById(id)
    }

    override fun deleteById(id: String) {
        localDataSource.deleteById(id)
    }

    override fun deleteAll() {
        localDataSource.deleteAll()
    }
}