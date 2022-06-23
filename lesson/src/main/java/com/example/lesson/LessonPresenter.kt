package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient.get
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

class LessonPresenter(private val activity: LessonActivity) {
    private var lessons: List<Lesson> = ArrayList()
    private val type = object : TypeToken<List<Lesson?>?>() {}.type
    fun fetchData() {
        get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(lessons: List<Lesson>) {
                //
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message) }
            }
        })
    }

    fun showPlayback() {
        /**
         * List 是不可修改的，用 List 创建。
         * mutableList、ArrayList 是可修改的，用 xxxOf 创建
         * **/
        val playbackLessons =  mutableListOf<Lesson>()
        for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }

    companion object {
        /**
         * 用 const val 声明编译期常量
         * **/
        private const val LESSON_PATH = "lessons"
    }
}