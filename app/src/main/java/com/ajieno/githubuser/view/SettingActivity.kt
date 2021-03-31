package com.ajieno.githubuser.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ajieno.githubuser.R
import com.ajieno.githubuser.util.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        const val SAVE_ALARM = "set alarm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.title = "Setting"

        alarmReceiver = AlarmReceiver()
        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE)

        setSwitch()

        sw_alarm.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
//                alarmReceiver.setRepeatingAlarm(
//                        this,AlarmReceiver.EXTRA_TYPE,
//                        "09:00",
//                        "Let's connect with another")
                sharedPreferences.edit().putBoolean("alarm",true).apply()
                Toast.makeText(this, "Alarm Activate", Toast.LENGTH_SHORT).show()
            }else{
                alarmReceiver.cancelAlarm(this)
                sharedPreferences.edit().putBoolean("alarm", false).apply()
                Toast.makeText(this, "Alarm Deactive", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setSwitch() {
        val value = sharedPreferences.getBoolean("alarm",true)
        if (value == true){
            sw_alarm.isChecked = true
        }
    }
}