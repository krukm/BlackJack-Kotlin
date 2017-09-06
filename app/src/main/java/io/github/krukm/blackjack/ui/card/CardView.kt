package io.github.krukm.blackjack.ui.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import io.github.krukm.blackjack.models.Card
import io.github.krukm.blackjack.R


class CardView : RelativeLayout {


    private val aspectRatioHeight = 3.5F
    private val aspectRatioWidth = 2.5F

    private val topFaceTextView: TextView by lazy { findViewById<TextView>(R.id.top_face_text_view) }
    private val topSuitTextView: TextView by lazy { findViewById<TextView>(R.id.top_suit_text_view) }
    private val bottomFaceTextView: TextView by lazy { findViewById<TextView>(R.id.bottom_face_text_view) }
    private val bottomSuitTextView: TextView by lazy { findViewById<TextView>(R.id.bottom_suit_text_view) }
    private val centerSuitTextview: TextView by lazy { findViewById<TextView>(R.id.center_suit_text_view) }

    @JvmOverloads
    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val originalWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val originalHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        val calculatedHeight = originalWidth * aspectRatioHeight / aspectRatioWidth

        val finalWidth: Int
        val finalHeight: Int

        if (calculatedHeight > originalHeight) {
            finalWidth = (originalHeight * aspectRatioWidth / aspectRatioHeight).toInt()
            finalHeight = originalHeight
        } else {
            finalWidth = originalWidth
            finalHeight = calculatedHeight.toInt()
        }

        super.onMeasure(
                View.MeasureSpec.makeMeasureSpec(finalWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(finalHeight, View.MeasureSpec.EXACTLY)
        )
    }

    fun setCard(card: Card) {
        topFaceTextView.text = card.face.name
        topSuitTextView.text = card.suit.name
        bottomFaceTextView.text = card.face.name
        bottomSuitTextView.text = card.suit.name
        centerSuitTextview.text = card.suit.name

    }

}