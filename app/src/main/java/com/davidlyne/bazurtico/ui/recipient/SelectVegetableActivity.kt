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
import com.davidlyne.bazurtico.data.local.VegetableDataType
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vegetable)
        setRecyclerViewSoccerLeagues()
        // Filling bill headers
        val bill : BillDataType = TotalizerDatabase.getInstance(this)!!.getBillDAO().getCurrentBill()
        val client: ClientDataType = TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientDetail(bill.clientId)
        textViewClientName.text = "CLIENTE: "+client.nameClient.toString()
        textViewconsecutive.text = "CONSECUTIVO: "+bill.id.toString()
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
        //elelmento guardado en database
        //
        Log.e("#ERROR008","eee"+TotalizerDatabase.getInstance(this)!!.getBillDAO().getBillList())
    }




}