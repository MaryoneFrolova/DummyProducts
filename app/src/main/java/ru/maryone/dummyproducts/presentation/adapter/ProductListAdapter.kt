package ru.maryone.dummyproducts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.maryone.dummyproducts.databinding.ItemProductBinding
import ru.maryone.dummyproducts.domain.model.ProductItem

class ProductListAdapter(
    var elements: List<ProductItem>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var onReachEndListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elements[position])
        if (position == elements.size - 5) {
            onReachEndListener?.invoke()
        }
    }

    override fun getItemCount(): Int = elements.size

    class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: ProductItem) {
            with(binding) {
                titleTextView.text = element.title
                descriptionTextView.text = element.description
                Glide
                    .with(thumbnailImageView.context)
                    .load(element.thumbnail)
                    .into(thumbnailImageView)
            }
        }
    }
}