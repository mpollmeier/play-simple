package controllers

import java.util.Base64
import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._
import play.api.mvc.Security.AuthenticatedRequest
import scala.concurrent.Future
import models.Entities._

import AuthenticationHelpers._
object Authenticated extends AuthenticatedBuilder(
  _.headers.get("Authorization").flatMap(parseAuthHeader).flatMap(validateUser)
)

object AuthenticationHelpers {
  def authHeaderValue(credentials: Credentials) =
    "Basic " + Base64.getEncoder.encodeToString(s"${credentials.user.value}:${credentials.password.value}".getBytes)

  def parseAuthHeader(authHeader: String): Option[Credentials] =
    authHeader.split("""\s""") match {
      case Array("Basic", userAndPass) ⇒
        new String(Base64.getDecoder.decode(userAndPass), "UTF-8").split(":") match {
          case Array(user, password) ⇒ Some(Credentials(User(user), Password(password)))
          case _ ⇒ None
        }
      case _ ⇒ None
    }

  val validCredentials = Set(
    Credentials(User("michael"), Password("correct password"))
  )

  def validateUser(c: Credentials): Option[User] =
    if (validCredentials.contains(c))
      Some(c.user)
    else
      None
}

