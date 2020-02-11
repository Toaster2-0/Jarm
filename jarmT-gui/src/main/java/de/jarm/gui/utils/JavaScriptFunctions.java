package de.jarm.gui.utils;

public class JavaScriptFunctions {
	
	public static final String FIND_USER_FOR_TODO = "var elementsAddedToTodo = [];\r\n" + 
			"function addUserToTodo(element){\r\n" + 
			"    \r\n" + 
			"    $(\"#todo-users-added\").append(\"<div id='remove-value-todo-\" + element.value + \"'>\" + $(element).html() + \" <button type='button' id='remove-user-todo-button-\" + element.value + \"' value='\" + element.value + \"'>x</button></div>\");\r\n" + 
			"	$(\"#remove-user-todo-button-\" + element.value).click(function(){\r\n" + 
			"        removeAddedTodoUser(this);\r\n" + 
			"    });\r\n" + 
			"    $('#query-input-todo').val(\"\");\r\n" + 
			"    $('#query-output-todo').empty();\r\n" + 
			"    elementsAddedToTodo.push(element.value);\r\n" + 
			"    $(\"#added-user-ids-todo\").attr(\"value\", $(\"#added-user-ids-todo\").val() + \" \" + element.value + \" \");\r\n" + 
			"}\r\n" + 
			"function closeModalTodo(){\r\n" + 
			"    elementsAddedToTodo.splice(0,elementsAddedToTodo.length);\r\n" + 
			"    $(\"#todo-users-added\").empty();\r\n" + 
			"    $(\"#added-user-ids-todo\").attr(\"value\", \"\");\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function removeAddedTodoUser(element){\r\n" + 
			"    \r\n" + 
			"    var index = elementsAddedToTodo.indexOf(element.value);\r\n" + 
			"    if (index > -1) {\r\n" + 
			"      elementsAddedToTodo.splice(index, 1);\r\n" + 
			"    }\r\n" + 
			"    var oldValue = $(\"#added-user-ids-todo\").val();\r\n" + 
			"    oldValue = oldValue.replace(\" \" + element.value + \" \", \"\");\r\n" + 
			"    $(\"#added-user-ids-todo\").attr(\"value\", oldValue);\r\n" + 
			"    $( \"#remove-value-todo-\" + element.value).remove();\r\n" + 
			"}\r\n" + 
			"    \r\n" + 
			"function findUserForTodo(){\r\n" + 
			"    \r\n" + 
			"    var queryString = $('#query-input-todo').val();\r\n" + 
			"    var projectId = $('#current-project-id').html();\r\n" + 
			"    \r\n" + 
			"    if (queryString.length >= 3){\r\n" + 
			"        $.ajax({\r\n" + 
			"            url: \"ajax.findUser\",\r\n" + 
			"            data: {\r\n" + 
			"                userName : queryString,\r\n" + 
			"                projectId : projectId\r\n" + 
			"            },\r\n" + 
			"            success: function(result){\r\n" + 
			"                \r\n" + 
			"                $('#query-output-todo').empty();\r\n" + 
			"                \r\n" + 
			"                var users = result.split(\"<:>\");\r\n" + 
			"                \r\n" + 
			"                users.forEach(function(item){\r\n" + 
			"                    \r\n" + 
			"                    if (item != \"\"){\r\n" + 
			"                        var user = item.split(\"<&>\");\r\n" + 
			"                    \r\n" + 
			"                        var userId = user[0];\r\n" + 
			"                        \r\n" + 
			"                        if (!elementsAddedToTodo.includes(userId)){\r\n" + 
			"                            var userEmail = user[1];\r\n" + 
			"                            var userName = user[2];\r\n" + 
			"                        \r\n" + 
			"                            $('#query-output-todo').append(\"<div><button style='border:none;' class='dropdown-content-item' id='todoU\" + userId + \"' type='button' value='\" + userId + \"'>\" + userName + \" - \" + userEmail + \"</button></div>\");\r\n" + 
			"                            $('#todoU' + userId).click(function(){\r\n" + 
			"                                addUserToTodo(this);\r\n" + 
			"                            });\r\n" + 
			"                        }\r\n" + 
			"                    }                                \r\n" + 
			"                });    \r\n" + 
			"            }\r\n" + 
			"        });\r\n" + 
			"    } else {\r\n" + 
			"        $('#query-output-todo').empty();\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"$('#query-input-todo').keyup(findUserForTodo);\r\n" + 
			"$('.close-modal-window-todo').click(closeModalTodo);";
	
