package com.ajieno.githubuser.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.util.ListUserAdapter.ListViewHolder
import org.w3c.dom.Text

class ListUserAdapter(private  val listuser: ArrayList<User>) : RecyclerView.Adapter<ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtUsername: TextView = itemView.findViewById(R.id.txt_username)
        var txtName: TextView = itemView.findViewById(R.id.txt_name)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listuser [position]

        holder.txtUsername.text = user.username
        holder.txtName.text = user.name

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
    }

    override fun getItemCount(): Int {
        return  listuser.size
    }
}