package com.babichdev.moneyflow.presentation.model

object Categories {

    val expense = listOf(
        CategoryUi("Продукты", "🛒"),
        CategoryUi("Кафе", "☕"),
        CategoryUi("Транспорт", "🚗"),
        CategoryUi("Дом", "🏠"),
        CategoryUi("Коммунальные", "💡"),
        CategoryUi("Одежда", "👕"),
        CategoryUi("Здоровье", "💊"),
        CategoryUi("Развлечения", "🎮"),
        CategoryUi("Путешествия", "✈️"),
        CategoryUi("Другое", "📦")
    )

    val income = listOf(
        CategoryUi("Зарплата", "💰"),
        CategoryUi("Премия", "🎁"),
        CategoryUi("Подарок", "🎉"),
        CategoryUi("Инвестиции", "📈"),
        CategoryUi("Подработка", "💼"),
        CategoryUi("Другое", "📦")
    )

    fun findEmoji(category: String): String {

        return (expense + income)
            .firstOrNull { it.title == category }
            ?.emoji
            ?: "📦"
    }
}