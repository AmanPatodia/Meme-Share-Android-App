package com.example.myapplication

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import javax.sql.DataSource


class MainActivity : AppCompatActivity() {
    var currentmemeurl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()
    }
    private fun loadmeme(){
        val progressbar=findViewById<ProgressBar>(R.id.process)
        progressbar.visibility=View.VISIBLE
        val imageview=findViewById<ImageView>(R.id.memeimageview)

        currentmemeurl="https://meme-api.herokuapp.com/gimme"


        val json=JsonObjectRequest(
             Request.Method.GET,currentmemeurl,null,
            {
            response ->
        currentmemeurl=response.getString("url")
        Glide.with(this).load(currentmemeurl).listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressbar.visibility=View.GONE
                return false
            }

            fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressbar.visibility=View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                TODO("Not yet implemented")
            }
        }).into(imageview)
    },
            {
               Toast.makeText(this,"Error occurs",Toast.LENGTH_SHORT).show()
            })
       Mysingleton.getInstance(this).addToRequestQueue(json)
    }

    fun sharememe(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey there is a coll meme i got from reedit $currentmemeurl")
        val chooser= Intent.createChooser(intent,"Share this meme using....")
        startActivity(chooser)
    }

    fun nextmeme(view: View) {
        loadmeme()
    }


}