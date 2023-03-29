<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!--
<div class="register">
	<div class="container">
		<div class="login-form-grids">

            <form method="post" onsubmit="FilterCustomerOrders(event)">
                <input type="email" placeholder="Email*" required id="customerEmailOrder"
                        pattern="^([\w\d_\-]{3,})@([a-zA-Z]+(\.[a-zA-z]+)+)$" onblur="getAllOrders()">
                <input type="submit" value="Filter" >
            </form>
        </div>
    </div>
</div>
-->
<table class="styled-table">
    <thead>
        <tr>
            <th>Order Id</th>
            <th>Customer Email</th>
            <th>Address</th>
            <th>Date</th>
            <th>Total</th>

        </tr>
       
    </thead>
    <tbody id="masterOrderTableBody" class="masterOrderTableBody">
        <c:forEach items="${orderList}" var="current">
            <tr class="active-row" onclick="location.href='PreviewOrderDetails?orderId=${current.id}'" >
                <td>${current.id}</td>
                <td>${current.cust.email}</td>
                <td>${current.cust.address}</td>
                <td>${current.date}</td>
                <td>${current.total}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<!--
<c:forEach items="${orderList}" var="current">
  <tr class="active-row" onclick="location.href='PreviewOrderDetails?orderId=${current.id}'">
    <td><a href="PreviewOrderDetails?orderId=${current.id}">${current.id}</a></td>
    <td><a href="PreviewOrderDetails?orderId=${current.id}">${current.cust.email}</a></td>
    <td><a href="PreviewOrderDetails?orderId=${current.id}">${current.cust.address}</a></td>
    <td><a href="PreviewOrderDetails?orderId=${current.id}">${current.date}</a></td>
    <td><a href="PreviewOrderDetails?orderId=${current.id}">${current.total}</a></td>
  </tr>
</c:forEach>

-->
<nav class="page-buttons">
    <div class="page-item">
        <input type="button"  class="page-link" id="prevOrderBTN" value="<" onclick="getPrevOrderList()" disabled>
        <label id="orderPageNo">${pageOrderNo} of ${totalOrderPages}</label>
        <input type="button" class="page-link" id="nextOrderBTN" value=">" onclick="getNextOrderList()">
    </div>
</nav>

