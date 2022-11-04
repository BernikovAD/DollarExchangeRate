package com.example.dollarexchangerate.view

import android.app.*
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.dollarexchangerate.MyApplication
import com.example.dollarexchangerate.R
import com.example.dollarexchangerate.domain.repository.RepositoryDataBase
import com.example.dollarexchangerate.domain.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    var myPendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null
    var myBroadcastReceiver: BroadcastReceiver? = null

    @Inject
    lateinit var repositoryDataBase: RepositoryDataBase

    @Inject
    lateinit var repositoryImpl: RepositoryImpl

    companion object {
        const val CHANNEL_ID = "dollarexchangerate"
        const val NOTIFICATION_ID = 101
        val CHANNEL_NAME = "dollarexchangerate"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                DollarExchangeRateFragment.newInstance()
            ).commit()
        }


        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        registerMyAlarmBroadcast()
        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            86400000,
            myPendingIntent
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun registerMyAlarmBroadcast() {
        createNotificationChannel()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                //Получить данные за сегодняшний день, сравнить
                //если курс изменился, отправить пуш
                //sendPush()
            }
        }
        registerReceiver(myBroadcastReceiver, IntentFilter("com.example.dollarexchangerate"))
        myPendingIntent =
            PendingIntent.getBroadcast(this, 0, Intent("com.example.dollarexchangerate"), 0)
        alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager
    }


    fun sendPush() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notif = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Курс поменялся!")
            .setContentText("Доллар по отношению к рублю изменился, зайдите в приложении чтобы увидеть его стоимость.")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notifManger = NotificationManagerCompat.from(this)
        notifManger.notify(NOTIFICATION_ID, notif)
    }

    override fun onDestroy() {
        unregisterReceiver(myBroadcastReceiver)
        super.onDestroy()
    }
}