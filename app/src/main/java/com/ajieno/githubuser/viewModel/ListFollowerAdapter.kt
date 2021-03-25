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

var followerFilterList = ArrayList<User>()

class ListFollowerAdapter(private  val listuser: ArrayList<User>) : RecyclerView.Adapter<ListFollowerAdapter.ListViewHolder>() {

    init {
        followerFilterList = listuser
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView = itemView.findViewById(R.id.txt_name)
        var txtCompany: TextView = itemView.findViewById(R.id.txt_company)
        var txtLocation: TextView = itemView.findViewById(R.id.txt_location)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listuser [position]

        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .into(holder.imgAvatar)
        holder.txtName.text = user.name
        holder.txtCompany.text = user.company
        holder.txtLocation.text = user.location

    }

    override fun getItemCount(): Int {
        return  listuser.size
    }

}