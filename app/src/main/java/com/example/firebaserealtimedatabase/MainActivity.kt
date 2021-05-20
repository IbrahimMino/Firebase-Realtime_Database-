package com.example.firebaserealtimedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.firebaserealtimedatabase.model.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    private var count_id:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Firebase"

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                count_id = snapshot.childrenCount
            }
        })

        btnOk.setOnClickListener {
            if (edtName.text.isNotEmpty() && edtPass.text.isNotEmpty()) {

                //Takrorlanmaydigan keyni ozi oladi
                //val key = databaseReference.push().key
                //databaseReference.child(key!!).setValue(user)

                //Custom takrorlanmaydigan holatda key yasaymiz
                   databaseReference.addValueEventListener(object : ValueEventListener{
                       override fun onCancelled(error: DatabaseError) {

                       }

                       override fun onDataChange(snapshot: DataSnapshot) {
                           if (snapshot.exists()){
                               count_id = snapshot.childrenCount
                           }
                       }
                   })

                val user = User(edtName.text.toString(),edtPass.text.toString())
                databaseReference.child("User ${++count_id}").setValue(user)

                edtName.text.clear()
                edtPass.text.clear()
                Toast.makeText(applicationContext, "User save", Toast.LENGTH_SHORT).show()
            }
        }


        btnLoad.setOnClickListener {
            startActivity(Intent(applicationContext,LoadActivity::class.java))
        }
    }
}