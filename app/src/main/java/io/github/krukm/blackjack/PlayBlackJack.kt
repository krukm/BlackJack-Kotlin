package io.github.krukm.blackjack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.github.krukm.blackjack.models.Deck
import io.github.krukm.blackjack.models.PlayerModel

class PlayBlackJack : AppCompatActivity(), View.OnClickListener {

    private val drawButton: Button by lazy { findViewById(R.id.draw_button_view) as Button }
    private val standButton: Button by lazy { findViewById(R.id.stand_button_view) as Button }
    private val resetButtonView: Button by lazy { findViewById(R.id.reset_button) as Button }
    private val player1ScoreView: TextView by lazy { findViewById(R.id.score_value_view) as TextView }
    private val dealerScoreView: TextView by lazy { findViewById(R.id.dealer_score_value_view) as TextView }
    private val gameStatusView: TextView by lazy { findViewById(R.id.game_status_view) as TextView }
    private val dealerCard1: ImageView by lazy { findViewById(R.id.dealer_card1_view) as ImageView }
    private val dealerCard2: ImageView by lazy { findViewById(R.id.dealer_card2_view) as ImageView }
    private val dealerCard3: ImageView by lazy { findViewById(R.id.dealer_card3_view) as ImageView }
    private val dealerCard4: ImageView by lazy { findViewById(R.id.dealer_card4_view) as ImageView }
    private val dealerCard5: ImageView by lazy { findViewById(R.id.dealer_card5_view) as ImageView }
    private val playerCard1: ImageView by lazy { findViewById(R.id.player_card1_view) as ImageView }
    private val playerCard2: ImageView by lazy { findViewById(R.id.player_card2_view) as ImageView }
    private val playerCard3: ImageView by lazy { findViewById(R.id.player_card3_view) as ImageView }
    private val playerCard4: ImageView by lazy { findViewById(R.id.player_card4_view) as ImageView }
    private val playerCard5: ImageView by lazy { findViewById(R.id.player_card5_view) as ImageView }
    private val player1 = PlayerModel()
    private val dealer = PlayerModel()
    private var dealerHasAces = false
    private var hasAces = false
    private var dealerAces = 0
    private var aces = 0
    private var i = 2

