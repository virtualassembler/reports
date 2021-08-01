package com.davidlyne.bazurtico.ui.recipient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidlyne.bazurtico.R
import com.davidlyne.bazurtico.data.local.VegetableDataType
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * SoccerLeagueListAdapter
 *
 * Provides access to the SoccerLeague data items, makes a View for each SoccerLeague item
 *
 * @author david.mazo
 */
class VegetableListAdapter() : RecyclerView.Adapter<VegetableListAdapter.ViewHolder>() {

    private var listVegetable: List<VegetableDataType> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listVegetable.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listVegetable[position])
    }

    fun addAll(listVegetable: List<VegetableDataType>) {
        this.listVegetable = listVegetable
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(soccerLeague: VegetableDataType) {
            itemView.textViewName.text = soccerLeague.name
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
