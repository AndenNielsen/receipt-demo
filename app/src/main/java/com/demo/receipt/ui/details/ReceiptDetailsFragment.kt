package com.demo.receipt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.demo.receipt.R
import com.demo.receipt.databinding.ReceiptDetailsFragmentBinding
import com.demo.receipt.getPhotoURI
import kotlinx.android.synthetic.main.receipt_details_fragment.*
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setOnClickListener {
            getPhotoURI(requireContext())?.let {
                viewModel.imageUri.set(it)
                takePicture.launch(it)
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