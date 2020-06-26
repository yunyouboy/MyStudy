package cn.readsense.kotlinactualcombat.modules.collect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.readsense.kotlinactualcombat.R

class CollectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_collect, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }
}