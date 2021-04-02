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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class ListUserFavAdapter(private val activity: Activity) : RecyclerView.Adapter<ListUserFavAdapter.UserViewHolder>() {
    var listUserFavorite = ArrayList<UserFavorite>()
        set(listUser) {
            if (listUser.size > 0) {
                this.listUserFavorite.clear()
            }
            this.listUserFavorite.addAll(listUser)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUserFavorite[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userdata: UserFavorite) {
            with(itemView) {
                item_name_txt.text = userdata.name
                item_company_txt.text = userdata.company
                item_location_txt.text = userdata.location
                Picasso.get().load(userdata.avatar).into(item_img_avatar);

                itemView.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent = Intent(activity, DetailUserActivity::class.java)
                                intent.putExtra(DetailUserActivity.USER_DATA_FAVORITE, userdata)
                                activity.startActivity(intent)
                            }
                        }
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = this.listUserFavorite.size
}