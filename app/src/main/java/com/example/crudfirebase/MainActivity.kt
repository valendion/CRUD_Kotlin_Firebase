package com.example.crudfirebase

import android.media.MediaDrm
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.crudfirebase.Model.KeyRequest
import com.example.crudfirebase.Model.Request
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dataBase: DatabaseReference? = null
    var id: String? = ""
    var name: String? = ""
    var email: String? = ""
    var desk: String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBase = FirebaseDatabase.getInstance().getReference("Request")
        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")
        desk = intent.getStringExtra("desk")



        et_name.setText(name)
        et_email.setText(email)
        et_desk.setText(desk)

        if (id.isNullOrEmpty()) {
            btn_save.setText("SAVE")
            btn_cancel.setText("CANCEL")
        } else {
            btn_save.setText("EDIT")
            btn_cancel.setText("DELETE")
        }

        btn_save.setOnClickListener {
            var et_Name = et_name.text.toString()
            var et_Email = et_email.text.toString()
            var et_Desk = et_desk.text.toString()
            var key = dataBase!!.push().key.toString()

            if (btn_save.text.equals("SAVE")) {
                if (et_Name.isEmpty()) {
                    et_name.setError("Your name is empty")
                    et_name.requestFocus()
                } else if (et_Email.isEmpty()) {
                    et_email.setError("Your email is empty")
                    et_email.requestFocus()
                } else if (et_Desk.isEmpty()) {
                    et_desk.setError("Your desk is empty")
                    et_desk.requestFocus()
                } else {
                    pg_loading.visibility = View.VISIBLE
                    Log.e("_key", key)
                    submitter(
                        req = Request(
                            et_Name,
                            et_Email,
                            et_Desk,
                            key
                        ),key = key
                    )
                    finish()
                }

            } else {
//                Edit
                if (et_Name.isEmpty()) {
                    et_name.setError("Your name is empty")
                    et_name.requestFocus()
                } else if (et_Email.isEmpty()) {
                    et_email.setError("Your email is empty")
                    et_email.requestFocus()
                } else if (et_Desk.isEmpty()) {
                    et_desk.setError("Your desk is empty")
                    et_desk.requestFocus()
                } else {
                    pg_loading.visibility = View.VISIBLE
                    edit(
                        req = Request(
                            et_Name,
                            et_Email,
                            et_Desk,
                            id!!
                        ),
                        id = id!!
                    )
                    finish()
                }
            }

        }

        btn_cancel.setOnClickListener {
            if (btn_cancel.text.equals("CANCEL")) {
                finish()
            } else {
                dataBase!!
                    .child(id!!)
                    .removeValue()
                    .addOnSuccessListener {
                        pg_loading.visibility = View.GONE
                        finish()
                        Toast.makeText(this, "Data berhasil di input", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }

    fun submitter(req: Request,key: String) {

        dataBase!!
            .child(key)
            .setValue(req)
            .addOnSuccessListener {
                pg_loading.visibility = View.GONE
                et_name.setText("")
                et_email.setText("")
                et_desk.setText("")

                Toast.makeText(this, "Data berhasil di input", Toast.LENGTH_SHORT).show()
            }

    }

    fun edit(req: Request, id: String) {
        dataBase!!
            .child(id)
            .setValue(req)
            .addOnSuccessListener {
                pg_loading.visibility = View.GONE
                et_name.setText("")
                et_email.setText("")
                et_desk.setText("")

                Toast.makeText(this, "Data di edit", Toast.LENGTH_SHORT).show()
            }
    }
}