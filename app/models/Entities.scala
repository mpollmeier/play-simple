package models

import play.api.libs.json.Json

object Entities {
  case class Credentials(user: User, password: Password)
  case class User(value: String) extends AnyVal
  case class Password(value: String) extends AnyVal
}
