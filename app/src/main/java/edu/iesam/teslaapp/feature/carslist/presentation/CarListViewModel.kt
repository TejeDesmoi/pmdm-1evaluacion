package edu.iesam.teslaapp.feature.carslist.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import edu.iesam.marvelapp.core.serialization.GsonJsonSerializer
import edu.iesam.teslaapp.feature.carslist.data.local.CarXmlLocalDataSource
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.data.CarDataRepository
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase

class CarListViewModel(
    private val getCarByIdUseCase: GetCarByIdUseCase
): ViewModel() {

    //private val _uiState = MutableLiveData<UiState>()
    //val uiState: LiveData<UiState> = _uiState

    fun buscarCoche(id: String): Car? {
        //_uiState.value = UiState(getCarByIdUseCase(id))
        return getCarByIdUseCase(id)
    }

    /*data class UiState(
        private val car: Car? =null,
        private val isLoading: Boolean = false,
        private val hasError: Boolean = false
    )*/

    companion object {
            fun factory(context: Context) = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CarListViewModel(GetCarByIdUseCase(CarDataRepository(
                        CarXmlLocalDataSource(context, GsonJsonSerializer(List::class.java)),
                        CarApiRemoteDataSource()
                    )
                    )) as T
                }
            }
    }
}