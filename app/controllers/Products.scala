package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.Configuration
import play.api.i18n.{I18nSupport, MessagesApi}

@Singleton
class Products @Inject()(implicit val messagesApi: MessagesApi, val configuration: Configuration) extends Controller with I18nSupport {
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }
}