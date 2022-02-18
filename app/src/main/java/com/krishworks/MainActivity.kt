package com.krishworks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.krishworks.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        queue = Volley.newRequestQueue(this)
        binding.button.setOnClickListener(this)
        setContentView(binding.root)
    }

    override fun onClick(v: View?) {
        if(v==binding.button){
            trigger()
        }
    }

    fun trigger(){
        val URL = "https://script.google.com/macros/s/AKfycbzywFnRh7sU2LYFVOFlOxX1htSxFvoX6JTqhD-TTvhuL3Oz0OAbapu-kWKGCRmfFWnMLA/exec"
        val request = StringRequest(Request.Method.GET, URL,
            { response ->
                val obj = JSONObject(response)
                Toast.makeText(this@MainActivity, ""+obj.get("result"), Toast.LENGTH_LONG).show()
            }
        ) { error -> Log.e("error", error.toString()) }
        queue.add(request)
    }
}