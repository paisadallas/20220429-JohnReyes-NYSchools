package com.john.a20220526_johnreyes_nyschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.a20220526_johnreyes_nyschools.databinding.SchoolItemBinding
import com.john.a20220526_johnreyes_nyschools.model.SchoolItem

class SchoolAdapter(
    private var schoolList:MutableList<SchoolItem> = mutableListOf(),
    private var openSchool:(SchoolItem) -> Unit
):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SchoolItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schoolList[position],openSchool)
    }

    override fun getItemCount(): Int = schoolList.size

    fun update(schools:List<SchoolItem>){
        schoolList.clear()
        schoolList.addAll(schools)
        notifyDataSetChanged()
    }
}

class ViewHolder(
    private val binding:SchoolItemBinding
):RecyclerView.ViewHolder(binding.root){

    fun bind(schoolItem:SchoolItem,openSchool: (SchoolItem) -> Unit){
        binding.tvSchoolName.text =  schoolItem.school_name
        binding.tvCity.text = "City:  ${schoolItem.city}"
        binding.tvZip.text = "Zip:  ${schoolItem.zip}"
        binding.tvPhoneNumber.text = "Phone:  ${schoolItem.phone_number}"
        itemView.setOnClickListener { openSchool(schoolItem) }
    }
}