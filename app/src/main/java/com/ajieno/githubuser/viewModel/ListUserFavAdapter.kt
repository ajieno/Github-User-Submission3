package com.ajieno.githubuser.viewModel

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.UserFavorite
import com.ajieno.githubuser.util.CustomOnItemClickListener
import com.ajieno.githubuser.view.DetailUserActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class ListUserFavAdapter(private val activity: Activity) : RecyclerView.Adapter<ListUserFavAdapter.UserViewHolder>() {

    var listUser = ArrayList<UserFavorite>()
        set(listUser) {
            if (listUser.size > 0) {
                this.listUser.clear()
            }
            this.listUser.addAll(listUser)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = this.listUser.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fav: UserFavorite) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fav.avatar)
                    .apply(RequestOptions().override(250, 250))
                    .into(itemView.img_avatar)
                txt_name.text = fav.name
                txt_company.text = fav.company
                txt_location.text = fav.location
                itemView.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent = Intent(activity, DetailUserActivity::class.java)
                                intent.putExtra(DetailUserActivity.Extra_fav, fav)
                                activity.startActivity(intent)
                            }
                        }
                    )
                )
            }
        }
    }
}