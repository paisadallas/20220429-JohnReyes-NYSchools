package com.john.a20220526_johnreyes_nyschools.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.a20220526_johnreyes_nyschools.adapter.SchoolAdapter
import com.john.a20220526_johnreyes_nyschools.databinding.FragmentListBinding
import com.john.a20220526_johnreyes_nyschools.model.School
import com.john.a20220526_johnreyes_nyschools.utils.SchoolState


class ListFragment : BaseFragment() {

    private val schoolAdapter by lazy {
        SchoolAdapter(){

            var data = it.dbn

            val intention = ListFragmentDirections.actionListFragmentToSchoolFragment(data)

            findNavController().navigate(intention)
        }
    }

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.rvSchool.apply {
            layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = schoolAdapter
        }


            schoolViewModel.getSchool()


        Log.d("SeeViewModel", "viewModel: ${schoolViewModel} ")
        schoolViewModel.school.observe(viewLifecycleOwner){
            when(it){
                SchoolState.LOADING ->{
                    Toast.makeText(activity, "Loading...", Toast.LENGTH_SHORT)
                    .show()
                }
                is SchoolState.SUCCESS<*>->{
                    val schools = it?.school as School
                    schools.let {
                        schoolAdapter.update(it)
                    }
                }
                is SchoolState.ERROR ->{
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        return binding.root
    }

}