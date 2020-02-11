<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 style = "padding: 5px; background-color: #c0c0c0;">Projektinterne Nachrichten
<i class="far fa-comments"></i></h1>
     <div style = "padding: 5px; background-color: #eefafd;height: 300px; overflow:scroll;">
     	<table  style="border-collapse:collapse;">
     		<c:forEach var="thisMessage" items="${nachrichten}">
     			<tr style="border-bottom: 1px solid #c0c0c0; "><td style="min-width: 120px" valign="middle"><h5><c:out value="${thisMessage.author.name}: " /></h5></td>
     			<td valign="middle"><c:out value="${thisMessage.message}" /></td>
     			<td valign="middle" style="font-size: x-small; font-style: italic;"><font color= "#c0c0c0"><c:out value="${thisMessage.creationDate}"></c:out></font></td></tr>
			</c:forEach>
     	</table>
     </div>
	 <div style = "padding: 5px; background-color: #c0c0c0;"> 
		 <form action="<c:url value='/projects/addMessage.do'/>" method="post">
		 		<input type="hidden" name="id" value="<c:out value='${currentProject.id}'/>" />
				<table style= "margin-left: 10px; margin-right: 10px; margin-bottom: 5px;">
					<tr>
						<td style="width:99%">
							<input type = "text" name = "message" placeholder="Eine Nachricht schreiben..." style="width:100%">
						</td>
						<td>
							<input type = "submit" value = "Senden">
						</td>
					</tr>
				</table>
		 </form>
	 </div>
	 
