package com.davidlyne.bazurtico.ui.recipient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        clearDatabase()
    }

    private fun setRecyclerViewSoccerLeagues() {
        clientListAdapter = ClientListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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

    override fun onItemClicked(clientDataType: ClientDataType) {
        val intent = Intent(this, SelectVegetableActivity::class.java)
        Log.e("error001",""+clientDataType.id)
        var billId = TotalizerDatabase.getInstance(this)!!.getBillDAO().getLastBillId()
        Log.e("IDde la ultima bill",""+billId)
        var bill: BillDataType = BillDataType(clientDataType.id,2,System.currentTimeMillis(),System.currentTimeMillis())
        TotalizerDatabase.getInstance(this)!!.getBillDAO().insertBill(bill)
        startActivity(intent)
    }

    public fun clearDatabase(){
        Log.e("CLEAR","CLEAR DATABASE")
        Log.e("UNSAVED BILLS","getUnsavedBillList.count() "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getUnsavedBillList().count())
        Log.e("SAVED BILLS","getSavedBillList.count() "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getSavedBillList().count())
        Log.e("TOTAL BILL","getBillList.count() "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList().count())
        Log.e("TOTAL BILL VEGETABLE","getBillVegetableList.count() "+TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().getBillVegetableList().count())
        Log.e("bills ","bbb "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList())
        Log.e("bills with id ","bbb "+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillListWithId())
        var idUnsavedBill = TotalizerDatabase.getInstance(this)!!.getBillDAO().getUnsavedBill()
        if(idUnsavedBill.id != null && idUnsavedBill.id > 0){
            Log.e("ERROR5","el Id devuelto es "+idUnsavedBill.id)
            Log.e("BILL_VEGETABLE_LIST","rrr "+TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().getBillVegetableList())
            TotalizerDatabase.getInstance(this)!!.getBillVegetableDAO().clearUnsavedBillVegetableList(idUnsavedBill.id)
            TotalizerDatabase.getInstance(this)!!.getBillDAO().clearUnsavedBillList(idUnsavedBill.id)
        }else{
            Log.e("ERROR5","el Id devuelto es null")
        }
    }
}