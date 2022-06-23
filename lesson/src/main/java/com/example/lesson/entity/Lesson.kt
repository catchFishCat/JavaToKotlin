package com.example.lesson.entity

class Lesson(var date: String, var content: String, var state: State) {
    /**
     * 用 enum class 声明枚举类
     * 用 annotation class 声明注解
     * **/
    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String?
    }

}