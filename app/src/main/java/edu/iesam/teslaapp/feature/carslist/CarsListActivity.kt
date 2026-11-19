package edu.iesam.teslaapp.feature.carslist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.CreationExtras
import edu.iesam.teslaapp.R
import edu.iesam.teslaapp.feature.carslist.data.CarDataRepository
import edu.iesam.teslaapp.feature.carslist.data.local.CarXmlLocalDataSource
import edu.iesam.teslaapp.feature.carslist.presentation.CarListViewModel
import edu.iesam.marvelapp.core.serialization.GsonJsonSerializer
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase

class CarsListActivity : AppCompatActivity() {

    private val viewModel: CarListViewModel by viewModels {
        CarListViewModel.factory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initListeners()
    }

    private fun initListeners() {
        val labelCarId = findViewById<EditText>(R.id.input_car_id)
        val actionFind = findViewById<Button>(R.id.action_find)
        actionFind.setOnClickListener {
            buttonFindClicked(labelCarId.text.toString())
        }
    }

    private fun buttonFindClicked(carId: String) {
        //el usuario ha pulsado el botón.
        Log.d(CarsListActivity::class.java.simpleName, carId)
        val car = viewModel.buscarCoche(carId)
        val carCard = findViewById<LinearLayout>(R.id.car_card)
        val mensajeError = findViewById<TextView>(R.id.mensaje_error)

        car.let{
            mensajeError.visibility = TextView.GONE
            carCard.visibility = LinearLayout.VISIBLE
            renderCar(car)
        }
        if (car == null){
            carCard.visibility = LinearLayout.GONE
            mensajeError.visibility = TextView.VISIBLE
        }

    }

    private fun renderCar(car: Car?){
        val matriculaLabel = findViewById<TextView>(R.id.matricula_label)
        val marcaLabel = findViewById<TextView>(R.id.marca_label)
        val modeloLabel = findViewById<TextView>(R.id.modelo_label)

        matriculaLabel.text = car?.matricula
        marcaLabel.text = car?.marca
        modeloLabel.text = car?.modelo
    }

}