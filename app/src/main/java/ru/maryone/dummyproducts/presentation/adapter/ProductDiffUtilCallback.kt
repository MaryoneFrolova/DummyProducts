package ru.maryone.dummyproducts.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.maryone.dummyproducts.domain.model.ProductItem

class ProductDiffUtilCallback (
    private val oldElements: List<ProductItem>,
    private val newElements: List<ProductItem>,
) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldElements.size

        override fun getNewListSize(): Int = newElements.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldElements[oldItemPosition].id == newElements[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            (oldElements[oldItemPosition].title == newElements[newItemPosition].title) &&
            (oldElements[oldItemPosition].description == newElements[newItemPosition].description) &&
            (oldElements[oldItemPosition].thumbnail == newElements[newItemPosition].thumbnail)

}