package com.bmf.myapplication.next

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull

abstract class HAdapter<T, B : ViewDataBinding> : RecyclerView.Adapter<HHolder<B>>() {

    //设置的数据
    lateinit var mData: MutableList<T>
    //旧的数据
    lateinit var oldData: MutableList<T>
    private var useDiffUtil = true//默认使用
    var NO_MAX_SIZE = -1
    //头部
    private val headerCount = 0

    //绑定viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HHolder<B> {
        val binding: B =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getItemLayoutId(),
                parent,
                false
            )
        dealViewListener(binding)
        return HHolder(binding)
    }


    override fun onBindViewHolder(holder: HHolder<B>, position: Int) {
        var itemData: T? = null
        if (position >= headerCount) {
            itemData = mData[position]
            holder.holderBinding.setVariable(getBrId(holder.itemViewType), itemData)
            holder.holderBinding.executePendingBindings()
        }
        itemData?.let {
            dealData(holder.absoluteAdapterPosition, holder.holderBinding, it)
        }
    }

    /**
     * 返回xml中item的 id
     * @sample
     * <variable>
     *     <name="item"  type="xxx.xx.User"/>
     * </variable>
     */
    abstract fun getBrId(itemViewType: Int): Int

    /**
     * 处理数据和view的操作
     */
    open fun dealData(absolutePos: Int, hBinding: ViewDataBinding, item: T) {

    }

    /**
     * 处理view的事件绑定 以及其他跟数据无关的操作
     */
    open fun dealViewListener(binding: B) {}

    /**
     * 返回item的布局id
     */
    abstract fun getItemLayoutId(): Int

    fun setData(@NotNull data: List<T>) {
        oldData = mutableListOf()
        mData.clear()
        mData.addAll(data)
        if (useDiffUtil) {
            val diffUtilResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    areItemsTheSame(oldData, mData, oldItemPosition, newItemPosition)

                override fun getOldListSize(): Int = oldData.size

                override fun getNewListSize(): Int = mData.size

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = areContentsTheSame(oldData, mData, oldItemPosition, newItemPosition)

            })
            diffUtilResult.dispatchUpdatesTo(this)
        }
    }

    /**
     * 设置是否使用DiffUtil
     */
    fun setDiffUtil(isUse: Boolean) {
        useDiffUtil = isUse
    }

    fun areItemsTheSame(
        oldData: List<T?>?,
        data: List<T?>?,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = getItemViewType(oldItemPosition) == getItemViewType(newItemPosition)

    fun areContentsTheSame(
        oldData: List<T>,
        data: List<T>,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldData[oldItemPosition] == data[newItemPosition]

    open fun getMaxSize(): Int = NO_MAX_SIZE

    override fun getItemCount() = if (getMaxSize() <= NO_MAX_SIZE) mData.size else {
        mData.size.coerceAtMost(getMaxSize()) + getHeadCount()
    }

    open fun getHeadCount(): Int {
        return headerCount
    }
}


/**
 * recyclerview 的holder
 */
class HHolder<B : ViewDataBinding>(val holderBinding: B) :
    RecyclerView.ViewHolder(holderBinding.root)