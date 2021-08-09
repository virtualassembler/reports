package com.davidlyne.bazurtico.ui.recipient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.*
import com.davidlyne.bazurtico.repository.VegetableRepository
import com.davidlyne.bazurtico.ui.client.ClientEvents
import com.davidlyne.bazurtico.ui.client.VegetableEvents
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_select_vegetable.*

class SelectVegetableActivity : VegetableEvents,AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var vegetableListAdapter: VegetableListAdapter
    private lateinit var vegetableRepository: VegetableRepository
    //private lateinit var vegetableList: List<VegetableDataType>
    private lateinit var selectedVegetable: VegetableDataType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vegetable)
        setRecyclerViewSoccerLeagues()
        // Filling bill headers
        val bill : BillDataType = TotalizerDatabase.getInstance(this)!!.getBillDAO().getCurrentBill()
        val client: ClientDataType = TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientDetail(bill.clientId)
        textViewClientName.text = "CLIENTE: "+client.nameClient.toString()
        textViewconsecutive.text = "CONSECUTIVO: "+bill.id.toString()
        //onButtonAdd
        buttonAddVegetablex.setOnClickListener {
            Toast.makeText(applicationContext, "pulsado add", Toast.LENGTH_LONG).show()
            // Agrega este producto a la List de productos seleccionados
            if(textViewSelectedVegetable.text.isNotEmpty() && editTextAmount.text.isNotEmpty() && editTextPrice.text.isNotEmpty()){
                Toast.makeText(applicationContext, "pulsadox "+selectedVegetable.id, Toast.LENGTH_LONG).show()
                Log.e("#ERROR009","aaa"+ selectedVegetable.id)
                TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().insert(BillVegetableDataType(selectedVegetable.id,bill.id,Integer.parseInt(editTextAmount.text.toString().trim()) ,editTextPrice.text.toString().trim().toFloat()))
                // Setea el reciclerview con la lista de vegetales actualizada

            }else{
                Toast.makeText(applicationContext, "El producto no se pudo agregar a la factura", Toast.LENGTH_LONG).show()
            }
        }

        //onButtonSaveBill
        buttonSaveBill.setOnClickListener {
            // Change bill state to 1
        }
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
            "listado de clientes",
            Snackbar.LENGTH_LONG
        ).show()
        /*
        soccerLeagueRepository = SoccerLeagueRepository(this)
        if (hasConnection()) {
            soccerLeagueListAdapter.addAll(soccerLeagueRepository.requestMovieReviewList(league))
            Snackbar.make(
                constraintLayoutMainActivity,
                getString(R.string.movie_review_database_updated),
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            soccerLeagueListAdapter.addAll(soccerLeagueRepository.getMovieReviewList())
            Snackbar.make(
                constraintLayoutMainActivity,
                getString(R.string.not_network_connection),
                Snackbar.LENGTH_LONG
            ).show()
        }
        */
    }

    override fun onItemClicked(vegetableDataType: VegetableDataType) {
        selectedVegetable = vegetableDataType
        textViewSelectedVegetable.text = vegetableDataType.name.toString()
        Toast.makeText(applicationContext, "Item Clicked", Toast.LENGTH_LONG).show()
        Log.e("#ERROR008","eee"+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList())
        editTextAmount.text.clear()
        editTextPrice.text.clear()
    }




}