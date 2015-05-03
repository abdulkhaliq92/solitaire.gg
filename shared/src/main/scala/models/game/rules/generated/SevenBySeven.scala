// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SevenBySeven extends GameRules(
  id = "sevenbyseven",
  title = "Seven by Seven",
  description = "This danish game with a seven-by-seven tableau and three cells allows two redeals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3,
      initialCards = 3
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  ),
  complete = false
)
