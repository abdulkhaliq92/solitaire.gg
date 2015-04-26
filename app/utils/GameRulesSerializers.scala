package utils

import models.game.rules._
import play.api.libs.json._

object GameRulesSerializers {
  import GameSerializers._

  def stringWriter[T] = new Writes[T] {
    override def writes(t: T) = JsString(t.toString)
  }

  implicit val victoryConditionWrites = stringWriter[VictoryCondition]
  implicit val cardRemovalMethodWrites = stringWriter[CardRemovalMethod]

  implicit val deckOptionsWrites = Json.writes[DeckOptions]

  implicit val initialCardsWrites = stringWriter[InitialCards]

  // Stock
  implicit val stockDealToWrites = stringWriter[StockDealTo]
  implicit val stockCardsDealtWrites = stringWriter[StockCardsDealt]

  implicit val stockWrites = Json.writes[StockRules]

  // Waste
  implicit val wastePlayableCardsWrites = stringWriter[WastePlayableCards]

  implicit val wasteWrites = Json.writes[WasteRules]

  // Foundation
  implicit val foundationCanMoveFromWrites = stringWriter[FoundationCanMoveFrom]
  implicit val foundationLowRankWrites = stringWriter[FoundationLowRank]
  implicit val suitMatchRuleWrites = stringWriter[SuitMatchRule]
  implicit val rankMatchRuleWrites = stringWriter[RankMatchRule]

  implicit val foundationSetWrites = Json.writes[FoundationRules]

  // Tableau
  implicit val tableauFaceDownCardsWrites = stringWriter[TableauFaceDownCards]
  implicit val tableauAutoFillEmptyFromWrites = stringWriter[TableauAutoFillEmptyFrom]
  implicit val tableauFillEmptyWithWrites = stringWriter[TableauFillEmptyWith]
  implicit val pileActionWrites = stringWriter[PileAction]

  implicit val tableauSetWrites = Json.writes[TableauRules]

  // Cell
  implicit val cellSetWrites = Json.writes[CellRules]

  // Reserve
  implicit val reserveSetWrites = Json.writes[ReserveRules]

  // Pyramid
  implicit val pyramidTypeWrites = stringWriter[PyramidType]
  implicit val pyramidFaceDownCardsWrites = stringWriter[PyramidFaceDownCards]
  implicit val pyramidFillEmptyWithWrites = stringWriter[PyramidFillEmptyWith]

  implicit val pyramidSetWrites = Json.writes[PyramidRules]

  // Game Rules
  implicit val gameRulesWrites = Json.writes[GameRules]
}