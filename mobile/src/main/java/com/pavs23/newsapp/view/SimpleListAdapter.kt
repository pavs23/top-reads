package com.pavs23.newsapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.pavs23.newsapp.R
import com.pavs23.newsapp.datamodel.NewsApiArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.headline_layout.view.*


/**
 * Created by pavan on 8/7/17.
 */
class SimpleListAdapter(dataList: List<NewsApiArticle>, context: Context) : BaseAdapter() {


    internal val dataList = dataList

    private val mInflator: LayoutInflater = LayoutInflater.from(context)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: HeadlineRowHolder

        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.headline_layout, parent, false)
            viewHolder = HeadlineRowHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as HeadlineRowHolder
        }

        viewHolder.headline.text = dataList[position].title
        Picasso.with(parent?.context)
                .load(dataList[position].urlToImage)
                .into(viewHolder.image)

        return view
    }

    override fun getItem(p0: Int): Any {
        return dataList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    private class HeadlineRowHolder(row: View?) {
        val headline: TextView = row?.findViewById<TextView>(R.id.headline) as TextView
        val image: ImageView = row?.findViewById<ImageView>(R.id.image_view) as ImageView
    }

}

