package com.ajieno.githubuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val Extra = "Extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val user : User = intent.getParcelableExtra(Extra)

        txt_detail_name.setText(user.name)
        txt_username.setText(user.username)
        txt_detail_company.setText("Company : "+user.company)
        txt_detail_location.setText("Location : "+user.location)
        txt_detail_repository.setText("Repository : "+user.repository)
        txt_detail_follower.setText("Follower : "+user.follower)
        txt_following.setText("Following : "+user.following)

        Glide.with(this)
            .load(user.avatar)
            .into(img_detail_avatar)

        viewPagerConfig()


    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = ViewPagerDetailAdapter(this, supportFragmentManager)
    }
}