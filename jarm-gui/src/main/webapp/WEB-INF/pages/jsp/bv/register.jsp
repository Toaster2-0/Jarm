<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container">
		</p>
		<form method="post">
			<div class="form-group">
				<div class="col-sm-5">
					<h2>
						Registrier dich bei Jarm
						<ion-icon name="contacts"></ion-icon>
					</h2>
					</p>
					<label for="formGroupExampleInput">Benutzername:</label> <input
						type="text" class="form-control" id="formGroupExampleInput"
						placeholder="Benutzername" name="Name" value="<c:out value="${nameValue}"/>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-5">
					<label for="exampleInputEmail1">E-Mail:</label> <input type="email"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="E-Mail"
						 name="Email" 
						value="<c:out value="${emailValue}"/>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-5">
					<label for="exampleInputPassword1">Passwort:</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						name="Passwort" 
						placeholder="Passwort" value="">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-5">
					<label for="exampleInputPassword1">Passwort wiederholen:</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						name="Passwortwiederholung"
						placeholder="Passwort wiederholen" value="">
					</p>
					<button type="submit" class="btn btn-info">Registrieren</button>
				</div>
			</div>
		</form>
		Du hast schon einen Account? <a href="<c:url value='/bv/login.do'/>">Hier</a> einloggen!
	</div>