package com.davidlyne.bazurtico.repository

import android.content.Context
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDataType

/**
 * SoccerLeagueRepository class
 *
 * This Class returns the data to the presentation layout
 * a requestSoccerLeagueList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates Sqlite Room table and the UI
 *
 * @author david.mazo
 */
class VegetableRepository(private val context: Context) {

    private val vegetableList: List<VegetableDataType> get() = TotalizerDatabase.getInstance(context)!!.getVegetableDAO().getVegetableList()

    fun requestVegetableList(): List<VegetableDataType> {
        return vegetableList
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
