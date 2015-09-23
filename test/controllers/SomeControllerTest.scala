package controllers

import models.Entities._
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mock.MockitoSugar
import play.api.test._
import play.api.test.Helpers._

class SomeControllerTest extends WordSpec with Matchers with MockitoSugar {

  "some endpoint" can {
    "be accessed when authenticated" in new Fixture {
      val response = controller.someEndpoint()(requestValidAuth)
      status(response) shouldBe OK
    }

    "not be accessed" when {
      "not authenticated" in new Fixture {
        val response = controller.someEndpoint()(FakeRequest())
        status(response) shouldBe UNAUTHORIZED
      }

      "using invalid credentials" in new Fixture {
        val response = controller.someEndpoint()(requestWrongPass)
        status(response) shouldBe UNAUTHORIZED
      }
    }
  }

  trait Fixture {
    val requestValidAuth = FakeRequest().withHeaders(
      ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("correct password"))))
    )
    val requestWrongPass = FakeRequest().withHeaders(
      ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("wrong password"))))
    )
    lazy val controller = new SomeController {
    }
  }
}
