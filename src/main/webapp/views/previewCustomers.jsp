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
    <tbody id="customerTableBody">
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
        <input type="button"  class="page-link" id="prevCustomerBTN" value="<" onclick="getPrevCustomerList()" disabled>
        <label id="customerPageNo">${CustomerPageNo} of ${customerTotalPage}</label>
        <input type="button" class="page-link" id="nextCustomerBTN" value=">" onclick="getNextCustomerList('${pageNo}')">
    </div>
</nav>

