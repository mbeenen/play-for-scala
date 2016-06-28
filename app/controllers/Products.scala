package controllers

import javax.inject.Singleton

import play.api.mvc.{Action, Controller}
import models.Product

@Singleton
class Products extends Controller {
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }
}