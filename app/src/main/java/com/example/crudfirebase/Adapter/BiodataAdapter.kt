package com.example.crudfirebase.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirebase.MainActivity
import com.example.crudfirebase.Model.KeyRequest
import com.example.crudfirebase.Model.Request
import com.example.crudfirebase.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_data.view.*

class BiodataAdapter(
    var list: MutableList<Request>,
    var activity: Activity
): RecyclerView.Adapter<BiodataAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var et_name_bio:TextView = view.tv_name
        var et_email_bio:TextView = view.tv_email
        var item:ConstraintLayout = view.cl_item

        @SuppressLint("SetTextI18n")
        fun bio(req: Request){
            et_name_bio.text = "Nama : ${req.name}"
            et_email_bio.text = "Email : ${req.email}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.list_data, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bio(list[position])
        holder.item.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("id", list[position].key)
            intent.putExtra("name", list[position].name)
            intent.putExtra("email", list[position].email)
            intent.putExtra("desk", list[position].desk)
            activity.startActivity(intent)
        }
    }

}