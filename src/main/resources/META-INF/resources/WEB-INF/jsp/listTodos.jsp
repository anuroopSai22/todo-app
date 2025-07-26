<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

    <div class="container">
        <h2>The todo tasks are:</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                        <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                        <td><a href="update-todo?id=${todo.id}" class = "btn btn-success">Update</a>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/add-Todo" class="btn btn-success">Add Todo</a>
    </div>
<%@ include file="common/footer.jspf" %>
