package com.davidlyne.bazurtico.ui.recipient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.davidlyne.bazurtico.PdfActivity
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.*
import com.davidlyne.bazurtico.repository.VegetableRepository
import com.davidlyne.bazurtico.ui.vegetable.VegetableEvents
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_select_vegetable.*

class SelectVegetableActivity : VegetableEvents,AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var vegetableListAdapter: VegetableListAdapter
    private lateinit var vegetableRepository: VegetableRepository
    private lateinit var selectedVegetable: VegetableDataType
    private lateinit var bill: BillDataType
    private lateinit var selectedVegetableListAdapter: SelectedVegetableListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vegetable)
        setRecyclerViewSoccerLeagues()
        // Filling bill headers
        bill = TotalizerDatabase.getInstance(this)!!.getBillDAO().getCurrentBill()
        //getBillListWithId()
        val client: ClientDataType = TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientDetail(bill.clientId)
        textViewClientName.text = "CLIENTE: "+client.nameClient.toString()
        textViewconsecutive.text = "CONSECUTIVO: "+bill.id.toString()
        //onButtonAdd
        setButtonAdd()
        //onButtonSaveBill
        setButtonSaveBill()
    }

    private fun setRecyclerViewSoccerLeagues() {
        vegetableListAdapter = VegetableListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewClient.layoutManager = gridLayoutManager
        recyclerViewClient.adapter = vegetableListAdapter
        vegetableRepository = VegetableRepository(this)
        //soccerLeagueListAdapter.addAll(clientRepository.requestTeamReviewList("algo"))
        vegetableListAdapter.addAll(VegetableRepository(applicationContext).requestVegetableList())
        Snackbar.make(
            constraintLayoutClient,
            "Nueva Factura",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setButtonAdd(){
        buttonAddVegetable.setOnClickListener {
            // Agrega este producto a la List de productos seleccionados
            if(textViewSelectedVegetable.text.isNotEmpty() && editTextAmount.text.isNotEmpty()){
                val billVegetable = BillVegetableDataType(selectedVegetable.id,bill.id,Integer.parseInt(editTextAmount.text.toString().trim()))
                TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().insert(billVegetable)
                Toast.makeText(applicationContext, "vegetable added ", Toast.LENGTH_LONG).show()
                // Setea el reciclerview con la lista de vegetales actualizada
                setRecyclerViewSelectedVegetable()
                //Clear Selected Vegetable UI fields
                textViewSelectedVegetable.text = ""
                editTextAmount.text.clear()
                //editTextVegetablePrice.text.clear()
            }else{
                Toast.makeText(applicationContext, "El producto no se pudo agregar a la factura", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setButtonSaveBill(){
        buttonSaveBill.setOnClickListener {
            // Change bill state to 1
            val b = bill
            TotalizerDatabase.getInstance(this)!!.getBillDAO().updateStatus(bill.id)
            // Create and Download recipient  PDF
            // Go to main Activity
            val intent = Intent(this, PdfActivity::class.java)
            intent.putExtra("billId", bill.id.toString())
            startActivity(intent)
            //this@SelectVegetableActivity.startActivity(Intent(this@SelectVegetableActivity, PdfActivity::class.java))
        }
    }

    private fun setRecyclerViewSelectedVegetable() {
        selectedVegetableListAdapter = SelectedVegetableListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewSelectedVegetables.layoutManager = gridLayoutManager
        recyclerViewSelectedVegetables.adapter = selectedVegetableListAdapter
        selectedVegetableListAdapter.addAll(TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getSelectedVegetableList())
        //vegetableListAdapter.addAll(VegetableRepository(applicationContext).requestVegetableList())
    }

    override fun onItemClicked(vegetableDataType: VegetableDataType) {
        Log.e("#ERROR008","getBillList.count() "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList().count())
        Log.e("#ERROR008","getBillVegetableList.count() "+TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().getBillVegetableList().count())
        textViewSelectedVegetable.text = vegetableDataType.name
        selectedVegetable = vegetableDataType
    }
}