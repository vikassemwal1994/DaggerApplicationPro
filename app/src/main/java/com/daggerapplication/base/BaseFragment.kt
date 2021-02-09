package com.daggerapplication.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : BaseCoreFragment<VM, DB>() {
    override fun viewModelActivityScope(): Boolean {
        return false
    }

}