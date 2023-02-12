package com.example.myapplication

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class Mysingleton constructor(context: Context){
     companion object{
         @Volatile
         private var INSTANCE : Mysingleton?=null
         fun getInstance(context: Context) =
             INSTANCE ?: synchronized(this){
                 INSTANCE ?: Mysingleton(context).also {
                     INSTANCE = it
                 }
             }
     }

   private val requestqueue:RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    fun<T>addToRequestQueue(req:Request<T>){
        requestqueue.add(req)
    }
}