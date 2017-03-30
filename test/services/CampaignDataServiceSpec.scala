package services

import org.scalatest._
import org.scalatestplus.play._
import org.scalatest.mock.MockitoSugar

class CampaignDataServiceSpec extends PlaySpec with MockitoSugar with BeforeAndAfter {
  var campaignDataService: CampaignDataService = _
  var data: Array[Array[String]] = _

  before {
    campaignDataService = new CampaignDataService()
    data = campaignDataService.load
  }

  "CampaignDataService#load" should {
    "return an array of data" in {
      data.isInstanceOf[Array[Array[String]]] mustBe true
    }

    "return the correct number of rows exclusive of pairwise of same campaigns" in {
      data.length mustBe 118
    }

    "removes pairwise of same campaigns" in {
      data.forall(d => d(0) != d(1)) mustBe true
    }

    "remove header row" in {
      val firstRow = data(0)
      firstRow(0) must not be "campaign_1"
      firstRow(1) must not be "campaign_2"
      firstRow(2) must not be "user_overlap"
    }

    "return first row of data correctly" in {
      val firstRow = data(0)
      firstRow(0) mustBe "campaign A"
      firstRow(1) mustBe "campaign B"
      firstRow(2) mustBe "2182"
    }

    "return last row of data correctly" in {
      val lastRow = data.last
      lastRow(0) mustBe "campaign AA"
      lastRow(1) mustBe "campaign D"
      lastRow(2) mustBe "1280"
    }
  }
}
