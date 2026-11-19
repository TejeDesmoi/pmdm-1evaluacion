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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.teslaapp.R
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.presentation.CarListViewModel

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

        viewModel.uiState.observe(this){ uiState ->
            Log.d("MainActivity", uiState.isLoading.toString())
            bind(uiState)
        }
    }

    private fun bind(uiState: CarListViewModel.UiState?) {
        renderCar(uiState?.car)
    }

    private fun buttonFindClicked(carId: String) {
        //el usuario ha pulsado el botón.
        Log.d(CarsListActivity::class.java.simpleName, carId)
        viewModel.buscarCoche(carId)
    }

    private fun renderCar(car: Car?){
        val carCard = findViewById<LinearLayout>(R.id.car_card)
        val mensajeError = findViewById<TextView>(R.id.mensaje_error)
        val matriculaLabel = findViewById<TextView>(R.id.matricula_label)
        val marcaLabel = findViewById<TextView>(R.id.marca_label)
        val modeloLabel = findViewById<TextView>(R.id.modelo_label)

        if (car == null) {
            carCard.visibility = LinearLayout.GONE
            mensajeError.visibility = TextView.VISIBLE
        } else {
            carCard.visibility = LinearLayout.VISIBLE
            mensajeError.visibility = TextView.GONE
            matriculaLabel.text = car.matricula
            marcaLabel.text = car.marca
            modeloLabel.text = car.modelo
        }
    }

}