$(function () {
    var years = [];
    var moneys = [];
    var moneyperent = [];
    $.ajax({
        url: CONTEXT_PATH + '/indexChartData',
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (list) {
            for (var i = 0; i < list.length; i++) {
                years.push(list[i][0]);
                moneys.push(list[i][1]);
                moneyperent.push(list[i][2]*100);
            }
        }
    });

    var myConfig = {
        "type": "line",
//    "plot":{"value-box":{
//          "font-color": "#41af89",
//          "padding":"0px 0px 0px 0px"
//      }
//      },
        "background-color": "rgba(0, 0, 0, 0.3)",
        "border-radius": "5px",
        "legend": {
            "layout": "float",
            "background-color": "none",
            "border-color": "none",
            "item": {
                "font-color": "#03d5cc"
            },
            "x": "6%",
            "y": "16%",
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
        "scale-y-2": {
            "max-value": 100,
            "label": {
                "text": "投入占比"
            }
        },
        "scale-x": {
            "alpha": 0.1,
            "border-bottom": "1px solid #41af89",
            "border-left": "1px solid #41af89",
            "values": years,
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
                "font-angle": 360,
                "text":"年份",
                "font-color":"#03d5cc"
            },
            "item": {
                "text-align": "center",
                "font-color": "#41af89"
            },
        },
        "series": [{
            "values": moneys,
            "scales": "scale-x,scale-y",
            "text": "设备建设情况",
            "background-color": "#03A9F4",
            "border-color": "#03A9F4",
            "legend-marker": {
                "border-color": "#03A9F4"
            },
            "tooltip": {
                "background-color": "#03A9F4",
                "text": "建设年代:%kt年\n投入资金:%v元",
                "font-size": "12px",
                "padding": "6 12",
                "border-color": "none",
                "shadow": 0,
                "border-radius": 5
            }
        }
            , {
                "values": moneyperent,
                "scales": "scale-x,scale-y-2",
                "text": "投入占比",
                "background-color": "#41af89",
                "border-color": "#41af89",
                "legend-marker": {
                    "border-color": "#41af89"
                },
                "tooltip": {
                    "background-color": "#41af89",
                    "text": "当年投入占比:%v%",
                    "font-size": "12px",
                    "padding": "6 12",
                    "border-color": "none",
                    "shadow": 0,
                    "border-radius": 5
                }
            },
//          {
//         "values": [34200, 12750, 24250, 11500, 22550, 24250],
//         "text": "完好数",
//         "background-color": "#673AB7",
//         "border-color": "#673AB7",
//         "legend-marker": {
//           "border-color": "#673AB7"
//         },
//         "tooltip": {
//           "background-color": "#673AB7",
// //        "text": "$%v",
//           "font-size": "12px",
//           "padding": "6 12",
//           "border-color": "none",
//           "shadow": 0,
//           "border-radius": 5
//         }
//       }
        ]
    };

    zingchart.render({
        id: 'myChart',
        data: myConfig,
        height: '310',
        width: '900',
    });
})
