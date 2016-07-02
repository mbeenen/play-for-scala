package controllers

import javax.inject.{Inject, Singleton}

import play.api.data.Form;
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.mvc.{Action, Controller, Result}
import models.Product
import play.api.Configuration
import play.api.i18n.{I18nSupport, MessagesApi}

@Singleton
class Products @Inject()(implicit val messagesApi: MessagesApi, val configuration: Configuration) extends Controller with I18nSupport {
  private val productForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying(
        "validation.ean.duplicate", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }

  def show(ean: Long) = Action { implicit request =>

    val result: Option[Result] = for {
      product <- Product.findByEan(ean)
    } yield Ok(views.html.products.details(product))
    result.getOrElse(NotFound)
  }
}