	public static final String FIND_USER_FOR_PROEJCT = "var elementsAddedToProject = [];\r\n" + 
			"var alreadyAddedToProject = [];\r\n" + 
			"$(\".current-project-subscribers\").each(function(){\r\n" + 
			"    alreadyAddedToProject.push($(this).html());\r\n" + 
			"});\r\n" + 
			"function addUserToProject(element){\r\n" + 
			"    \r\n" + 
			"    $(\"#project-users-added\").append(\"<div>\" + $(element).html() + \"</div>\");\r\n" + 
			"    $('#query-input-user').val(\"\");\r\n" + 
			"    $('#query-output-user').empty();\r\n" + 
			"    elementsAddedToProject.push(element.value);\r\n" + 
			"	$(\"#added-user-ids-project\").attr(\"value\", $(\"#added-user-ids-project\").val() + \" \" + element.value);\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function closeModalProject(){\r\n" + 
			"	elementsAddedToProject.splice(0,elementsAddedToProject.length);\r\n" + 
			"	$(\"#project-users-added\").empty();\r\n" + 
			"	$(\"#added-user-ids-project\").attr(\"value\", \"\");\r\n" + 
			"}\r\n" + 
			"    \r\n" + 
			"function findUserForProject(){\r\n" + 
			"    \r\n" + 
			"    var queryString = $('#query-input-user').val();\r\n" + 
			"    \r\n" + 
			"    if (queryString.length >= 3){\r\n" + 
			"        $.ajax({\r\n" + 
			"            url: \"ajax.findUser\",\r\n" + 
			"            data: {\r\n" + 
			"                userName : queryString\r\n" + 
			"            },\r\n" + 
			"            success: function(result){\r\n" + 
			"                \r\n" + 
			"                $('#query-output-user').empty();\r\n" + 
			"                \r\n" + 
			"                var users = result.split(\"<:>\");\r\n" + 
			"                \r\n" + 
			"                users.forEach(function(item){\r\n" + 
			"                    \r\n" + 
			"                    if (item != \"\"){\r\n" + 
			"                        var user = item.split(\"<&>\");\r\n" + 
			"                    \r\n" + 
			"                        var userId = user[0];\r\n" + 
			"                        \r\n" + 
			"                        if (!elementsAddedToProject.includes(userId) && !alreadyAddedToProject.includes(userId)){\r\n" + 
			"                            var userEmail = user[1];\r\n" + 
			"                            var userName = user[2];\r\n" + 
			"                        \r\n" + 
			"                            $('#query-output-user').append(\"<div><button style='border:none;' class='dropdown-content-item' id='proj\" + userId + \"' type='button' value='\" + userId + \"'>\" + userName + \" - \" + userEmail + \"</button></div>\");\r\n" + 
			"                            $('#proj' + userId).click(function(){\r\n" + 
			"                                addUserToProject(this);\r\n" + 
			"                            });\r\n" + 
			"                        }\r\n" + 
			"                    }                                \r\n" + 
			"                });    \r\n" + 
			"            }\r\n" + 
			"        });\r\n" + 
			"    } else {\r\n" + 
			"        $('#query-output-user').empty();\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"$('#query-input-user').keyup(findUserForProject);\r\n" + 
			"$('.close-modal-window-project').click(closeModalProject);";
	
