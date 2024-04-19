var randomList = new Array(
	"/images/taikin.png",
	"/images/jousi.png",
	"/images/tukare.png",
	"/images/nomikai.png");
var num = Math.floor(Math.random() * randomList.length);
var printHtml = '<img class="image" src=' + randomList[num] + ' alt="ランダム画像">';
document.write(printHtml);