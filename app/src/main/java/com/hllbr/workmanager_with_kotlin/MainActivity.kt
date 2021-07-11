package com.hllbr.workmanager_with_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = Data.Builder().putInt("intKey",1).build()
        /*
        Telefon sadece şarj durumunda olduğunda sadece internet açıkken gibi koşullar belirleyerek istediğimiz koşullarda bu constraitlerimizi çalıştırabiliriz.

         */
    val constraints = Constraints.Builder()
        //.setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(false)
        .build()

        //OneTimeWorkRequestBuilder<>()//bir defa çalıştırılan workrequestimiz
        val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5,TimeUnit.MINUTES)
            //.addTag("myTag")
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)



    }
}