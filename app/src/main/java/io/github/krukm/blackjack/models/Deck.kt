package io.github.krukm.blackjack.models

import java.util.*


class Deck {

    val deck: Stack<Card> = Stack()
    val MAX_DECK_SIZE = 52
    var i = 0

    init {
        while (i < MAX_DECK_SIZE) {
            val card = generateRandomCard()
            if (!cardDuplicate(card)) {
                deck.push(card)
                i++
            }
        }
    }

    fun drawCard(): Card {
        return deck.pop()
    }

    fun generateRandomCard(): Card {
        val random = Random()
        val faces = Face.values()
        val suits = Suit.values()

        return Card(faces[random.nextInt(faces.size)],
                suits[random.nextInt(suits.size)])
    }

    fun cardDuplicate(card: Card): Boolean {
        for (c in deck) {
            if (c == card) return true
        }
        return false
    }
}