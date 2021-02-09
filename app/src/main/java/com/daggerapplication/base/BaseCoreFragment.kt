package com.daggerapplication.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStores
import com.daggerapplication.utility.CustomProgress
import com.daggerapplication.utility.NetworkStatus
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseCoreFragment<VM : ViewModel, DB : ViewDataBinding>: DaggerFragment(),View.OnClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewDataBinding: DB

    private lateinit var mViewModel: VM
    private lateinit var navigatorViewModel: ViewModel
    lateinit var mProgressDialog: CustomProgress
    var networkStatus: NetworkStatus? = null
    /**
     * ViewModel class
     */
    abstract fun getViewModelClass(): Class<VM>

    /**
     * layoutId
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {
        //

    }
    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun performBinding(viewModel: VM,dataBinding: DB)

    fun getViewModel(): VM {
        return mViewModel
    }

    fun getDataBinding() : DB {
        return mViewDataBinding
    }

    fun getNavigationViewModel(): ViewModel {
        return navigatorViewModel
    }

    private fun initilizeViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    private fun initializeViewModel() {
      //  navigatorViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(NavigatorViewModel::class.java)
        mViewModel = if(viewModelActivityScope()) {
            Log.d("BaseViewModelScope","Activity")
            ViewModelProviders.of(requireActivity(), viewModelFactory).get(getViewModelClass())
        } else {
            Log.d("BaseViewModelScope","Fragment")
            ViewModelProvider(ViewModelStores.of(this), viewModelFactory).get(getViewModelClass())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onInject()
        initializeViewModel()
        getIntentValues()
        networkStatus = NetworkStatus()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("View Frag",view.toString())
        initilizeCustomProgress()
        initilizeViewBinding(inflater, container)
        performBinding(mViewModel,mViewDataBinding)
        return mViewDataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues()
        val viewList = registerClickListener()
        viewList?.let { list: Array<View> ->
            if (list.isNotEmpty()) {
              //  ClickUtils.setClickListener(list, this)
            }
        }
    }
    /**
     * set value on view
     */
    open fun setValues(){
        //
    }
    /**
     *
     */
    abstract fun  registerClickListener():Array<View>?
    /**
     * Get Intent Values
     */
    abstract   fun viewModelActivityScope():Boolean

    open fun getIntentValues(){
        //
    }



    override fun onClick(v: View?) {
        //
    }
    private fun initilizeCustomProgress()
    {
        mProgressDialog=CustomProgress(requireContext())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}