$(function(){

    var dataArr=[];
    var dataArr1=[];
    $.ajax({
        url: CONTEXT_PATH + "/dataAnalysis/loadDataAnalysisSchoolCityarea",
        data: {schoolCode:$("#schoolCode").val()},
        type:"POST",
        dataType : "json",
        async:false,
        success: function (data) {
            for (var i=0;i<data.data.length;i++){
                dataArr.push(data.data[i][0]);
                dataArr1.push(data.data[i][1]);
            }
        }
    })

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
        "text": "设备使用情况",
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
      	text:"使用数",
        values: dataArr1,
        borderWidth: 0,
        backgroundColor: "#41af89"
      }, {
      	text:"未使用数",
        values: dataArr,
        borderWidth: 0,
        backgroundColor: "#a81d52"
      }, ]
    };

    zingchart.render({
      id: 'myChart_nine',
      data: myConfig,
      height: 220,
      width: '31%'
    });
	
});