<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">

	<br />
	<div class="form-group">
		<div class="col-sm-7">
			<div class="border2">

				<h3>
					<c:out value="${currentProject.title}" />
				</h3>

				<br />
				<h5>
					Mitglieder:
					<c:out value="${currentProject.owner.name}" />(owner) <span class="current-project-subscribers" style="visibility: hidden;"><c:out value="${currentProject.owner.id}"/></span>
					<c:forEach var="projectUser" items="${currentProject.subscribers}">
						<c:out value="${projectUser.name}" />
						<a href="<c:url value='/projects/removeUserFromProject.do?projectId='/><c:out value="${currentProject.id}" />&userId=<c:out value="${projectUser.id}" />"><i class="fas fa-user-minus fa-xs darkblue"></i></a> <span class="current-project-subscribers" style="visibility: hidden;"><c:out value="${projectUser.id}"/></span></c:forEach>
				</h5>
				<br />
				<c:if test="${user.id == currentProject.owner.id}">
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#exampleModal">Mitglied hinzufügen</button></c:if>
					
					
				<form method="post" action="<c:url value='/projects/addUserToProject.do'/>">	
					<input type="hidden" name="id" value="<c:out value="${currentProject.id}"/>" />
					<div class="modal fade" id="exampleModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Neues
										Mitglied</h5>
									<button type="button" class="close close-modal-window-project" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<br/>
									<input type="hidden" value="" name="AddedUserIdsProject" id="added-user-ids-project" />
									<div id="project-users-added">
										
									</div>
									<br/>
									<div class="input-group md-form form-sm form-1 pl-0">
										<div class="input-group-prepend">
											<span class="input-group-text cyan lighten-2" id="basic-text1"><i
												class="fas fa-search text-white" aria-hidden="true"></i></span>
										</div>
										<input class="form-control my-0 py-1" type="text" id="query-input-user"
											placeholder="Search" aria-label="Search" autocomplete="off">
									</div>
									<div id="query-output-user" class="dropdown-content">		
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-info">Hinzufügen</button>
									<button type="button" class="btn btn-light close-modal-window-project" data-dismiss="modal">Abbrechen</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-7">
			<h4>
				<br /> Deine To-Dos:
			</h4>

			<ul class="list-group">
				<c:forEach var="todo" items="${currentProject.toDos}">
					<li class="list list-group-item"><h6><c:out value="${todo.name}" /></h6>
						
						<c:forEach var="instructedUser" items="${todo.instructedUsers }">
							<span>
								<c:out value="${instructedUser.name }"></c:out>, 
							</span>
						</c:forEach>
						<div class="float-right">
							<a data-toggle="modal" data-target="#myModal" data-todo-id="<c:out value="${todo.id}" />" class="button pointer edit-todo"><i class="fas fa-edit orange"></i></a> 
							<c:if test="${todo.state == 2}"> <i class="fas fa-check-circle green"></i> </c:if>
						 	<c:if test="${todo.state == 1}"> <i class="fas fa-hourglass-start yellow"></i> </c:if>
						 	<c:if test="${todo.state == 0}"> <i class="fas fa-question darkblue"></i> </c:if>	 							
							<a href="<c:url value='/projects/deleteTodo.do?todoId='/><c:out value="${todo.id}" />&projectId=<c:out value="${currentProject.id}" />"><i class="fas fa-trash-alt grey"></i></a>
							
						</div>
					</li>
				</c:forEach>
			</ul>
			<br />
			
			
			<button type="button" class="btn btn-info" data-toggle="modal"
				data-target="#staticBackdrop">Neues To-Do</button>
				
				
		<form method="post" action="<c:url value='/projects/addTodo.do'/>">
			<input type="hidden" name="id" value="<c:out value="${currentProject.id}"/>" />
			<div class="modal fade" id="staticBackdrop" data-backdrop="static"
				tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Neues To-Do</h5>
							<button type="button" class="close close-modal-window-todo" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Titel</span>
								</div>
								<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="Titel">
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Info</span>
								</div>
								<textarea class="form-control" aria-label="With textarea" name="Description"></textarea>
							</div>
							<br/>
							<div id="todo-users-added"></div>
							<input type="hidden" value="" name="AddedUserIdsTodo" id="added-user-ids-todo" />
							<br/>
							<br /> User zuweisen:
							<div class="input-group md-form form-sm form-1 pl-0">
								<div class="input-group-prepend">
									<span class="input-group-text cyan lighten-2" id="basic-text1">
										<i class="fas fa-search text-white" aria-hidden="true"></i>
									</span>	
								</div>
								
								<input id="query-input-todo" class="form-control my-0 py-1" type="text" placeholder="Search" aria-label="Search" autocomplete="off">
								
							</div>
							<div id="query-output-todo" class="dropdown-content">		
							</div>	
							
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-info">Speichern</button>
							<button type="button" class="btn btn-light close-modal-window-todo" data-dismiss="modal">Abbrechen</button>
						</div>
					</div>
				</div>
			</div>
			</form>

			
			<form method="post" action="<c:url value='/projects/editTodo.do'/>">
				<input type="hidden" name="id" value="<c:out value="${currentProject.id}"/>" />
				<input id="todo-id-input" type="hidden" name="todoId" value="" />
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">To-Do bearbeiten</h5>
								<button type="button" class="close close-modal-window-edit-todo" data-dismiss="modal"
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
									<input id="title-textfield" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="todoTitle">
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Info</span>
									</div>
									<textarea id="description-textfield" class="form-control" aria-label="With textarea" name="todoDescription" ></textarea>
								</div>
								<br />
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<label class="input-group-text" for="inputGroupSelect01">Status</label>
									</div>
									<select class="custom-select" id="inputGroupSelect01" name="todoState">
										<option selected value="0">Unbearbeitet</option>
										<option value="1">In Bearbeitung</option>
										<option value="2">Fertig</option>
									</select>
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
									</div>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
										<label class="custom-file-label" for="inputGroupFile01"></label>
									</div>
								</div><br />
								<div id="edit-todo-users-added"></div>
								<input type="hidden" value="" name="AddedUserIdsEditTodo" id="added-user-ids-edit-todo" />
								<br /> User zuweisen:
								<div class="input-group md-form form-sm form-1 pl-0">
									<div class="input-group-prepend">
										<span class="input-group-text cyan lighten-2" id="basic-text1"><i
											class="fas fa-search text-white" aria-hidden="true"></i></span>
									</div>
									<input class="form-control my-0 py-1" type="text"  id="query-input-edit-todo"
										placeholder="Search" aria-label="Search" autocomplete="off">
								</div>
								<div id="query-output-edit-todo" class="dropdown-content">		
								</div>	
								
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-info">Speichern</button>
								<button type="button" class="btn btn-light close-modal-window-edit-todo" data-dismiss="modal">Abbrechen</button>
							</div>
						</div>
					</div>
				</div>
			</form>



		</div>
	</div>
</div>
<span id="current-project-id" style="visibility: hidden;"><c:out value="${currentProject.id}"/></span>