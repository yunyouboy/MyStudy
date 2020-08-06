package com.example.materialdesign.viewpager2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialdesign.coordinator.adapter.AuthorRecyclerAdapter
import com.example.materialdesign.coordinator.bean.AuthorInfo
import com.example.materialdesign.databinding.Fragment2Binding

class TwoFragment : Fragment() {
    private lateinit var binding: Fragment2Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Fragment2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = AuthorRecyclerAdapter(AuthorInfo.Companion.createTestData())
    }

    companion object {
        fun newIntance(): Fragment {
            return TwoFragment()
        }
    }
}