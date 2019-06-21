<%--
  Created by IntelliJ IDEA.
  User: belka
  Date: 6/20/2019
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./static/css/site.css" />
    <title>Person Management</title>
</head>
<body>
<h4>Form #1
    choosePerson </h4>
<div class="border">
<form name="choosePerson" action="./person" method="post">
    <input type="hidden" name="formName" value="choosePerson" />

    <select name="selectPerson">
        <option value="1"> Bipin</option>
        <option value="2"> Sean</option>
        <option value="3"> Dan</option>
        <option value="4"> James</option>
    </select>

    <button type="submit"> Select Person</button>

</form>
</div>

<h4> Form # 2 updatePerson</h4>
<div class="border">
    <form name="updatePerson" action="./person" method="post">
        <input type="hidden" name="formName" value="updatePerson" />
        <input type="hidden" name="personId" value="0"/>

        <div>
            <input type="text" name="firstName" value="${firstName}" placeholder="First Name"/>
        </div>
        <div>
            <input type="text" name="middleName" value="${middleName}" placeholder="Middle Name"/>
        </div>
        <div>
            <input type="text" name="lastName" value="${lastName}" placeholder="Last Name"/>
        </div>
        <div>
            <input type="text" name="birthDate" value="${birthDate}" placeholder="Birth Date"/>
        </div>
        <div>
            <input type="text" name="socialSecurityNumber" value="${socialSecurityNumber}" placeholder="SSN"/>
        </div>

        <button type="submit"> Update Person </button>

    </form>

</div>

</body>
</html>
