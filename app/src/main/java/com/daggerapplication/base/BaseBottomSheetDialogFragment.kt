package com.daggerapplication.base

import android.content.Context
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.daggerapplication.utility.CustomProgress
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment<VM : ViewModel, DB : ViewDataBinding> :
    BottomSheetDialogFragment(), View.OnClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewDataBinding: DB

    private lateinit var mViewModel: VM
    private lateinit var navigatorViewModel: ViewModel
    lateinit var mProgressDialog: CustomProgress
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
    abstract fun performBinding(viewModel: VM, dataBinding: DB)

    fun getViewModel(): VM {
        return mViewModel
    }

    fun getDataBinding(): DB {
        return mViewDataBinding
    }

    fun getNavigationViewModel(): ViewModel {
        return navigatorViewModel
    }

    private fun initilizeViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    private fun initializeViewModel() {

        mViewModel = if(viewModelActivityScope()) {
            Log.d("BaseViewModelScope","Activity")
            ViewModelProviders.of(requireActivity(), viewModelFactory).get(getViewModelClass())
        } else {
            Log.d("BaseViewModelScope","Fragment")
            ViewModelProvider(ViewModelStores.of(this), viewModelFactory).get(getViewModelClass())
        }
    }
    abstract   fun viewModelActivityScope():Boolean
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
        getIntentValues()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * container return null, Remove validation on container
         */
        initilizeCustomProgress()
        initilizeViewBinding(inflater, container)
        performBinding(mViewModel, mViewDataBinding)
        return mViewDataBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageClickListener()
        setValues()

    }

    /**
     * set value on view
     */
    open fun setValues() {
        //
    }

    /**
     *
     */
    abstract fun registerClickListener(): Array<View>?

    /**
     * Get Intent Values
     */
    open fun getIntentValues() {
        //
    }

    override fun onClick(v: View?) {
        //
    }

    private fun manageClickListener() {
        val viewList = registerClickListener()
        viewList?.let { list: Array<View> ->
            if (list.isNotEmpty()) {
               // ClickUtils.setClickListener(list, this)
            }
        }
    }

    private fun initilizeCustomProgress() {
        mProgressDialog = CustomProgress(requireContext())
    }

}