package com.example.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.model.User
import com.example.firebaserealtimedatabase.rvadapter.RvAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_load.*

class LoadActivity : AppCompatActivity() {

    private lateinit var rvAdapter: RvAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var listUser:MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        title = "Users"


        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listUser = ArrayList()
              val children = snapshot.children
                for (child in children){
                    val user = child.getValue(User::class.java)
                    if (user!=null){
                        listUser.add(user)
                    }
                }

                rvAdapter = RvAdapter(listUser)
                recyclerview.layoutManager = LinearLayoutManager(this@LoadActivity)
                recyclerview.adapter = rvAdapter

            }
        })


    }
}