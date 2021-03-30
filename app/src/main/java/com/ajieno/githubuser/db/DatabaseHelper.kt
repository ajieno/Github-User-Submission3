package com.ajieno.githubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "UserFav.db"
        val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES = "CREATE TABLE ${DatabaseFavUser.UserFavColumns.TABLE_NAME}" +
                " (${DatabaseFavUser.UserFavColumns.USERNAME} TEXT PRIMARY KEY  ," +
                " ${DatabaseFavUser.UserFavColumns.NAME} TEXT," +
                " ${DatabaseFavUser.UserFavColumns.AVATAR} TEXT," +
                " ${DatabaseFavUser.UserFavColumns.COMPANY} TEXT," +
                " ${DatabaseFavUser.UserFavColumns.LOCATION} TEXT," +
                " ${DatabaseFavUser.UserFavColumns.REPOSITORY} INTEGER," +
                " ${DatabaseFavUser.UserFavColumns.FOLLOWERS} INTEGER," +
                " ${DatabaseFavUser.UserFavColumns.FOLLOWING} INTEGER," +
                " ${DatabaseFavUser.UserFavColumns.FAVORITE} TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DatabaseFavUser.UserFavColumns}.TABLE_NAME"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}