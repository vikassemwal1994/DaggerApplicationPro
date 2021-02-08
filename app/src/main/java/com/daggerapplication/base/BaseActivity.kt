package com.daggerapplication.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.daggerapplication.di.scope.ForegroundBackgroundListener
import com.daggerapplication.utility.NetworkStatus
import com.daggerapplication.utility.SessionMgmt
import com.daggerapplication.utility.Utility
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@Suppress("unused")
abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity(),
    View.OnClickListener {

    var sessionMngmnt: SessionMgmt? = null
    var networkStatus: NetworkStatus? = null
    override fun attachBaseContext(base: Context) {
        /*val session = SessionMgmt(base)
        if (session.keyLanguage != "") {
            if (session.keyLanguage == "2") {
                super.attachBaseContext(LocaleHelper.onAttach(base, "es"))
            } else if (session.keyLanguage == "1") {
                super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
            }
        } else {
            super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
        }*/
//        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewDataBinding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }
    /*private val navigatorViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NavigatorViewModel::class.java)
    }*/

    private val mActionbar by lazy {
        supportActionBar
    }

    /**
     * DataBinding Instance
     */
    fun getViewDataBinding(): DB {
        return mViewDataBinding
    }

    /**
     *ViewModel Instance
     */

    fun getViewModel(): ViewModel {
        return mViewModel
    }

    /**
     * navigationViewModel Instance
     */

    /*fun getNavigationViewModel(): ViewModel {
        return navigatorViewModel
    }*/

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
    abstract fun performBinding(viewModel: VM)

    /**
     * set value on view
     */
    open fun setValues() {
        //
    }

    /**
     * Get Intent Values
     */
    open fun getIntentValues() {
        //
    }

    /**
     * Toolbar
     */
    abstract fun getToolbar(): Toolbar?

    /**
     *
     */
    abstract fun registerClickListener(): Array<View>?

    /**
     *
     */
    fun getActionbarInstance(): ActionBar? {
        return mActionbar
    }

    private val appObserver: ForegroundBackgroundListener by lazy {
        ForegroundBackgroundListener()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)
//        sessionMngmnt = SessionMgmt(this)
        networkStatus = NetworkStatus()
        performBinding(mViewModel)
        // ProcessLifecycleOwner.get().lifecycle.addObserver(appObserver)
        // appObserver.startSomething(this)
        setUp()

    }

    open fun onArrowPress() {
        Utility.hideKeyboard(this)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // onArrowPress()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * set Toolbar
     */
    private fun setToolbar() {
        getToolbar()?.let {
            // setSupportActionBar(it)
        }
        mActionbar?.setDisplayShowTitleEnabled(false)

    }

    private fun setUp() {
        getIntentValues()
        setToolbar()
        setValues()
        val viewList = registerClickListener()
        viewList?.let { list: Array<View> ->
            if (list.isNotEmpty()) {
                //ClickUtils.setClickListener(list, this)
            }
        }

    }
}