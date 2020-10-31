package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter(private val portals: List<Portal>, private val clickListener: (Portal) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class PortalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal: Portal, clickListener: (Portal) -> Unit) {
            itemView.tvPortalTitle.text = portal.portalTitle
            itemView.tvPortalUrl.text = portal.portalUrl
            itemView.setOnClickListener {
                clickListener(portal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalViewHolder {
        return PortalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PortalViewHolder).bind(portals[position], clickListener)
    }

    override fun getItemCount(): Int {
        return portals.size
    }
}