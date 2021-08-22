package com.davidlyne.bazurtico.ui.vegetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDataType
import kotlinx.android.synthetic.main.activity_create_vegetable.*

class CreateVegetableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vegetable)
        buttonSaveVegetable.setOnClickListener {
            var isUnit: Int = if (checkBoxIsUnit.isChecked) 1 else 0
            var list = TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableList().size
            if(editTextVegetableName.text.isNotEmpty() && editTextVegetablePrice.text.isNotEmpty()){
                TotalizerDatabase.getInstance(this)!!.getVegetableDAO().insert(VegetableDataType(editTextVegetableName.text.toString(),editTextVegetablePrice.text.toString().trim().toDouble(),isUnit))
            }else{
                Toast.makeText(applicationContext, "Faltan Datos Importantes", Toast.LENGTH_LONG).show()
            }
            var list2 = TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableList().size
            var list3 = TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableList()
        }
    }
}