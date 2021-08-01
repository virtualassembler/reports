package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.ClientDao
import com.davidlyne.bazurtico.data.local.ClientDataClass
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TeamRepository Repository
 *
 * @author david.mazo
 */
class TeamRepository(private val context: Context) {

    //private val apiService = ApiRequest.instance
    private val movieDatabase: List<ClientDataClass> get() = TotalizerDatabase.getInstance(context)!!.getClientDAO().getClientList()

    private val clientList: ClientDao get() = TotalizerDatabase.getInstance(context)!!.getClientDAO()

    fun requestTeamReviewList(idTeam: String?): List<ClientDataClass> {
        return clientList.getClientList()
    }

    /*
    private fun insertTeamReviewListIntoDatabase(response: Response<TeamResponse>) {
        if (response.body() != null) {
            for (teamReview: TeamEvent in response.body()!!.events) {
                movieDatabase.insertTeamReview(teamReview)
            }
        }
    }

    fun getTeamReviewList(): List<TeamEvent> {
        return SoccerLeagueDatabase.getSoccerLeague(context).getTeamEventDAO().getTeamReviewList()
    }

    fun deleteTeamReviewList() {
        return SoccerLeagueDatabase.getSoccerLeague(context).getTeamEventDAO().deleteAllTeamEvents()
    }
    */
}
