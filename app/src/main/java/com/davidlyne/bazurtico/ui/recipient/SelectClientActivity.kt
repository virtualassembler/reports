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
        TotalizerDatabase.getInstance(this)!!.getBillDAO().clearBillList()
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

    override fun onItemClicked(clientDataType: ClientDataType) {
        val intent = Intent(this, SelectVegetableActivity::class.java)
        Log.e("error001",""+clientDataType.id)
        TotalizerDatabase.getInstance(this)!!.getBillDAO().insertBill(BillDataType(clientDataType.id,2,System.currentTimeMillis(),System.currentTimeMillis()))
        startActivity(intent)
    }

}