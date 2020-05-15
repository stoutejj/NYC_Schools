package com.example.codingchallengenycschools.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallengenycschools.R
import com.example.codingchallengenycschools.model.data.School

class SchoolAdapter(private val schoolInfoClickListener: SchoolInfoClickListener) :
    RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    var dataSet = emptyList<School>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SchoolViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.school_item_layout,
                parent,
                false
            )
    )

    fun setSchools(schools: List<School>) {
        dataSet = schools
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.onBind(dataSet[position], schoolInfoClickListener)
    }

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val schoolName: TextView = itemView.findViewById(R.id.tv_school_name)
        private val schoolAddr: TextView = itemView.findViewById(R.id.tv_school_addr)
        private val schoolPhone: Button = itemView.findViewById(R.id.btn_school_phone_number)
        private val schoolInfo: Button = itemView.findViewById(R.id.btn_school_info)

        fun onBind(school: School, schoolInfoClickListener: SchoolInfoClickListener) {
            schoolName.text = school.school_name
            schoolAddr.text = school.location

            //When the SCHOOL INFO BUTTON is clicked
            schoolInfo.setOnClickListener {
                schoolInfoClickListener.onClick(school)
            }

            //When the PHONE NUMBER BUTTON is clicked redirects to the dialer
            schoolPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:${school.phone_number}")
                )
                itemView.context.startActivity(intent)
            }
        }
    }
}

interface SchoolInfoClickListener {
    fun onClick(school: School)
}