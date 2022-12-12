package com.example.hellogarden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hellogarden.data.models.ProductArticle
import com.example.hellogarden.databinding.FragmentDetailProductBinding

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailProductBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = requireArguments().getInt("productId", 0)

        val productList: List<ProductArticle>? = viewModel.product.value

        val productArticle = productList?.find { it.id == productId }

        if (productArticle != null) {
            binding.detailImage.setImageResource(productArticle.imageResourceId)
            binding.detailTitle.text = productArticle.title
            binding.detailDatum.text = productArticle.date
            binding.detailText.text = productArticle.article
        }
    }
}
