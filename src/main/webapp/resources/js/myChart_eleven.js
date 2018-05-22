$(function() {

	changeChart();
	$('.control_chart li').eq(0).trigger('click');



});

function changeChart() {
    $(document).on('click','.control_chart li',function () {
    	var schoolCode=$("#schoolCode").val();
    	var newDate=$(this).attr('newDate');
        var datas=[];
        var titles=[];
        $.ajax({
            url: CONTEXT_PATH + "/dataAnalysis/loadDataAnalysisSchoolListChart",
            data: {schoolCode:schoolCode,newDate:newDate },
            type:"POST",
            dataType : "json",
            async:false,
            success: function (data) {
                for (var i=0;i<data.length;i++){
                    datas.push(data[i][0]);
                    titles.push(data[i][1]);
                }
                loadChart(titles,datas)
            }
        });
    });
}


function loadChart(titles,datas) {
    var myConfig = {
        "type": "line",
        "plot":{"value-box":{
            "font-color": "#4ca3b9",
            "padding":"0px 0px 0px 0px"
        }
        },
        "background-color": "rgba(255, 255, 255, 0.)",
        "border-radius": "5px",
        //    "legend": {
        //      "layout": "float",
        //      "background-color": "none",
        //      "border-color": "none",
        //      "item": {
        //        "font-color": "#03d5cc"
        //      },
        //      "x": "6%",
        //      "y": "16%",
        //      "width": "90%",
        //      "shadow": 0
        //    },
        "title": {
            "text": "每年应用课时数一览表",
            "height": "40px",
            "font-size": "20px",
            x: "-41.5%",
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
            "values": titles,
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
            "values": datas,
            "text": "课时数",
            "line-color": "#4ca3b9",
            "border-color": "#4ca3b9",
            "legend-marker": {
                "border-color": "#4ca3b9"
            },
            "tooltip": {
                "background-color": "#4ca3b9",
                "text": "使用课时数%v节",
                "font-size": "16px",
                "padding": "6 12",
                "border-color": "none",
                "shadow": 0,
                "border-radius": 5
            }
        }]
    };
    zingchart.render({
        id: 'myChart_eleven',
        data: myConfig,
        height: '430',
        width: '1260',
    });
}


