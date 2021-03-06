package settings

import menu.MenuService
import models.settings._
import org.scalajs.jquery.{jQuery => $}

object SettingsPanel {
  private[this] var initialized = false
  private[this] var originalSettings = Settings.default
  private[this] var current = originalSettings
  def getCurrentSettings = current
  def setCurrentSettings(settings: Settings) = current = settings

  private[this] def forBoolean(s: String, v: Boolean) = {
    $(s"#settings-$s-true").prop("checked", v)
    $(s"#settings-$s-false").prop("checked", !v)
  }

  def show(settings: Settings) = {
    originalSettings = settings
    current = originalSettings

    SettingsPanelInit.colorPicker.foreach(_.setHex(settings.backgroundColor))

    Language.values.foreach(l => $(s"#settings-language-${l.value}").prop("checked", settings.language == l))

    forBoolean("tilt", settings.tilt)
    forBoolean("auto-flip", settings.autoFlip)
    forBoolean("audio", settings.audio)

    MenuPosition.values.foreach(p => $(s"#settings-menu-position-${p.value}").prop("checked", settings.menuPosition == p))
    CardBack.values.foreach(cb => $(s"#settings-card-back-${cb.value}").prop("checked", settings.cardBack == cb))
    CardBlank.values.foreach(cb => $(s"#settings-card-blank-${cb.value}").prop("checked", settings.cardBlank == cb))
    CardFaces.values.foreach(cf => $(s"#settings-card-faces-${cf.value}").prop("checked", settings.cardFaces == cf))
    CardLayout.values.foreach(cl => $(s"#settings-card-layout-${cl.value}").prop("checked", settings.cardLayout == cl))
    CardRanks.values.foreach(cr => $(s"#settings-card-ranks-${cr.value}").prop("checked", settings.cardRanks == cr))
    CardSuits.values.foreach(cs => $(s"#settings-card-suits-${cs.value}").prop("checked", settings.cardSuits == cs))
  }

  def initIfNeeded(settings: Settings, menu: MenuService) = {
    if (!initialized) {
      SettingsPanelInit.init(settings)
      initialized = true
    }
    menu.options.setOptionsForSettings()
  }
}
