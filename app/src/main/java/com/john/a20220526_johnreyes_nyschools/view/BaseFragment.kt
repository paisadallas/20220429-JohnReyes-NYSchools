package com.john.a20220526_johnreyes_nyschools.view

import androidx.fragment.app.Fragment
import com.john.a20220526_johnreyes_nyschools.viewmodel.SchoolViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment:Fragment() {

    protected val schoolViewModel: SchoolViewModel by sharedViewModel()

}