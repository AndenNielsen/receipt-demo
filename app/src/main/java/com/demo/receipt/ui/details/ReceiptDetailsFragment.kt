package com.demo.receipt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.demo.receipt.R
import com.demo.receipt.databinding.ReceiptDetailsFragmentBinding
import kotlinx.android.synthetic.main.receipt_details_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

        viewModel.saveToDb.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setOnClickListener {
            lifecycleScope.launch {
                viewModel.photoPath.collect {
                    it?.let {
                        viewModel.imageUri.set(it)
                        takePicture.launch(it)
                    }
                }
            }
        }
    }

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            success?.let {
                // notifyChange to let image view update with new content of URI
                viewModel.imageUri.notifyChange()
            }
        }
}