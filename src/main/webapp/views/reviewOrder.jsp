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
<h2 class="head" style="margin-bottom: 40px;">Orders</h2>

<table class="styled-table">
    <thead>
        <tr>
            <th>Order Id</th>
            <th>Customer Email</th>
            <th>Date</th>
            <th>Total</th>
            <th>Order Details</th>
        </tr>
       
    </thead>
    <tbody id="masterOrderTableBody">
        <c:forEach items="${orderList}" var="current">
            <tr class="active-row">
                <td>${current.id}</td>
                <td>${current.cust.email}</td>
                <td>${current.date}</td>
                <td>${current.total}</td>
                <td><input type="button" value="Order Details" onclick="location.href='PreviewOrderDetails?orderId=${current.id}'"\></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<nav class="page-buttons">
    <div class="page-item">
        <input type="button"  class="page-link" id="prevOrderBTN" value="<" onclick="getPrevOrderList()" disabled>
        <label id="orderPageNo">${pageOrderNo} of ${totalOrderPages}</label>
        <input type="button" class="page-link" id="nextOrderBTN" value=">" onclick="getNextOrderList()">
    </div>
</nav>

