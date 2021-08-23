package com.davidlyne.bazurtico.ui.vegetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDataType
import kotlinx.android.synthetic.main.activity_create_vegetable.*

class EditVegetableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vegetable)
        val vegetableId=intent.getStringExtra("vegetableId")
        val vegetable = TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableById(vegetableId.toString())
        editTextVegetableName.setText(vegetable.name)
        editTextVegetablePrice.setText(vegetable.price.toString())
        if(vegetable.isUnit == 1){
            checkBoxIsUnit.isChecked = true
        }else{
            checkBoxIsUnit.isChecked = false
        }
        buttonSaveVegetable.setOnClickListener {
            var isUnit: Int = if (checkBoxIsUnit.isChecked) 1 else 0
            if(editTextVegetablePrice.text.toString().isNotEmpty()){
                TotalizerDatabase.getInstance(this)!!.getVegetableDAO().insert(VegetableDataType(editTextVegetableName.text.toString(),editTextVegetableName.text.toString().toInt().toDouble(),isUnit))
            }
        }
    }
}