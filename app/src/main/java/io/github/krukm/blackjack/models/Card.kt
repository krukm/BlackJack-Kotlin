package io.github.krukm.blackjack.models


data class Card(
        val face: Face,
        val suit: Suit) {

    fun getPoints(): Int {
        return face.points
    }

    override fun toString() = "${face.name}${suit.name}"

}