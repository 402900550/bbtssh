$(function(){
    var runtimeCount=[];
    var monitorCount=[];
    var schoolName=[];
    $.ajax({
        url: CONTEXT_PATH + '/indexChartDataToRuntime',
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (list) {
            for (var i = 0; i < list.length; i++) {
                monitorCount.push(list[i][1]);
                runtimeCount.push(list[i][2]);
                schoolName.push(list[i][0]);
            }
        }
    });
	var myConfig = {
      "type": "bar",
      "plot":{"value-box":{
            "font-color": "#41af89",
            "padding":"0px 0px 0px 0px"
        }
        },
      "background-color": "rgba(255, 255, 255, 0.)",
      "border-radius":"5px",
      "title": {
        "text": "重庆市沙坪坝区实时运行情况",
        "height": "40px",
        "font-weight": "normal",
        "color": "#41af89"
      },
      "legend": {
        "layout": "float",
        "background-color": "none",
        "border-color": "none",
        "item": {
          "font-color": "#03d5cc"
        },
        "x": "40%",
        "y": "13%",
        "width": "90%",
        "shadow": 0
      },
      "plotarea": {
        "margin": "95px 35px 50px 70px"
      },
      "scale-y": {
        "alpha": 0,
        "border-left": "1px solid #41af89",
        "guide": {
          "visible": false
        },
        "tick": {
          "line-color": "#19d0d0",
          "alpha": 0.2
        },
        "item": {
          "font-color": "#41af89",
          "padding-right": "6px"
        }
      },
      "scale-x": {
        "alpha": 0.1,
        "border-bottom": "1px solid #41af89",
        "border-left": "1px solid #41af89",
        "values": schoolName,
        "guide": {
          "visible": false
        },
        "tick": {
          "visible": false
        },
       "label": {
						"font-size": "14px",
						"font-weight": "normal",
						"offset-x": "10%",
						"font-angle": 360
					},
					"item": {
						"text-align": "center",
						"font-color": "#41af89"
					},
      },
      "series": [{
        "values": runtimeCount,
        "text": "正在使用数",
        "background-color": "#03A9F4",
        "border-color": "#03A9F4",
        "legend-marker": {
          "border-color": "#03A9F4"
        },
        "tooltip": {
          "background-color": "#03A9F4",
          "text": "正在使用数%v个",
          "font-size": "12px",
          "padding": "6 12",
          "border-color": "none",
          "shadow": 0,
          "border-radius": 5
        }
      }, {
        "values": monitorCount,
        "text": "监测数",
        "background-color": "#673AB7",
        "border-color": "#673AB7",
        "legend-marker": {
          "border-color": "#673AB7"
        },
        "tooltip": {
          "background-color": "#673AB7",
          "text": "监测数%v个",
          "font-size": "12px",
          "padding": "6 12",
          "border-color": "none",
          "shadow": 0,
          "border-radius": 5
        }
      }]
    };

		zingchart.render({
			id: 'myChart_three',
			data: myConfig,
			height: '310',
			width: '900',
		});
})
