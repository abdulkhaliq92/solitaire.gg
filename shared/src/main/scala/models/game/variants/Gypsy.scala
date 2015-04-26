package models.game.variants

import java.util.UUID

import models.game._

object Gypsy extends GameVariant.Description {
  override val key = "gypsy"
  override val name = "Gypsy"
  override val body = "..."

  def initialMoves(gameState: GameState, deck: Deck) = {
    (1 to 8).foreach { i =>
      gameState.addCards(deck.getCards(2), "tableau-" + i)
      gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
    gameState.addCards(deck.getCards(), "stock")
  }
}

class Gypsy(override val gameId: UUID, override val seed: Int) extends GameVariant("gypsy", Gypsy, gameId, seed, Gypsy.initialMoves) {
  //  private[this] val drawPiles = (1 to 8).map("tableau-" + _).toSeq
  //
  //  private[this] val stockOptions = new PileOptions(
  //    cardsShown = Some(1),
  //    selectCardConstraint = Some(Constraints.topCardOnly),
  //    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
  //  )
  //
  //  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
  //    dragToConstraint = Some(Constraints.klondikeTableauDragTo(None))
  //  )
}