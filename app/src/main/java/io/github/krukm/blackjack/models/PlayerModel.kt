package io.github.krukm.blackjack.models


class PlayerModel() {
    val hand: MutableList<Card> = mutableListOf<Card>()
    var playerScore: Int = 0
}