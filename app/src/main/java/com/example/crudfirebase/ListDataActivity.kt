package com.example.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudfirebase.Adapter.BiodataAdapter
import com.example.crudfirebase.Model.KeyRequest
import com.example.crudfirebase.Model.Request
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_data.*

class ListDataActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    lateinit var listReq: MutableList<Request>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data)

        database = FirebaseDatabase.getInstance().reference
        listReq = mutableListOf()


        rv_list_data.layoutManager = LinearLayoutManager(this)
        rv_list_data.itemAnimator = DefaultItemAnimator()



        database.child("Request").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("_database", " -> ${p0.details} ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listReq.clear()
                for (dataSnapshot: DataSnapshot in p0.children) {

                    var request: Request = dataSnapshot.getValue(Request::class.java)!!
                    listReq.add(request)

                }
                pg_loading_rv.visibility = View.GONE
                val adapter = BiodataAdapter(listReq,this@ListDataActivity)
                adapter.notifyDataSetChanged()
                rv_list_data.adapter = adapter
            }

        })

        fab_insert.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", "")
            intent.putExtra("name", "")
            intent.putExtra("email", "")
            intent.putExtra("desk", "")
            startActivity(intent)

        }

    }
}