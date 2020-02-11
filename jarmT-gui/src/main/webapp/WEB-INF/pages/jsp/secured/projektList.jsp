<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	
	<div class="col-sm-5">
		<div id="zentriert" class="border border-white">
			<h2>
				<b>Hallo <c:out value="${user.name}" /> 
				</b>
			</h2>
			<br />
			<h5>Deine Projekte:</h5>
			<br />
		</div>
		<ul class="list-group">
			<c:forEach var="project" items="${myProjects}">
				<li 
					class=" list list-group-item d-flex justify-content-between align-items-center"><a href="<c:url value='/projects/project.do?projectId='/><c:out value="${project.id }"/>">
					<c:out value="${project.title}" /></a> <span
					class="badge badge-primary badge-pill"><c:out
							value="${project.toDos.size()}" /></span>
				</li>
			</c:forEach>
		</ul>
		<br />
			<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#staticBackdrop">Neues Projekt</button>
				<form method="POST" action="<c:url value='/projects/create.do'/>">
					<div class="form-group">	
						<div class="modal fade" id="staticBackdrop" data-backdrop="static"
							tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="staticBackdropLabel">Neues
											Projekt</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<p>
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text" id="inputGroup-sizing-default">Titel</span>
											</div>
											<input type="text" class="form-control"
												aria-label="Sizing example input"
												aria-describedby="inputGroup-sizing-default" name="ProjectTitle">
										</div>
										User hinzufügen (optional):
										<div class="input-group md-form form-sm form-1 pl-0">
											<div class="input-group-prepend">
												<span class="input-group-text cyan lighten-2" id="basic-text1"><i
													class="fas fa-search text-white" aria-hidden="true"></i></span>
											</div>
											<input class="form-control my-0 py-1" type="text"
												placeholder="Search" aria-label="Search">
										</div>
									</div>
									<div class="modal-footer">
										<button type="submit"  class="btn btn-info">Speichern</button>
										<button type="button" class="btn btn-light" data-dismiss="modal">Abbrechen</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
	</div>
</div>
