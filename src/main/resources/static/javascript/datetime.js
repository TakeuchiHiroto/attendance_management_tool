function updateDateTime() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1; // 月は0-indexedなので+1する
	var date = now.getDate();
	var dayOfWeek = ["日", "月", "火", "水", "木", "金", "土"][now.getDay()];
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds(); // 秒数を取得

	// ゼロパディング
	month = ('0' + month).slice(-2);
	date = ('0' + date).slice(-2);
	hours = ('0' + hours).slice(-2);
	minutes = ('0' + minutes).slice(-2);
	seconds = ('0' + seconds).slice(-2); // 秒数もゼロパディング

	var datetimeString1 = "今日の日付 : " + year + "年" + month + "月" + date + "日(" + dayOfWeek + ") ";
	var datetimeString2 = "現在の時間 : " + hours + "時" + minutes + "分" + seconds + "秒"; // 秒数を含める
	document.getElementById('datetime1').innerText = datetimeString1;
	document.getElementById('datetime2').innerText = datetimeString2;
}

// 初回呼び出し
updateDateTime();

// 1秒ごとに更新
setInterval(updateDateTime, 1000); // 1000ミリ秒 = 1秒
