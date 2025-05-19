<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Google Login #1</title>
		<script src="https://accounts.google.com/gsi/client" async></script>
		<script src="https://cdn.jsdelivr.net/npm/jwt-decode@latest/build/jwt-decode.min.js"></script>
	    <script>
	        function handleCredentialResponse(response) {
	    		var responsePayload = jwt_decode(response.credential);
	    		console.log(responsePayload);	
	    		console.log("ID : " + responsePayload.sub);	
	    		console.log("Full name : " + responsePayload.name);	
	    		console.log("Given name : " + responsePayload.given_name);	
	    		console.log("Image URL : " + responsePayload.picture);	
	    		console.log("Email : " + responsePayload.email);	
	        }
	      	window.onload = function () {
	        google.accounts.id.initialize({
	          client_id: "629630871232-msb96lcatpevo09uj9s8iec0ro10iec7.apps.googleusercontent.com",
	          callback: handleCredentialResponse
	        });
	        google.accounts.id.renderButton(
	          document.getElementById("buttonDiv"),
	          { theme: "outline", size: "large" }  // customization attributes
	        );
	        google.accounts.id.prompt(); // also display the One Tap dialog
	    }
	    </script>
	</head>
	<body>
	    <div id="buttonDiv"></div>
	</body>
</html>