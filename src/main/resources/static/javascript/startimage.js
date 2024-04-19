var randomList = new Array(
	"/images/syukkin.png",
	"/images/tikoku.png",
	"/images/train.png",
	"/images/asobi.png");
var num = Math.floor(Math.random() * randomList.length);
var printHtml = '<img class="image" src=' + randomList[num] + ' alt="ランダム画像">';
document.write(printHtml);