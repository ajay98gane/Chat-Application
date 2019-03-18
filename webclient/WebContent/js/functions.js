var xhttp = new XMLHttpRequest();
function response(post, sendto, params, callback) {

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			callback();

		}
	};		
	if (post == "POST") {
		xhttp.open("POST", sendto, true);
		xhttp.setRequestHeader("Authorization", "abc");
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhttp.send(params);
	} else if (post == "GET") {
		xhttp.open("GET", sendto + "?" + params, true);
		xhttp.send();
	}
}
function wsSendMessage(msg) {

	if ((msg !== null) && (msg !== "")) {
		var object = '{"to":"' + window.user + '","text":"' + msg
				+ '","msg":"0","from":"' + id + '","fromname":"' + name + '"}';
		var span = document.createElement("span");
		span.textContent = msg;
		span.setAttribute('class', 'fromsub');
		var spanmain = document.createElement("span");
		spanmain.appendChild(span);
		spanmain.setAttribute('class', 'from');

		var today = new Date();
		// var time = today.getHours() + ":" + today.getMinutes() + ":"
		// + today.getSeconds();
		var time = today.toLocaleTimeString();
		var spantime = document.createElement("span");
		spantime.textContent = time;
		spantime.setAttribute('class', 'fromchattime');
		spanmain.appendChild(spantime);
		document.getElementById("secondtest").appendChild(spanmain);
		document.getElementById("secondtest").innerHTML += "<br>";
		webSocket.send(object);
		var element = document.getElementById("displaymsg");
		element.scrollTop = element.scrollHeight;
		textID.value = "";
	}

}
function wsGetMessage(message) {

	var a = message.data;

	var msg = JSON.parse(a);
	if (msg.group == "true") {
		if (window.user == msg.to) {
			var spanfrom = document.createElement("span");
			spanfrom.textContent = msg.fromname;
			spanfrom.setAttribute('class', 'touser');
			var span = document.createElement("span");
			span.textContent = msg.text;
			span.setAttribute('class', 'tosub');
			var spanmain = document.createElement("span");
			spanmain.appendChild(spanfrom);
			spanmain.appendChild(span);
			spanmain.setAttribute('class', 'to');
			var today = new Date();
			// var time = today.getHours() + ":" + today.getMinutes() + ":"
			// + today.getSeconds();
			var time = today.toLocaleTimeString();
			var spantime = document.createElement("span");
			spantime.setAttribute('class', 'tochattime');

			spantime.textContent = time;
			spanmain.appendChild(spantime);

			document.getElementById("secondtest").appendChild(spanmain);
			document.getElementById("secondtest").innerHTML += "<br>";
			var element = document.getElementById("displaymsg");
			element.scrollTop = element.scrollHeight;
		} else {
			function respons() {
				document.getElementById(msg.to).innerHTML = xhttp.responseText;
			}
			response("POST", "notif", "from=" + msg.to + "&to=" + id, respons);
		}
	} else {
		if ((window.user.localeCompare(msg.from)) == 0) {
			var span = document.createElement("span");
			span.textContent = msg.text;
			span.setAttribute('class', 'tosub');
			var spanmain = document.createElement("span");
			spanmain.appendChild(span);
			spanmain.setAttribute('class', 'to');
			var today = new Date();

			var time = today.toLocaleTimeString();
			var spantime = document.createElement("span");
			spantime.setAttribute('class', 'tochattime');

			spantime.textContent = time;
			spanmain.appendChild(spantime);

			document.getElementById("secondtest").appendChild(spanmain);
			document.getElementById("secondtest").innerHTML += "<br>";
			var element = document.getElementById("displaymsg");
			element.scrollTop = element.scrollHeight;
		} else {
			function respons() {
				document.getElementById(msg.from).innerHTML = xhttp.responseText;
			}
			response("POST", "notif", "from=" + msg.from + "&to=" + msg.to,
					respons);

		}
	}

}
function wsSendFriendRequest(status, to) {
	// var object = '{"to":"' + to + '","from":"' + id +
	// '","msg":"1","status":"'
	// + status + '"}';
	// webSocket.send(object);
	var a = "to=" + to + "&from=" + id + "&status=" + status;
	function respons() {
		location.reload();
	}
	response("POST", "friendrequesthandler", a, respons);
}

