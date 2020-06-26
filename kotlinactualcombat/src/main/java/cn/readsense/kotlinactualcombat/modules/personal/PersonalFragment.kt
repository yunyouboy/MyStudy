package cn.readsense.kotlinactualcombat.modules.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.readsense.kotlinactualcombat.R

class PersonalFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View? = inflater.inflate(R.layout.fragment_personal, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }
}