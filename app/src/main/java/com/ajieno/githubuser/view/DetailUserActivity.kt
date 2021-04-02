package com.ajieno.githubuser.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ajieno.githubuser.R
import com.ajieno.githubuser.db.DatabaseFavUser
import com.ajieno.githubuser.db.FavUserHelper
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.model.UserFavorite
import com.ajieno.githubuser.viewModel.ViewPagerDetailAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : AppCompatActivity() {
    private lateinit var user : User
    private lateinit var favUserHelper: FavUserHelper
    private var isfavorite = false
    private var userFavorite: UserFavorite? = null

    private lateinit var name : String
    private lateinit var username : String
    private lateinit var company : String
    private lateinit var location : String
    private var repository : Int? = 0
    private var follower : Int? = 0
    private var following : Int? = 0
    private lateinit var favorite : String
    private lateinit var avatar : String

    companion object{
        const val USER_DATA = "USER_DATA"
        const val USER_DATA_FAVORITE = "USER_DATA_FAVORITE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        supportActionBar?.hide()

        checkFavorite()

        btn_fav.setOnClickListener {
            if (isfavorite == true){
                deletfromFav()
            }else {
                addtoFav()
            }
        }

    }

    private fun checkFavorite() {
        favUserHelper = FavUserHelper.getInstance(applicationContext)
        favUserHelper.open()

        userFavorite = intent.getParcelableExtra(USER_DATA_FAVORITE)
        if (userFavorite != null){
            setDataFav()            
            isfavorite = true
            btn_fav.setImageResource(R.drawable.ic_round_favorite_24)
        }else{
            setData()
            val finduser = favUserHelper.queryById(user.username.toString())
            if (finduser.count == 1){
                btn_fav.setImageResource(R.drawable.ic_round_favorite_24)
                isfavorite = true}
        }

        txt_detail_name.setText(name)
        txt_username.setText(username)
        txt_detail_company.setText("Company : "+company)
        txt_detail_location.setText("Location : "+location)
        txt_detail_repository.setText("Repository : "+repository)
        txt_detail_follower.setText("Follower : "+follower)
        txt_following.setText("Following : "+following)

        Glide.with(this)
            .load(avatar)
            .into(img_detail_avatar)

        viewPagerConfig()

    }

    private fun setDataFav() {
        val fav = intent.getParcelableExtra(USER_DATA_FAVORITE)  as UserFavorite
        name = fav.name.toString()
        username = fav.username.toString()
        company = fav.company.toString()
        location = fav.location.toString()
        repository = fav.repository
        follower = fav.follower
        following = fav.following
        avatar = fav.avatar.toString()
    }

    private fun setData() {
        user = intent.getParcelableExtra(USER_DATA)
        name = user.name.toString()
        username = user.username.toString()
        company = user.company.toString()
        location = user.location.toString()
        repository = user.repository
        follower = user.follower
        following = user.following
        avatar = user.avatar.toString()
    }

    private fun deletfromFav() {
        favUserHelper = FavUserHelper.getInstance(applicationContext)
        favUserHelper.open()
        favUserHelper.deleteById(username)

        btn_fav.setImageResource(R.drawable.ic_round_favorite_border_24)
        isfavorite = false

    }

    private fun addtoFav() {
        val values = ContentValues()
        values.put(DatabaseFavUser.UserFavColumns.USERNAME, username)
        values.put(DatabaseFavUser.UserFavColumns.NAME, name)
        values.put(DatabaseFavUser.UserFavColumns.AVATAR, avatar)
        values.put(DatabaseFavUser.UserFavColumns.COMPANY, company)
        values.put(DatabaseFavUser.UserFavColumns.LOCATION, location)
        values.put(DatabaseFavUser.UserFavColumns.REPOSITORY, repository)
        values.put(DatabaseFavUser.UserFavColumns.FOLLOWERS,follower)
        values.put(DatabaseFavUser.UserFavColumns.FOLLOWING, following)
        values.put(DatabaseFavUser.UserFavColumns.FAVORITE, "1")

        favUserHelper = FavUserHelper.getInstance(applicationContext)
        favUserHelper.open()
        favUserHelper.insert(values)

        btn_fav.setImageResource(R.drawable.ic_round_favorite_24)

        isfavorite = true
    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = ViewPagerDetailAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerDetailAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }


}