function changeWidth(){
	for (var i=0;i<50;i++) {
		var n=i;
		var tem=$(".temperature>span").eq(n).html();
		var tems=((tem/(50-0))*158)+40;
		$(".temperature").eq(n).css("width",tems);
	}
	
	for (var i=0;i<50;i++) {
		var n=i;
		var hum=$(".humidity>span").eq(n).html();
		var hums=((hum/(90-20))*140)+18;
		$(".humidity").eq(n).css("width",hums);
	}
	
	
	for (var i=0;i<50;i++) {
		var n=i;
		var pm=$(".PM>span").eq(n).html();
		var pms=((pm/(300-0))*145)+50;
		$(".PM").eq(n).css("width",pms);
	}
	
	for (var i=0;i<50;i++) {
		var n=i;
		var ill=$(".illumination>span").eq(n).html();
		var ills=((ill/(65535-0))*120)+70;
		$(".illumination").eq(n).css("width",ills);
	}
	
	for (var i=0;i<50;i++) {
		var n=i;
		var noise=$(".noise>span").eq(n).html();
		var noises=((noise/(150-0))*150)+40;
		$(".noise").eq(n).css("width",noises);
	}
	
}




$(function(){
	changeWidth();
});
