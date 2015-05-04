package services.help

import models.game.rules._

object GameRulesHelpService {
  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  def description(desc: String, link: Boolean = true) = {
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = if(id == "fortytheives") {
        GameRulesSet.allById("fortythieves")
      } else {
        GameRulesSet.allById(id)
      }
      if(link) {
        val url = controllers.routes.HomeController.help(rules.id).url
        desc.replaceAllLiterally("^" + id + "^", "<a href=\"" + url + "\">" + rules.title + "</a>")
      } else {
        desc.replaceAllLiterally("^" + id + "^", rules.title)
      }
    }
    linked
  }

  def objective(vc: VictoryCondition, crm: CardRemovalMethod) = vc match {
    case VictoryCondition.AllButFourCardsOnFoundation => "Place all but four cards on the foundation."
    case VictoryCondition.AllOnFoundation => "Place all cards on the foundation."
    case VictoryCondition.AllOnFoundationOrStock => "Place all cards on the foundation or stock."
    case VictoryCondition.AllOnTableauSorted => "Sort all cards on the tableau."
    case VictoryCondition.NoneInPyramid => "Remove all cards from the pyramid."
    case VictoryCondition.NoneInStock => "Remove all cards from the stock."
  }

  def layout(layoutString: String, rules: GameRules) = {
    var foundationsProcessed = 0
    var tableausProcessed = 0
    var pyramidsProcessed = 0

    layoutString.flatMap {
      case 's' => rules.stock.map(StockHelpService.stock)
      case 'w' => rules.waste.map(WasteHelpService.waste)
      case 'r' => rules.reserves.map(ReserveHelpService.reserve)
      case 'c' => rules.cells.map(CellHelpService.cell)
      case 'f' =>
        val fr = rules.foundations(foundationsProcessed)
        foundationsProcessed += 1
        Some(FoundationHelpService.foundation(fr, rules.deckOptions))
      case 't' =>
        val tr = rules.tableaus(tableausProcessed)
        tableausProcessed += 1
        Some(TableauHelpService.tableau(tr))
      case 'p' =>
        val pr = rules.pyramids(pyramidsProcessed)
        pyramidsProcessed += 1
        Some(PyramidHelpService.pyramid(pr))
      case x if x == '2' || x == '3' || x == '4' =>
        None
      case x if x == '|' || x == ':' || x == '.' =>
        None
    }
  }
}
