package com.davidlyne.bazurtico.ui.vegetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDataType
import com.davidlyne.bazurtico.repository.ClientRepository
import kotlinx.android.synthetic.main.activity_vegetable_list.*

class VegetableListActivity : VegetableEvents, AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var vegetableListAdapter: VegetableListAdapter
    private lateinit var clientRepository: ClientRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegetable_list)
        setRecyclerViewSoccerLeagues()
    }

    private fun setRecyclerViewSoccerLeagues() {
        vegetableListAdapter = VegetableListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerViewVegetableList.layoutManager = gridLayoutManager
        recyclerViewVegetableList.adapter = vegetableListAdapter
        clientRepository = ClientRepository(this)
        vegetableListAdapter.addAll(TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableList())
        //Snackbar.make(constraintLayoutMainActivity,"listado de vegetales",Snackbar.LENGTH_LONG).show()
    }

//    override fun onItemClicked(clientDataType: ClientDataType) {
//        val intent = Intent(this, SelectVegetableActivity::class.java)
//        Log.e("error001", "" + clientDataType.id)
//        var billId = TotalizerDatabase.getInstance(this)!!.getBillDAO().getLastBillId()
//        Log.e("IDde la ultima bill", "" + billId)
//        var bill: BillDataType = BillDataType(clientDataType.id,2,
//            DateHelper().getYear(),
//            DateHelper().getMonth(),
//            DateHelper().getDay(),
//            DateHelper().getDayName(),System.currentTimeMillis(),System.currentTimeMillis())
//        TotalizerDatabase.getInstance(this)!!.getBillDAO().insertBill(bill)
//        Log.e("hhhhh", "" + TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList())
//        startActivity(intent)
//    }

    override fun onItemClicked(vegetableDataType: VegetableDataType) {
        val intent = Intent(this, EditVegetableActivity::class.java)
        intent.putExtra("vegetableId",""+vegetableDataType.id)
        startActivity(intent)
    }
}