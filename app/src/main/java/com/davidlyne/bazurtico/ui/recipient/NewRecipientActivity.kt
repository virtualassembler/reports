package com.davidlyne.bazurtico.ui.recipient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.david.spanisleague.repository.ClientRepository
import com.david.spanisleague.repository.VegetableRepository
import com.davidlyne.bazurtico.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_new_recipient.*
import kotlinx.android.synthetic.main.activity_recipient.*

class NewRecipientActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var clientListAdapter: ClientListAdapter
    private lateinit var clientRepository: ClientRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipient)
        setRecyclerViewSoccerLeagues("Spanish La Liga")
    }

    private fun setRecyclerViewSoccerLeagues(league: String) {
        clientListAdapter = ClientListAdapter()
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewClient.layoutManager = gridLayoutManager
        recyclerViewClient.adapter = clientListAdapter
        clientRepository = ClientRepository(this)
        //soccerLeagueListAdapter.addAll(clientRepository.requestTeamReviewList("algo"))
        clientListAdapter.addAll(ClientRepository(applicationContext).requestClientList())
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
}