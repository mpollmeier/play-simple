import controllers._
import models.Entities._
import play.api._
import play.api.ApplicationLoader.Context
import play.api.mvc._
import play.api.routing._
import play.api.routing.sird._

class MyApplicationLoader extends ApplicationLoader {
  override def load(context: Context) = {
    new MyComponents(context).application
  }
}

class MyComponents(context: Context) extends BuiltInComponentsFromContext(context) {
  val someController: SomeController = SomeController

  override lazy val router = Router.from {
    case GET(p"/someEndpoint") =>
      someController.someEndpoint()
  }
}
