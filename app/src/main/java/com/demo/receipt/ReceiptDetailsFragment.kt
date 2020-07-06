package com.demo.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.receipt.databinding.ReceiptDetailsFragmentBinding

class ReceiptDetailsFragment : Fragment() {

    private lateinit var viewModel: ReceiptDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReceiptDetailsViewModel::class.java)
        viewModel.imageUri = arguments?.let { ReceiptDetailsFragmentArgs.fromBundle(it).imageUri }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ReceiptDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.receipt_details_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}