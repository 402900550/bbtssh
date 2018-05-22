$(function(){
	var myConfig = {
		backgroundColor: 'rgba(255, 255, 255, 0.)',
      type: 'ring',
      fontColor: "#ffffff",
      plot: {
        slice: 33, // set hole size in middle of chart
        detached: false // turn off click on slices
      },
      tooltip: {
        visible: true,
        fontSize: 12,
//			anchor: 'c',
//			x: '68%',
//			y: '48%',
//			sticky: true,
			backgroundColor: '#3c2c58',
//			borderWidth: 0,
//			thousandsSeparator: ',',
			borderWidth: 0,
			text: '<span style="color:#02ddd3"> %t</span><br><span style="color:#02ddd3"> %v</span>',
//			"padding-top":"35px",
			mediaRules: [{
				maxWidth: 0,
				y: '50%',
			}]
      },
      "title": {
        "text": "课内外使用情况",
        "height": "40px",
        "font-size":"20px",
        x:"-23%",
        "font-weight": "normal",
        "color": "#02ddd3"
      },
      legend: {
        toggleAction: 'remove', // remove plot so it re-calculates percentage
        verticalAlign: 'middle',
        align: 'left',
        "padding-top":"-35px",
        backgroundColor: 'rgba(255, 255, 255, 0.)',
        layout: 'vertical',
        borderWidth: 0,
        marker: {
//        type: 'circle',
          size: 9,
          cursor: 'pointer',
          borderWidth: 0
        },
        item: {
          fontSize: 12,
          cursor: 'pointer',
          offsetX: -5,
          "color": "#ffffff"
        }
      },
      series: [{
      	text:"课内使用",
        values: [3413],
        borderWidth: 0,
        backgroundColor: "#41af89"
      }, {
      	text:"课外使用",
        values: [2023],
        borderWidth: 0,
        backgroundColor: "#a81d52"
      }, ]
    };

    zingchart.render({
      id: 'myChart_ten',
      data: myConfig,
      height: 220,
      width: '75%'
    });
	
});