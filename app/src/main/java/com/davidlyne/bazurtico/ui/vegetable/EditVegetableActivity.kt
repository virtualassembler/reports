package com.davidlyne.bazurtico.ui.vegetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.davidlyne.bazurtico.MainActivity
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDataType
import kotlinx.android.synthetic.main.activity_create_vegetable.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditVegetableActivity : AppCompatActivity() {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

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
                val name = editTextVegetableName.text.toString().trim()
                val price = editTextVegetablePrice.text.toString().trim().toInt().toDouble()
                //val vegetable = VegetableDataType(editTextVegetableName.text.toString(),editTextVegetablePrice.text.toString().toInt().toDouble(),isUnit)
                if (vegetableId != null) {
                    TotalizerDatabase.getInstance(this)!!.getVegetableDAO().update(vegetableId,name,price,isUnit)
                }else{
                    Log.e("error41","EditVegetableActivity Error")
                }
//                val database = TotalizerDatabase.getInstance(this)!!.getVegetableDAO()
//
//                scope.launch {
//                    // New coroutine that can call suspend functions
//                    database.update(vegetable)
//                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}