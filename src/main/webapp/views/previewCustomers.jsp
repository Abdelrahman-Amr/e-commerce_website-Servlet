<%@taglib prefix="c" uri="jakarta.tags.core" %>
<table class="styled-table">
    <thead>
        <tr>
            <th>Email</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>credit Limit</th>
        </tr>
       
    </thead>
    <tbody id="tableBody">
        <c:forEach items="${customerList}" var="current">
            <tr class="active-row">
                <td>${current.email}</td>
                <td>${current.userName}</td>
                <td>${current.address}</td>
                <td>${current.phone}</td>
                <td>${current.creditLimit}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<nav class="page-buttons">
    <div class="page-item">
        <input type="button"  class="page-link" id="prevBTN" value="Previous" onclick="getPrevCustomerList()" disabled>
        <label id="pageNo">${pageNo} of ${pageNUM}</label>
        <input type="button" class="page-link" id="nextBTN" value="Next" onclick="getNextCustomerList(${pageNo})">
    </div>
</nav>

