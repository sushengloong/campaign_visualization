package services

import play.api.Play

import scala.io.Source

class CampaignDataService {
  val DataFilePath = "/resources/campaign_data.csv"
  val CsvDelimiter = '|'

  def load(): Array[Array[String]] = {
    val stream = Play.getClass.getResourceAsStream(DataFilePath)
    val bufferedSource = Source.fromInputStream(stream)
    val data = bufferedSource
      .getLines
      .map { line =>
        val cells = line.split(CsvDelimiter)
        Array(cells(0), cells(1), cells(4))
      }
      .filter(line => line(0) != line(1))
      .toArray
      .drop(1)
    bufferedSource.close
    data
  }
}
