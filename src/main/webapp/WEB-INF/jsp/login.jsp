<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<h4>       Authorize with CURL or POSTMAN     </h4>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <br>
    <hr style="margin-left: 20px">
    <p>Design and implementation a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.<br>
        Tests with: <hr color="blue">restaurant_voting.postman_collection.json file.</hr>></p>

    <img src=""><img src="resources/images/UML Restaurant_voting.png">

    <h4>The task is:</h4>
    <p>Build a voting system for deciding where to have lunch.</p>
    <p>2 types of users: admin and regular users<br>
        Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)<br>
        Menu changes each day (admins do the updates)<br>
        Users can vote on which restaurant they want to have lunch at<br>
        Only one vote counted per user<br>
        If user votes again the same day:<br>
        If it is before 11:00 we assume that he changed his mind.<br>
        If it is after 11:00 then it is too late, vote can't be changed<br>
        Each restaurant provides new menu each day.
    </p>
    <p>As a result, provide a link to github repository.</p>
    <p>It contain the code and README.md with API documentation and curl commands to get data for voting and vote.
    </p>
    <p>Example JSON out: <a href="http://localhost:8080/restaurant_voting/rest/profile/text"> Method GET: test REST-Controller's </a></p>

    <p>*to Google Chrome must be install - JSON plugin. API is testing with CURL methods (curl.md) or POSTMAN.exe</p>
    <br><br>

    <div>
        <div>
            <sec:authorize access="isAuthenticated()">
            </sec:authorize>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>