package com.elexandro.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.elexandro.marsphotos.network.MarsPhoto
import com.elexandro.marsphotos.overview.MarsApiStatus
import com.elexandro.marsphotos.overview.PhotoGridAdapter


@BindingAdapter("imageURL")
public fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
        /*
        * {
        * placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
        * */
    }
}

@BindingAdapter("listData")
public fun bindRecycleView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
public fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when(status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_download_24)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
