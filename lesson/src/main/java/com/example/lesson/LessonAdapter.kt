package com.example.lesson


import androidx.recyclerview.widget.RecyclerView
import com.example.lesson.LessonAdapter.LessonViewHolder
import com.example.lesson.entity.Lesson
import android.view.ViewGroup
import com.example.core.BaseViewHolder
import com.example.lesson.R
import android.view.LayoutInflater
import android.view.View
import java.util.ArrayList

class LessonAdapter : RecyclerView.Adapter<LessonViewHolder>() {
    private var list: List<Lesson> = ArrayList()
    internal fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    /**
     * 静态内部类
     * 如用嵌套内部类，需要用 inner 修饰
     * internal 关键字：模块(module)内可见，防止无用
     */
    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {
        internal fun onBind(lesson: Lesson) {
            val date = lesson.date
            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)
            val state = lesson.state
            setText(R.id.tv_state, state.stateName())
            var colorRes = R.color.playback
            colorRes = when (state) {
                Lesson.State.PLAYBACK -> {

                    // 即使在 {} 中也是需要 break 的。
                    R.color.playback
                }
                Lesson.State.LIVE -> R.color.live
                Lesson.State.WAIT -> R.color.wait
            }
            val backgroundColor = itemView.context.getColor(colorRes)
            getView<View>(R.id.tv_state)!!.setBackgroundColor(backgroundColor)
        }

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false)
                )
            }
        }
    }
}