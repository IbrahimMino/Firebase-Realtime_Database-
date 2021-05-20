package com.example.firebaserealtimedatabase.rvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.R

import com.example.firebaserealtimedatabase.model.User


class RvAdapter(val listUser:MutableList<User>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
         val name = itemview.findViewById<TextView>(R.id.rowName)
        val pass = itemview.findViewById<TextView>(R.id.rowPass)
        fun onBind(user: User){
            name.text = user.username
            pass.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listUser[position])
    }

}