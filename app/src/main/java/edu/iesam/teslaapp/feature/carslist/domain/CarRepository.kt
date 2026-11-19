package edu.iesam.teslaapp.feature.carslist.domain

interface CarRepository {
    fun save(cars: List<Car>)
    fun findAll(): List<Car>
    fun findById(id: String): Car?
    fun deleteById(id: String)
    fun deleteAll()
}