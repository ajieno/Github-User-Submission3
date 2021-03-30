package com.ajieno.githubuser.util

import android.database.Cursor
import com.ajieno.githubuser.db.DatabaseFavUser
import com.ajieno.githubuser.model.UserFavorite

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<UserFavorite> {
        val userfavlist = ArrayList<UserFavorite>()
        notesCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.USERNAME))
                val name = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.NAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.AVATAR))
                val company = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.COMPANY))
                val location = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.LOCATION))
                val repository = getInt(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.REPOSITORY))
                val followers = getInt(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.FOLLOWERS))
                val following = getInt(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.FOLLOWING))
                val favstat = getString(getColumnIndexOrThrow(DatabaseFavUser.UserFavColumns.FAVORITE))

                userfavlist.add(
                        UserFavorite(
                                username,
                                name,
                                avatar,
                                company,
                                location,
                                repository,
                                followers,
                                following,
                                favstat
                        )
                )
            }
        }
        return userfavlist
    }
}