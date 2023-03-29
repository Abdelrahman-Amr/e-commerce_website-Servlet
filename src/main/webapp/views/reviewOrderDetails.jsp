<%@taglib prefix="c" uri="jakarta.tags.core" %>
<table class="styled-table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Category</th>
            <th>Size</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
       
    </thead>
    <tbody id="detailOrderTableBody">
        <c:forEach items="${orderDetailList}" var="current">
            <tr class="active-row">
                <td>${current.product.name}</td>
                <td>${current.product.catg.name}</td>
                <td>${current.size}</td>
                <td>${current.quantity}</td>
                <td>${current.price}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<nav class="page-buttons">
    <div class="page-item">
        <input type="button"  class="page-link" id="prevOrderDetailBTN" value="<" onclick="getPrevOrderDetailsList()" disabled>
        <label id="pageNoOrderDetail">${pageOrderDetailNo} of ${totalOrderDetailPages}</label>
        <input type="button" class="page-link" id="nextOrderDetailBTN" value=">" onclick="getNextOrderDetailsList()" >
    </div>
</nav>

