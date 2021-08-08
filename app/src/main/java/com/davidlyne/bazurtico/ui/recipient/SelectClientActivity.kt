package com.davidlyne.bazurtico.ui.recipient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.david.spanisleague.repository.VegetableRepository
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.ui.client.VegetableEvents
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_select_client.*

class SelectClientActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var vegetableListAdapter: VegetableListAdapter
    private lateinit var clientRepository: VegetableRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_client)
        setRecyclerViewSoccerLeagues("Spanish La Liga")
    }

    private fun setRecyclerViewSoccerLeagues(league: String) {
        vegetableListAdapter = VegetableListAdapter(this as VegetableEvents)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = vegetableListAdapter
        clientRepository = VegetableRepository(this)
        //soccerLeagueListAdapter.addAll(clientRepository.requestTeamReviewList("algo"))
        vegetableListAdapter.addAll(VegetableRepository(applicationContext).requestVegetableList("lo que sea"))
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

}