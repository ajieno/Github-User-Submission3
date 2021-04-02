package com.ajieno.githubuser.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

var filterListFollowing = ArrayList<User>()

class ListFollowingAdapter(private  val listfolowwingg: ArrayList<User>) : RecyclerView.Adapter<ListFollowingAdapter.ListViewHolder>() {

    init {
        filterListFollowing = listfolowwingg
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_name_txt)
        var company: TextView = itemView.findViewById(R.id.item_company_txt)
        var location: TextView = itemView.findViewById(R.id.item_location_txt)
        var avatar: ImageView = itemView.findViewById(R.id.item_img_avatar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val userdata = listfolowwingg [position]
        
        holder.name.text = userdata.name
        holder.company.text = userdata.company
        holder.location.text = userdata.location
        Picasso.get().load(userdata.avatar).into(holder.avatar)

    }

    override fun getItemCount(): Int {
        return  listfolowwingg.size
    }

}