package com.davidlyne.bazurtico.repository

import android.content.Context
import com.davidlyne.bazurtico.data.local.ClientDataType
import com.davidlyne.bazurtico.data.local.TotalizerDatabase

/**
 * SoccerLeagueRepository class
 *
 * @author david.lyne
 */
class ClientRepository(private val context: Context) {

    private val clientList: List<ClientDataType> get() = TotalizerDatabase.getInstance(context)!!.getClientDAO().getClientList()

    fun requestClientList(): List<ClientDataType> {
        return clientList
    }

    /*

    private val apiService = ApiRequest.instance
    private val soccerLeagueDatabase: SoccerLeagueDao get() = SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO()

    fun requestMovieReviewList(league: String): List<SoccerLeague> {
        apiService.getMovieReviewListFromInternet(league).enqueue(object : Callback<SoccerLeagueResponse> {
            override fun onResponse(callSoccerLeagueResponse: Call<SoccerLeagueResponse>, response: Response<SoccerLeagueResponse>) {
                when (response.code()) {
                    200 -> {
                        insertMovieReviewListIntoDatabase(response)
                    }
                    else -> Log.e(context.getString(R.string.error_tag), context.getString(R.string.error_response_code_different_to_200))
                }
            }

            override fun onFailure(call: Call<SoccerLeagueResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.printStackTrace().toString())
            }
        })
        return getMovieReviewList()
    }

    private fun insertMovieReviewListIntoDatabase(response: Response<SoccerLeagueResponse>) {
        if (response.body() != null) {
            for (soccerLeague: SoccerLeague in response.body()!!.teams) {
                soccerLeagueDatabase.insertMovieReview(soccerLeague)
            }
        }
    }

    fun getMovieReviewList(): List<SoccerLeague> {
        return SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO().getMovieReviewList()
    }

    fun deleteMovieReviewList() {
        return SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO().deleteAllSoccerLeague()
    }
    */
}
