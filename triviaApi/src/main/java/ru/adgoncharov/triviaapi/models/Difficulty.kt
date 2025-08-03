package ru.adgoncharov.triviaapi.models

enum class Difficulty(val value: String, val description: String) {
    ANY("", "Любая"),
    EASY("easy", "Легкая"),
    MEDIUM("medium", "Средняя"),
    HARD("hard", "Сложная");

    override fun toString(): String = value

    fun getName(): String = description
}