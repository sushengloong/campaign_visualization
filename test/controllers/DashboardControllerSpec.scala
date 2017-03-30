package controllers

import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.libs.json._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import org.mockito.Mockito._

import scala.concurrent.Future
import services._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class DashboardControllerSpec extends PlaySpec with Results with MockitoSugar {
  "DashboardController" should {
    "render page" in {
      val service = new CampaignDataService()
      val controller = new DashboardController(service)
      val result: Future[Result] = controller.index().apply(FakeRequest())
      status(result) mustBe OK
    }

    //TODO: Test json data
    "return campaign data in json" in {
      val service = new CampaignDataService()
      val controller = new DashboardController(service)
      val result: Future[Result] = controller.data().apply(FakeRequest())
      val json: JsValue = contentAsJson(result)

      status(result) mustBe OK
    }

    // TODO: spy isn't working
//    "call CampaignDataService" in {
//      val service = new CampaignDataService()
//      val spiedService = spy(service)
//      val controller = new DashboardController(spiedService)
//
//      verify(spiedService).load()
//      val result: Future[Result] = controller.data().apply(FakeRequest())
//    }

  }

}
