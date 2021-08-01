package com.davidlyne.bazurtico.ui.client

import com.davidlyne.bazurtico.data.local.ClientDataType


/**
 * SoccerLeagueEvents
 *
 * This interface is implemented in activities to manage recyclerView items onItemClick event
 *
 * @author david.mazo
 */
interface ClientEvents {
    fun onItemClicked(clientDataType: ClientDataType)
}
