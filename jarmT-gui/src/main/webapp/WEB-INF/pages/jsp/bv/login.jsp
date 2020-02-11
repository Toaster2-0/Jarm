<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container">
		<form method="post">
			<div class="form-group">
				<div class="col-sm-4">
					<h2>Login</h2>
					<label for="formGroupExampleInput">Email:</label> <input
						type="text" class="form-control" id="formGroupExampleInput"
						placeholder="E-Mail" name="Email" value="<c:out value="${emailValue}"/>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<label for="exampleInputPassword1">Passwort:</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Passwort" name="Passwort"  value="">
						</p>
					<button type="submit" class="btn btn-info">Login</button>
				</div>
			</div>
		</form>
		Du hast noch keinen Account? <a href="<c:url value='/bv/register.do'/>">Hier</a> registrieren!
	</div>
