package com.davidlyne.bazurtico.ui.recipient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.ClientDataType
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * SoccerLeagueListAdapter
 *
 * Provides access to the SoccerLeague data items, makes a View for each SoccerLeague item
 *
 * @author david.mazo
 */
class ClientListAdapter() : RecyclerView.Adapter<ClientListAdapter.ViewHolder>() {

    private var listClient: List<ClientDataType> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listClient.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listClient[position])
    }

    fun addAll(listClient: List<ClientDataType>) {
        this.listClient = listClient
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(client: ClientDataType) {
            itemView.textViewName.text = client.nameClient
            //itemView.textViewStadium.text = soccerLeague.telClient
            /*
            Glide.with(itemView)
                    .load(soccerLeague.strTeamBadge)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.imageViewTeamBadge)
            view.setOnClickListener { listener.onItemClicked(soccerLeague) }
            */
        }
    }
}
