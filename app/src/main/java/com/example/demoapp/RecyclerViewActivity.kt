package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.network.RetroInstance
import com.example.demoapp.network.RetroService
import com.example.demoapp.viewmodel.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerviewadapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recycler.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerviewadapter = RecyclerViewAdapter()
            adapter = recyclerviewadapter
        }
    }

    fun createData(){
        /*val item=ArrayList<RecyclerData>()
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )
        item.add(RecyclerData("Purva","Brave") )

        recyclerviewadapter.setListData(item)
        recyclerviewadapter.notifyDataSetChanged()
print("********************************")
       val retroinstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
       val call = retroinstance.getDataFromAPI("atlanta")
     call.enqueue(object : retrofit2.Callback<RecyclerList>{
         override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
             if(response.isSuccessful){
                 recyclerviewadapter.setListData(response.body()?.items!!)
                 recyclerviewadapter.notifyDataSetChanged()
             }
         }

         override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
       print("error***************")
         }

     })*/

        val viewModel = ViewModelProviders.of(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{

            if(it != null) {
                recyclerviewadapter.setListData(it.items)
                recyclerviewadapter.notifyDataSetChanged()

            } else {
                Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }

        })
     viewModel.makeApiCall("atlanta")
    }
}