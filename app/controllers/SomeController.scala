package controllers

import models.Entities._
import org.slf4j.LoggerFactory
import play.api.mvc._

object SomeController extends SomeController

trait SomeController extends Controller {
  protected val log = LoggerFactory.getLogger(getClass.getSimpleName)

  def someEndpoint() = Authenticated { req =>
    println("inside endpoint")
    println(req.user)
    Ok
  }
}
