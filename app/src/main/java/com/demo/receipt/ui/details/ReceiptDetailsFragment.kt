package com.demo.receipt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.demo.receipt.R
import com.demo.receipt.databinding.ReceiptDetailsFragmentBinding
import com.demo.receipt.hideSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptDetailsFragment : Fragment() {

    private val viewModel: ReceiptDetailsViewModel by viewModel()

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

        viewModel.inputComplete.observe(viewLifecycleOwner) {
            binding.dateInput.hideSoftKeyboard()
            findNavController().popBackStack()
        }
        viewModel.uriForPhoto.observe(viewLifecycleOwner) {
            takePicture.launch(it)
        }

        return binding.root
    }

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            success?.let {
                viewModel.processPhoto()
            }
        }
}