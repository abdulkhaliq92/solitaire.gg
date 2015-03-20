package services.database

import com.simple.jdub._
import utils.Config
import utils.metrics.Checked

object DatabaseConnection {
  private val db = {
    val url = "jdbc:postgresql://localhost/scalataire"
    val username = "scalataire"
    val password = "omgWTFdragonz!"
    val name = Some(Config.projectId)
    val healthCheckRegistry = Some(Checked.healthCheckRegistry)
    val maxSize = 32
    val maxWait = 10000
    Database.connect(url, username, password, name, maxWait = maxWait, maxSize = maxSize, healthCheckRegistry = healthCheckRegistry)
  }

  def open() = {
    Class.forName("org.postgresql.Driver")
    DatabaseSchema.update()
  }

  def close() = {
    db.close()
  }

  def transaction[A](f: => A) = {
    db.transactionScope(f)
  }

  def query[A](query: RawQuery[A]) = {
    db.query(query)
  }

  def execute(statement: Statement) = {
    db.execute(statement)
  }
}
