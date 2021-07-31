package com.davidlyne.bazurtico.ui.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.davidlyne.bazurtico.MainActivity
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.ClientDataClass
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import kotlinx.android.synthetic.main.activity_create_client.*

class CreateClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_client)
        buttonCreateClient.setOnClickListener {
            //Save on database
            val name = editTextTextPersonName.text
            if(name.length > 0){
                val bazurticoClient =  ClientDataClass(1,""+name,"3144468866")
                TotalizerDatabase.getInstance(this)!!.getClientDAO().insertClient(bazurticoClient)
                Toast.makeText(applicationContext, "Cliente guardado exitosamente", Toast.LENGTH_LONG).show()
                //Redirect to main activity
                this@CreateClientActivity.startActivity(Intent(this@CreateClientActivity, MainActivity::class.java))
            }else{
                Toast.makeText(applicationContext, "Faltan Datos Importantes", Toast.LENGTH_LONG).show()
            }
        }
    }
}