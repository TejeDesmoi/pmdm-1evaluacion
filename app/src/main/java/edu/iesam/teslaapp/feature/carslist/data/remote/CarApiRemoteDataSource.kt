package edu.iesam.teslaapp.feature.carslist.data.remote

import edu.iesam.teslaapp.feature.carslist.domain.Car

class CarApiRemoteDataSource {


    fun getAll(): List<Car> {
        return listOf(
            Car("1","matricula1","marca1","modelo1"),
            Car("2","matricula2","marca2","modelo2"),
            Car("3","matricula3","marca3","modelo3"),
            Car("4","matricula4","marca4","modelo4"),
            Car("5","matricula5","marca5","modelo5"),
            Car("6","matricula6","marca6","modelo6"),
        )
    }

}