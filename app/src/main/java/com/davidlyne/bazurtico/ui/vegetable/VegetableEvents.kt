package com.davidlyne.bazurtico.ui.vegetable

import com.davidlyne.bazurtico.data.local.VegetableDataType


/**
 * SoccerLeagueEvents
 *
 * This interface is implemented in activities to manage recyclerView items onItemClick event
 *
 * @author david.lyne
 */
interface VegetableEvents {
    fun onItemClicked(vegetableDataType: VegetableDataType)
}
