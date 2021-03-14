package com.ajieno.githubuser.viewModel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.view.DetailUserActivity
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.viewModel.ListUserAdapter.ListViewHolder

var userFilterList = ArrayList<User>()

class ListUserAdapter(private  val listuser: ArrayList<User>) : RecyclerView.Adapter<ListViewHolder>() {

    init {
        userFilterList = listuser
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

        holder.txtName.text = user.name
        holder.txtCompany.text = user.company
        holder.txtLocation.text = user.location
        holder.imgAvatar.setImageResource(user.avatar)


        val userExtra = User(
            user.username,
            user.name,
            user.avatar,
            user.location,
            user.company,
            user.repository,
            user.follower,
            user.following,
        )

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra("Extra", userExtra)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  listuser.size
    }
}