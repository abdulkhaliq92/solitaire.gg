package services.socket

import java.util.UUID

import akka.actor.{ActorRef, Props}
import models.{SocketStarted, SocketStopped, UserSettings}
import models.user.User
import utils.Logging
import utils.metrics.InstrumentedActor

object SocketService {
  def props(id: Option[UUID], supervisor: ActorRef, user: User, out: ActorRef, sourceAddress: String) = {
    Props(SocketService(id.getOrElse(UUID.randomUUID), supervisor, user, out, sourceAddress))
  }
}

case class SocketService(
    id: UUID, supervisor: ActorRef, user: User, out: ActorRef, sourceAddress: String
) extends InstrumentedActor with RequestMessageHelper with Logging {

  override def preStart() = {
    log.info(s"Starting connection for user [${user.id}: ${user.username.getOrElse("-")}].")
    supervisor ! SocketStarted(user.id, user.username, id, self)
    out ! UserSettings(user.id, user.username, user.email, user.preferences)
  }

  override def postStop() = {
    supervisor ! SocketStopped(id)
  }
}