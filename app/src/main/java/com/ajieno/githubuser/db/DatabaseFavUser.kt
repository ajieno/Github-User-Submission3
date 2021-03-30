package com.ajieno.githubuser.db


import android.net.Uri
import android.provider.BaseColumns

object DatabaseFavUser {

    const val AUTHORITY = "com.ajieno.githubuser"
    const val SCHEME = "content"

    class UserFavColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "favuser"
            val USERNAME = "username"
            val NAME = "name"
            val AVATAR = "avatar"
            val COMPANY = "company"
            val LOCATION = "location"
            val REPOSITORY = "repository"
            val FOLLOWERS = "followers"
            val FOLLOWING = "following"
            val FAVORITE = "favstat"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()

        }
    }
}