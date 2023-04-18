package com.example.practicetdd_task

class Legacy(
    private val text: String,
    private val interaction: Interaction
) {

    fun map(): LegacyObject {
        return LegacyObject(text, HandleInteraction(text, interaction))
    }
}

data class HandleInteraction(
    private val text: String,
    private val interaction: Interaction
) : () -> Unit {

    override fun invoke() {
        interaction.print(text)
    }
}

data class LegacyObject(
    private val text: String,
    private val lambda: () -> Unit
) {

    fun go() {
        lambda.invoke()
    }
}

interface Interaction {
    fun print(value: String)
}