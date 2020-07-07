package com.demo.receipt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.demo.receipt.R
import com.demo.receipt.databinding.ReceiptDetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptDetailsFragment : Fragment() {

    private val viewModel: ReceiptDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.imageUri = arguments?.let {
            ReceiptDetailsFragmentArgs.fromBundle(
                it
            ).imageUri
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ReceiptDetailsFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.receipt_details_fragment, container, false
            )
        binding.viewModel = viewModel
        return binding.root
    }
}