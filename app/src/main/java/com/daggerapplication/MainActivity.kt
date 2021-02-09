package com.daggerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.daggerapplication.base.BaseActivity
import com.daggerapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainActivityVM, ActivityMainBinding>() {
    private lateinit var mViewModel: MainActivityVM
    private lateinit var mainBinding: ActivityMainBinding

    override fun getViewModelClass(): Class<MainActivityVM> {
        return MainActivityVM::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun performBinding(viewModel: MainActivityVM) {
        mViewModel = viewModel
        mainBinding = getViewDataBinding()
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun registerClickListener(): Array<View>? {
        return null
    }

    override fun onClick(v: View?) {

    }

}