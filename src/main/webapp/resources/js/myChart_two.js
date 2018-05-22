$(function() {

    var dataArr=[];
    var dataArr1=[];
    $.ajax({
        url: CONTEXT_PATH + "/operation/selectGetOperations",
        data: {},
        type:"POST",
        dataType : "json",
        async:false,
        success: function (data) {
            for (var i=0;i<data.length;i++){
                dataArr.push(data[i][0]);
                dataArr1.push(data[i][1]);
            }
        }
    })

	var myConfig = {
		backgroundColor: 'rgba(255, 255, 255, 0.)',
		type: "ring",
//		title: {
//			text: "Monthly Page Views",
//			fontFamily: 'Lato',
//			fontSize: 14,
//			// border: "1px solid black",
//			padding: "15",
//			fontColor: "#1E5D9E",
//		},
//		subtitle: {
//			text: "06/10/16 - 07/11/16",
//			fontFamily: 'Lato',
//			fontSize: 12,
//			fontColor: "#777",
//			padding: "5"
//		},
		plot: {
			slice: '50%',
			borderWidth: 0,
			backgroundColor: '#FBFCFE',
			animation: {
				effect: 2,
				sequence: 3
			},
			valueBox: [{
					type: 'all',
					text: '%t',
					placement: 'out'
				},
				{
					type: 'all',
					text: '%npv%',
					placement: 'in'
				}
			]
		},
		tooltip: {
			fontSize: 12,
			anchor: 'c',
			x: '50%',
			y: '38%',
			sticky: true,
			backgroundColor: 'rgba(255, 255, 255, 0.)',
			borderWidth: 0,
			thousandsSeparator: ',',
			text: '<span style="color:#02ddd3"> %t</span><br><span style="color:#02ddd3"> %v</span>',
			"padding-top":"35px",
			mediaRules: [{
				maxWidth: 0,
				y: '50%',
			}]
		},
		plotarea: {
			backgroundColor: 'transparent',
			borderWidth: 0,
			borderRadius: "0 0 0 10",
			margin: "0 0 0 0"
		},
		legend: {
			toggleAction: 'remove',
			backgroundColor: 'rgba(255, 255, 255, 0.)',
			borderWidth: 0,
			adjustLayout: true,
			align: 'center',
			verticalAlign: 'bottom',
			marker: {
				type: 'circle',
				cursor: 'pointer',
				borderWidth: 0,
				size: 5
			},
			item: {
				fontColor: "#777",
				cursor: 'pointer',
				offsetX: -6,
				fontSize: 12
			},
			mediaRules: [{
				maxWidth: 500,
				visible: false
			}]
		},
//		scaleR: {
//			refAngle: 270
//		},
		series: [{
				text: "结单数",
				values: dataArr1,
				lineColor: "#E80C60",
				backgroundColor: "#E80C60",
				lineWidth: 1,
				marker: {
					backgroundColor: '#E80C60'
				}
			},
			{
				text: "运维单总数",
				values: dataArr,
				lineColor: "#9B26AF",
				backgroundColor: "#9B26AF",
				lineWidth: 1,
				marker: {
					backgroundColor: '#9B26AF'
				}
			}
		]
	};

	zingchart.render({
		id: 'myChart_two',
		data: {
			gui: {
				contextMenu: {
					button: {
						visible: true,
						lineColor: "#2D66A4",
						backgroundColor: "#2D66A4"
					},
					gear: {
						alpha: 1,
						backgroundColor: "#2D66A4"
					},
					position: "right",
					backgroundColor: "#306EAA",
					/*sets background for entire contextMenu*/
					docked: true,
					item: {
						backgroundColor: "#306EAA",
						borderColor: "#306EAA",
						borderWidth: 0,
						fontFamily: "Lato",
						color: "#fff"
					}

				},
			},
			graphset: [myConfig]
		},
		height: '310',
		width: '800'
	});
})