function updateScore(url, dtid) {
	if (validateRegex("score-"+dtid, /^\d+(\.\d+)?$/)) {
		var score = document.getElementById("score-" + dtid).value;
		window.location = url + "&details.score=" + score + "&details.dtid=" + dtid;
	}
}