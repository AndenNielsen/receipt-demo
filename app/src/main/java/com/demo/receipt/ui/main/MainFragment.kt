package com.demo.receipt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.demo.receipt.R
import com.demo.receipt.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var adapter: ReceiptsListAdapter
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.main_fragment, container, false
            )
        binding.viewModel = viewModel
        adapter = ReceiptsListAdapter()
        binding.receiptList.adapter = adapter

        viewModel.getReceipts().observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addReceiptButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_receiptDetailsFragment)
        }
    }

}