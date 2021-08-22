package com.davidlyne.bazurtico.ui.recipient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.BillDataType
import com.davidlyne.bazurtico.data.local.ClientDataType
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.repository.ClientRepository
import com.davidlyne.bazurtico.ui.client.ClientEvents
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_select_client.*
import java.util.*

class SelectClientActivity : ClientEvents, AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var clientListAdapter: ClientListAdapter
    private lateinit var clientRepository: ClientRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_client)
        setRecyclerViewSoccerLeagues()
    }

    private fun setRecyclerViewSoccerLeagues() {
        clientListAdapter = ClientListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = clientListAdapter
        clientRepository = ClientRepository(this)
        //soccerLeagueListAdapter.addAll(clientRepository.requestTeamReviewList("algo"))
        clientListAdapter.addAll(ClientRepository(applicationContext).requestClientList())
        Snackbar.make(
            constraintLayoutMainActivity,
            "listado de clientes",
            Snackbar.LENGTH_LONG
        ).show()
    }

    //    fun convertDateToLong(): Date {
    //
    //
    //        //var date = LocalDate.parse("2018-12-12")
    //        var date =
    //
    //        return date;
    //    }

    override fun onItemClicked(clientDataType: ClientDataType) {
        val intent = Intent(this, SelectVegetableActivity::class.java)
        Log.e("error001", "" + clientDataType.id)
        var billId = TotalizerDatabase.getInstance(this)!!.getBillDAO().getLastBillId()
        Log.e("IDde la ultima bill", "" + billId)
        var bill: BillDataType = BillDataType(clientDataType.id,2,getYear(),getMonth(),getDay(),getDayName(),System.currentTimeMillis(),System.currentTimeMillis())
        TotalizerDatabase.getInstance(this)!!.getBillDAO().insertBill(bill)
        Log.e("hhhhh", "" + TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList())
        startActivity(intent)
    }

    fun getYear(): Int {
        val year = android.text.format.DateFormat.format("yyyy", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getMonth(): Int {
        val year = android.text.format.DateFormat.format("MM", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getDay(): Int {
        val year = android.text.format.DateFormat.format("dd", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getDayName(): String {
        return android.text.format.DateFormat.format("EEEE", Date()).toString()
    }
}