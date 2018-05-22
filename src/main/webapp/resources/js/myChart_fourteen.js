$(function() {

    var dataArr = [];
    var dataArr1 = [];
    $.ajax({
        url: CONTEXT_PATH + "/application/loadEquimentSchoolList",
        data: {},
        type: "POST",
        dataType: "json",
        async: false,
        success: function (data) {
            for (i = 0; i < data.data.length; i++) {
                dataArr.push(data.data[i][1]);
                dataArr1.push(data.data[i][5] * 100);
            }
        }
    });

	var myConfig = {
		"type": "bar",
		    "plot":{"value-box":{
		          "font-color": "#4ca3b9",
		          "padding":"0px 0px 0px 0px"
		      }
		      },
		"background-color": "rgba(255, 255, 255, 0.)",
		"border-radius": "5px",
		    "legend": {
		      "layout": "float",
		      "background-color": "none",
		      "border-color": "none",
		      "item": {
		        "font-color": "#03d5cc"
		      },
		      "x": "0.8%",
		      "y": "12%",
		      "width": "90%",
		      "shadow": 0
		    },
		"title": {
			"text": "实时图表数据",
			"height": "40px",
			"font-size": "20px",
			x: "-46.2%",
			"font-weight": "normal",
			"color": "#02ddd3"
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
			"values": dataArr,
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
			"values": dataArr1,
			"text": "使用率",
			"background-color": "#4ca3b9",
			"border-color": "#4ca3b9",
			"legend-marker": {
				"border-color": "#4ca3b9"
			},
			"tooltip": {
				"background-color": "#4ca3b9",
				"text": "使用率%v%",
				"font-size": "16px",
				"padding": "6 12",
				"border-color": "none",
				"shadow": 0,
				"border-radius": 5
			}
		}
		// ,{
		// 	"values": [3420, 3275, 2620, 2150, 1550, 2213,3233,3111],
		// 	"text": "班级数",
		// 	"background-color": "#41af89",
		// 	"border-color": "#41af89",
		// 	"legend-marker": {
		// 		"border-color": "#41af89"
		// 	},
		// 	"tooltip": {
		// 		"background-color": "#41af89",
		// 		//        "text": "$%v",
		// 		"font-size": "16px",
		// 		"padding": "6 12",
		// 		"border-color": "none",
		// 		"shadow": 0,
		// 		"border-radius": 5
		// 	}
		// }
		]
	};

	zingchart.render({
		id: 'myChart_fourteen',
		data: myConfig,
		height: '340',
		width: '1600',
	});
})