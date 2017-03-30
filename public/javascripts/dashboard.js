var colors = [
    "#da4480",
    "#5ab449",
    "#7f5acd",
    "#aab740",
    "#ce58c0",
    "#50a26e",
    "#d1434b",
    "#45c0bc",
    "#ce5929",
    "#4e7bda",
    "#d49d3c",
    "#6660a3",
    "#7b853c",
    "#b58dde",
    "#97622e",
    "#609dd6",
    "#e29074",
    "#9c4b88",
    "#ab505f",
    "#dc85b6"
]

var buildChordDiagram = function(data) {
    var ch = viz.ch().data(data)
          .padding(.01)
          .innerRadius(430)
          .outerRadius(450)
          .duration(1000)
          .chordOpacity(0.3)
          .labelPadding(.03)
          .fill(function(d) {
            return _.sample(colors);
          });
    var width=1200, height=1100;
    var svg = d3.select("div#chord-diagram-container").append("svg").attr("height",height).attr("width",width);
    svg.append("g").attr("transform", "translate(600,550)").call(ch);
    d3.select(self.frameElement).style("height", height+"px").style("width", width+"px");
};

axios.get("/data")
    .then(function(response) {
        document.querySelector("div#chord-diagram-container").innerText = "";
        buildChordDiagram(response.data.campaign_data);
    })
    .catch(function (error) {
        console.log(error);
    });