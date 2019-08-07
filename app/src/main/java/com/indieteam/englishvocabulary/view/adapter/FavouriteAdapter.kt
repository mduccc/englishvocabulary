package com.indieteam.englishvocabulary.view.adapter

import android.speech.tts.TextToSpeech
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.model.FavouriteModel
import kotlinx.android.synthetic.main.favourite_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class FavouriteAdapter :
    RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {

    private var data = ArrayList<FavouriteModel.Item>()

    fun setData(data: ArrayList<FavouriteModel.Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
        notifyItemRangeChanged(0, data.size - 1)
    }

    fun updateData(data: ArrayList<FavouriteModel.Item>) {
        val lastData = this.data
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun removeData(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, data.size)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.favourite_item, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.bind(p1)
        val view = p0.itemView
        p0.textToSpeech(view, p1)
        // Animation
        val animation = AnimationUtils.loadAnimation(view.context, android.R.anim.fade_in)
        view.startAnimation(animation)
    }

    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view), TextToSpeech.OnInitListener {
        private var tts: TextToSpeech = TextToSpeech(view.context, this)

        override fun onInit(p0: Int) {
//            if (p0 != TextToSpeech.ERROR) {
//                tts.language = Locale.US
//            }
        }

        fun bind(p1: Int) {
            view.apply {
                vocabulary.text = data[p1].vocabulary.capitalize()
                vi.text = data[p1].vi.replace("\n", "").capitalize()
            }
        }

        fun textToSpeech(view: View, p1: Int) {
            view.apply {
                us_speaker.setOnClickListener {
                    Log.d("textToSpeech", "On")
                    val text = data[p1].vocabulary
                    tts.language = Locale.US
                    tts.speak(text, TextToSpeech.QUEUE_ADD, null)
                }

                uk_speaker.setOnClickListener {
                    Log.d("textToSpeech", "On")
                    val text = data[p1].vocabulary
                    tts.language = Locale.UK
                    tts.speak(text, TextToSpeech.QUEUE_ADD, null)
                }
            }
        }
    }

}

