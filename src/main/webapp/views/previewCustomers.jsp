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
<nav class="pagination pagination-circle">
    <div class="page-item">
        <input type="button"  class="page-link" value="Previous" onclick="getPrevCustomerList()">
        <label class="page-link" id="pageNo">${pageNo}</label>
        <input type="button" class="page-link" value="Next" onclick="getNextCustomerList(${pageNo})">
    </div>
</nav>
<nav aria-label="..." class="page_bar">
    <ul class="pagination pagination-circle">
      <li class="page-item">
        <a class="page-link">Previous</a>
      </li>
      <!--<li class="page-item"><a class="page-link" href="#">1</a></li>-->
      <li class="page-item active" aria-current="page" id="pageNumber">
        <a class="page-link" href="#">1 <span class="visually-hidden">(current)</span></a>
      </li>
      <!--<li class="page-item"><a class="page-link" href="#">3</a></li>-->
      <li class="page-item">
        <a class="page-link" href="#">Next</a>
      </li>
    </ul>
  </nav>
