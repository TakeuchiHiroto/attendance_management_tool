// countdown.js

// カウントダウンを開始する関数
function startCountdown() {
	var count = 5; // カウントダウンの初期値

	// カウントダウンを行うタイマー
	var timer = setInterval(function() {
		count--; // カウントを減らす

		// カウントが0になったらリダイレクトする
		if (count <= 0) {
			clearInterval(timer); // タイマーを停止
			window.location.href = "/main"; // リダイレクトする
		} else {
			// カウントダウンを表示する
			document.getElementById("countdown").textContent = "このページは" + count + "秒後に自動的にリダイレクトされます。";
		}
	}, 1000); // カウントダウンを1秒ごとに更新する
}

// ページの読み込みが完了したらカウントダウンを開始する
window.onload = startCountdown;