    private var deck = Deck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_black_jack)

        initDeal()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        drawButton.setOnClickListener(this)
        standButton.setOnClickListener(this)
        resetButtonView.visibility = View.INVISIBLE
        resetButtonView.isClickable = false
        resetButtonView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.draw_button_view ->
                if (!deck.deck.empty()) {
                    val card = deck.drawCard()

                    if (card.face.name == "ACE") {
                        aces++
                        hasAces = true
                    }

                    player1.hand.add(card)
                    player1.playerScore = player1.playerScore + card.getPoints()
                    when(i) {
                        2 -> addCard3()
                        3 -> addCard4()
                        4 -> addCard5()
                    }
                    i++

                    player1ScoreView.text = player1.playerScore.toString()


                    if (player1.playerScore > 21 && hasAces) {
                        player1.playerScore = player1.playerScore - 10
                        player1ScoreView.text = player1.playerScore.toString()
                        hasAces = false

                    } else if (player1.playerScore > 21 && !hasAces) {
                            gameStatusView.text = "${getString(R.string.bust)}"
                            endGame()
                    }

                } else {
                    gameStatusView.text = "${getString(R.string.empty_deck)}"
                    endGame()
                }

            R.id.reset_button ->
                resetGame()

            R.id.stand_button_view ->
                stand()
        }
    }

    private fun initDeal() {
        player1.hand.add(deck.drawCard())
        player1.hand.add(deck.drawCard())
        val playerCard1Image = resources.getIdentifier(player1.hand[0].toString().toLowerCase(), "drawable", packageName)
        val playerCard2Image = resources.getIdentifier(player1.hand[1].toString().toLowerCase(), "drawable", packageName)
        playerCard1.setImageResource(playerCard1Image)
        playerCard2.setImageResource(playerCard2Image)
        player1.playerScore = player1.hand[0].getPoints() + player1.hand[1].getPoints()
        player1ScoreView.text = player1.playerScore.toString()

        if(player1.hand[0].face.name == "ACE" || player1.hand[1].face.name == "ACE") {
            aces++
            hasAces = true
        }

        dealer.hand.add(deck.drawCard())
        dealer.hand.add(deck.drawCard())
        val dealerCard1Image = resources.getIdentifier(dealer.hand[0].toString().toLowerCase(), "drawable", packageName)
        val dealerCard2Image = resources.getIdentifier(dealer.hand[1].toString().toLowerCase(), "drawable", packageName)
        dealerCard1.setImageResource(dealerCard1Image)
        dealerCard2.setImageResource(dealerCard2Image)
        dealer.playerScore = dealer.hand[0].getPoints() + dealer.hand[1].getPoints()
        dealerScoreView.text = dealer.playerScore.toString()

        if(dealer.hand[0].face.name == "ACE" || dealer.hand[1].face.name == "ACE") {
            dealerAces++
            dealerHasAces = true
        }
    }

    private fun addCard3() {
        val playerCard3Image = resources.getIdentifier(player1.hand[2].toString().toLowerCase(), "drawable", packageName)
        playerCard3.setImageResource(playerCard3Image)
        playerCard3.visibility = View.VISIBLE
    }

    private fun addCard4() {
        val playerCard4Image = resources.getIdentifier(player1.hand[3].toString().toLowerCase(), "drawable", packageName)
        playerCard4.setImageResource(playerCard4Image)
        playerCard4.visibility = View.VISIBLE
    }

    private fun addCard5() {
        val playerCard5Image = resources.getIdentifier(player1.hand[4].toString().toLowerCase(), "drawable", packageName)
        playerCard5.setImageResource(playerCard5Image)
        playerCard5.visibility = View.VISIBLE
    }

    private fun addDealerCard3() {
        val dealerCard3Image = resources.getIdentifier(dealer.hand[2].toString().toLowerCase(), "drawable", packageName)
        dealerCard3.setImageResource(dealerCard3Image)
        dealerCard3.visibility = View.VISIBLE
    }

    private fun addDealerCard4() {
        val dealerCard4Image = resources.getIdentifier(dealer.hand[3].toString().toLowerCase(), "drawable", packageName)
        dealerCard4.setImageResource(dealerCard4Image)
        dealerCard4.visibility = View.VISIBLE
    }

    private fun addDealerCard5() {
        val dealerCard5Image = resources.getIdentifier(dealer.hand[4].toString().toLowerCase(), "drawable", packageName)
        dealerCard5.setImageResource(dealerCard5Image)
        dealerCard5.visibility = View.VISIBLE
    }

    private fun dealerTurn() {
        if(!deck.deck.empty()) {
            var j = 2

            while (dealer.playerScore < 17 && dealer.playerScore <= player1.playerScore) {
                var card = deck.drawCard()

                if(card.face.name == "ACE") {
                    dealerAces++
                    dealerHasAces = true
                }

                dealer.hand.add(card)
                dealer.playerScore = dealer.playerScore + card.getPoints()

                when(j) {
                    2 -> addDealerCard3()
                    3 -> addDealerCard4()
                    4 -> addDealerCard5()
                }

                dealerScoreView.text = dealer.playerScore.toString()
                j++
            }

            if(dealer.playerScore > 21 && dealerHasAces) {
                dealer.playerScore = dealer.playerScore - 10
                dealerScoreView.text = dealer.playerScore.toString()
                dealerHasAces = false

            } else if(dealer.playerScore > 21) {
                    gameStatusView.text = "${getString(R.string.dealer_bust)}"
                    endGame()

            } else {

                if(dealer.playerScore >= player1.playerScore) {
                    gameStatusView.text = "${getString(R.string.dealer_win)}"
                    endGame()

                } else {
                    gameStatusView.text = "${getString(R.string.player_win)}"
                    endGame()
                }
            }

        } else {
            gameStatusView.text = "${getString(R.string.empty_deck)}"
            endGame()
        }
    }

    private fun stand() {
        drawButton.isClickable = false
        standButton.isClickable = false
        dealerTurn()
    }

    private fun endGame() {
        gameStatusView.text = gameStatusView.text.toString() + "${getString(R.string.end_game)}"
        resetButtonView.visibility = View.VISIBLE
        resetButtonView.isClickable = true
        drawButton.isClickable = false
        standButton.isClickable = false
    }

    private fun resetGame() {
        gameStatusView.text = ""
        resetButtonView.visibility = View.INVISIBLE
        resetButtonView.isClickable = false
        drawButton.isClickable = true
        standButton.isClickable = true
        player1.hand.clear()
        dealer.hand.clear()
        playerCard3.visibility = View.INVISIBLE
        playerCard4.visibility = View.INVISIBLE
        playerCard5.visibility = View.INVISIBLE
        dealerCard3.visibility = View.INVISIBLE
        dealerCard4.visibility = View.INVISIBLE
        dealerCard5.visibility = View.INVISIBLE
        deck = Deck()
        hasAces = false
        aces = 0
        initDeal()
        i = 2

    }
}