function showvalue(str, toname) {

	var a = "user=" + str + "&from=" + id + "&fromname=" + name + "&username="
			+ toname;

	function respons() {
		document.getElementById("usersa").innerHTML = xhttp.responseText;
	}
	response("GET", "displaydetails", a, respons);

}

function sendinfo(str, toname) {
	window.user = str;
	i=0;
	console.log(i);


	var a = "user=" + str + "&from=" + id + "&fromname=" + name + "&username="
			+ toname;
	function scrolldown() {
		document.getElementById("users").innerHTML = xhttp.responseText;
		var element = document.getElementById("displaymsg");
		if (element !== null) {
			element.scrollTop = element.scrollHeight;
		}
	}
	response("POST", "sendbox", a, scrolldown);
	document.getElementById(str).innerHTML = "";

}

function searchInfo() {
	var name = document.getElementById("searchbar").value;
	var senda = "value=" + name + "&from=" + id;
	function respons() {
		document.getElementById("displayall").innerHTML = xhttp.responseText;
	}
	if (name != "") {

		document.getElementById("displayall").style.display = "block";
		response("GET", "showalluser", senda, respons);
	} else {
		document.getElementById("displayall").style.display = "none";
	}

}
function showFriendRequest() {
	window.showfriendrequest = "0";

	var a = "from=" + id;
	function respons() {
		document.getElementById("displayfriendrequest").innerHTML = xhttp.responseText;
	}
	response("GET", "displayfriendrequest", a, respons);

}
function acceptrequest(num, to) {

	var a = "from=" + id + "&to=" + to + "&num=" + num;
	function respons() {
		location.reload();
	}

	response("POST", "acceptfriend", a, respons);

}
function sendclick(e) {

	if (e.keyCode === 13) {

		event.preventDefault();
		document.getElementById("sendu").click();

	}
}
var i = 0;
function scrollingfunction(userid, username) {
	console.log(i);
	var element = document.getElementById("displaymsg");
	if (element.scrollTop == 0) {
		i += 1;
		var a = "user=" + userid + "&from=" + id + "&no=" + (i * 20)
				+ "&username=" + username + "&fromname=" + name;

		function prepend() {
			var elementa = document.createElement("div");

			elementa.innerHTML = xhttp.responseText;
			var div = document.getElementById("secondtest");

			var divChildren = div.childNodes;

			var j = 0;
			while (j < divChildren.length) {

				elementa.appendChild(divChildren[j]);

			}
			document.getElementById("secondtest").innerHTML = "";
			var k = 0;
			while (k < elementa.childNodes.length) {
				document.getElementById("secondtest").appendChild(
						(elementa.childNodes)[k]);
			}
			document.getElementById(window.user).innerHTML = "";
			var element1 = document.getElementById("displaymsg");
			if (element1 != null && (xhttp.responseText.startsWith("<"))) {
				element1.scrollTop = element1.scrollHeight / (i + 1);
			}

		}
		response("GET", "sendbox", a, prepend);

	}

}
function createnewgroup() {
	var a = "from=" + id;
	function respons() {
		document.getElementById("usersa").innerHTML = xhttp.responseText;
	}
	response("POST", "createnewgroup.jsp", a, respons);

}
function newGroupUser(groupname, groupid) {
	var a = "from=" + id + "&fromname=" + name + "&groupname=" + groupname
			+ "&groupid=" + groupid;
	function respons() {
		document.getElementById("usersa").innerHTML = xhttp.responseText;
	}
	response("POST", "createnewgroup", a, respons);

}
function removefromgroup(fromid, groupid) {
	var a = "from=" + fromid + "&groupid=" + groupid;
	console.log(a);
	function respons()
	{
		location.reload();
	}
	response("POST", "removefromgroup", a,respons);

}
function changeadminstatus(str, fromid, groupid, clickid) {
	var a = "adminaccess=" + str + "&fromid=" + clickid + "&groupid=" + groupid;
	var access = "";
	if (str == "admin") {
		access = "user";
	} else {
		access = "admin"
	}
	function respons() {
		document.getElementsByName(clickid).item(0).innerHTML = access;
		document.getElementsByName(clickid).item(0).value = access;

	}
	response("POST", "changeadminstatus", a, respons);
}
function deletegroup(groupid) {
	var a = "groupid=" + groupid;
	function respons() {
		location.reload();
	}
	response("POST", "deletefromgroup", a, respons);
}
function logout() {
	var a = "";
	function respons() {
		location.reload();
	}
	response("POST", "logout", a, respons);
}