	public static final String EDIT_TODO = 
			"var elementsAddedToEditTodo = []; \r\n" + 
			"var userObjectsAddedToEditTodo = [];\r\n" + 
			"\r\n" + 
			"function closeModalEditTodo(){\r\n" + 
			"    elementsAddedToEditTodo.splice(0,elementsAddedToEditTodo.length);\r\n" + 
			"    $(\"#edit-todo-users-added\").empty();\r\n" + 
			"    $(\"#added-user-ids-edit-todo\").attr(\"value\", \"\");\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function addUserToEditTodo(element){\r\n" + 
			"    \r\n" + 
			"    $(\"#edit-todo-users-added\").append(\"<div id='remove-value-\" + element.value + \"'>\" + $(element).html() + \" <button type='button' id='remove-user-button-\" + element.value + \"' value='\" + element.value + \"'>x</button></div>\");\r\n" + 
			"	$(\"#remove-user-button-\" + element.value).click(function(){\r\n" + 
			"		removeAddedUser(this);\r\n" + 
			"	});\r\n" + 
			"    $('#query-input-edit-todo').val(\"\");\r\n" + 
			"    $('#query-output-edit-todo').empty();\r\n" + 
			"    elementsAddedToEditTodo.push(element.value);\r\n" + 
			"    $(\"#added-user-ids-edit-todo\").attr(\"value\", $(\"#added-user-ids-edit-todo\").val() + \" \" + element.value + \" \");\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function removeAddedUser(element){\r\n" + 
			"	\r\n" + 
			"	var index = elementsAddedToEditTodo.indexOf(element.value);\r\n" + 
			"	if (index > -1) {\r\n" + 
			"	  elementsAddedToEditTodo.splice(index, 1);\r\n" + 
			"	}\r\n" + 
			"	var oldValue = $(\"#added-user-ids-edit-todo\").val();\r\n" + 
			"	oldValue = oldValue.replace(\" \" + element.value + \" \", \"\");\r\n" + 
			"	$(\"#added-user-ids-edit-todo\").attr(\"value\", oldValue);\r\n" + 
			"	$( \"#remove-value-\" + element.value).remove();\r\n" + 
			"}\r\n" + 
			" \r\n" + 
			"function fetchDataForEditTodo(){\r\n" + 
			"	\r\n" + 
			"	var todoId = $(this).data(\"todo-id\");\r\n" + 
			"	var projectId = $('#current-project-id').html();\r\n" + 
			"	$(\"#edit-todo-users-added\").empty();\r\n" + 
			"	elementsAddedToEditTodo.splice(0,elementsAddedToEditTodo.length);\r\n" + 
			"	userObjectsAddedToEditTodo.splice(0,userObjectsAddedToEditTodo.length);\r\n" + 
			"	\r\n" + 
			"	$.ajax({\r\n" + 
			"		url: \"ajax.findTodo\",\r\n" + 
			"		data: {\r\n" + 
			"			todoId : todoId,\r\n" + 
			"			projectId : projectId\r\n" + 
			"		},\r\n" + 
			"		success: function(result){\r\n" + 
			"			\r\n" + 
			"			var results = result.split(\"<u>\");\r\n" + 
			"			var todoInfos = results[0];\r\n" + 
			"			var userInfos = results[1];\r\n" + 
			"			\r\n" + 
			"			var todoInfoElements = todoInfos.split(\"<&>\");\r\n" + 
			"			var userInfoElements = userInfos.split(\"<n>\");\r\n" + 
			"			\r\n" + 
			"			var todoInfoId = todoInfoElements[0];\r\n" + 
			"			var todoInfoName = todoInfoElements[1];\r\n" + 
			"			var todoInfoDescription = todoInfoElements[2];\r\n" + 
			"			var todoInfoState = todoInfoElements[3];\r\n" + 
			"			\r\n" + 
			"			userInfoElements.forEach(function(item){\r\n" + 
			"				\r\n" + 
			"				if (item != \"\"){\r\n" + 
			"					\r\n" + 
			"					var user = item.split(\"<&>\");\r\n" + 
			"					var userId = user[0];\r\n" + 
			"					var userEmail = user[1];\r\n" + 
			"					var userName = user[2];\r\n" + 
			"					\r\n" + 
			"					elementsAddedToEditTodo.push(userId);\r\n" + 
			"\r\n" + 
			"					userObjectsAddedToEditTodo.push(userEmail + \" - \" + userName);\r\n" + 
			"					$(\"#edit-todo-users-added\").append(\"<div id='remove-value-\" + userId + \"'>\" + userName + \" - \" + userEmail + \" <button type='button' id='remove-user-button-\" + userId + \"' value='\" + userId + \"'>x</button></div>\");\r\n" + 
			"					$(\"#remove-user-button-\" + userId).click(function(){\r\n" + 
			"						removeAddedUser(this);\r\n" + 
			"					});\r\n" + 
			"					$(\"#added-user-ids-edit-todo\").attr(\"value\", $(\"#added-user-ids-edit-todo\").val() + \" \" + userId + \" \");\r\n" + 
			"				}\r\n" + 
			"				\r\n" + 
			"			});\r\n" + 
			"			\r\n" + 
			"			$(\"#title-textfield\").val(todoInfoName);\r\n" + 
			"			$(\"#description-textfield\").val(todoInfoDescription);\r\n" + 
			"			$(\"#inputGroupSelect01\").val(todoInfoState);\r\n" + 
			"			$(\"#todo-id-input\").val(todoInfoId);\r\n" + 
			"			\r\n" + 
			"		},\r\n" + 
			"		error: function(a,b){\r\n" + 
			"			$(\"#title-textfield\").val(\"\");\r\n" + 
			"			$(\"#description-textfield\").val(\"\");\r\n" + 
			"			$(\"#inputGroupSelect01\").val(\"\");\r\n" + 
			"		}\r\n" + 
			"	});	\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function findUserForEditTodo(){\r\n" + 
			"    \r\n" + 
			"    var queryString = $('#query-input-edit-todo').val();\r\n" + 
			"    var projectId = $('#current-project-id').html();\r\n" + 
			"    \r\n" + 
			"    if (queryString.length >= 3){\r\n" + 
			"        $.ajax({\r\n" + 
			"            url: \"ajax.findUser\",\r\n" + 
			"            data: {\r\n" + 
			"                userName : queryString,\r\n" + 
			"                projectId : projectId\r\n" + 
			"            },\r\n" + 
			"            success: function(result){\r\n" + 
			"                \r\n" + 
			"                $('#query-output-edit-todo').empty();\r\n" + 
			"                \r\n" + 
			"                var users = result.split(\"<:>\");\r\n" + 
			"                \r\n" + 
			"                users.forEach(function(item){\r\n" + 
			"                    \r\n" + 
			"                    if (item != \"\"){\r\n" + 
			"                        var user = item.split(\"<&>\");\r\n" + 
			"                    \r\n" + 
			"                        var userId = user[0];\r\n" + 
			"                        \r\n" + 
			"                        if (!elementsAddedToEditTodo.includes(userId)){\r\n" + 
			"                            var userEmail = user[1];\r\n" + 
			"                            var userName = user[2];\r\n" + 
			"                        \r\n" + 
			"                            $('#query-output-edit-todo').append(\"<div><button style='border:none;' class='dropdown-content-item' id='edittodoU\" + userId + \"' type='button' value='\" + userId + \"'>\" + userName + \" - \" + userEmail + \"</button></div>\");\r\n" + 
			"                            $('#edittodoU' + userId).click(function(){\r\n" + 
			"                                addUserToEditTodo(this);\r\n" + 
			"                            });\r\n" + 
			"                        }\r\n" + 
			"                    }                                \r\n" + 
			"                });    \r\n" + 
			"            }\r\n" + 
			"        });\r\n" + 
			"    } else {\r\n" + 
			"        $('#query-output-edit-todo').empty();\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"$(\".edit-todo\").click(fetchDataForEditTodo);\r\n" + 
			"$('#query-input-edit-todo').keyup(findUserForEditTodo);\r\n" + 
			"$('.close-modal-window-edit-todo').click(closeModalEditTodo);";
	
}
