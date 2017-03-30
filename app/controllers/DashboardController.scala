package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.libs.json._

import services._

class DashboardController @Inject()(campaignDataService: CampaignDataService) extends Controller {
  def index = Action {
    Ok(views.html.dashboard.index())
  }

  def data = Action {
    val campaignData = campaignDataService.load()
    val jsonObj = campaignData.map(d => Array(JsString(d(0)), JsString(d(1)), JsNumber(d(2).toInt)))
    Ok(Json.obj("campaign_data" -> jsonObj))
  }
}
