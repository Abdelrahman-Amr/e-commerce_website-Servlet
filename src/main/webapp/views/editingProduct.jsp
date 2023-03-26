<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!-- register -->
<div class="register">
	<div class="container">
		<h2>Editing Product</h2>
		<div class="login-form-grids">
			<!-- <h5>profile information</h5> -->
			<form method="post" onsubmit="editProductAdmin(event)" enctype="multipart/form-data">
				<input type="text" placeholder="Name*" required minlength="3" maxlength="30"  id="productName" name="productName" value="${currentProduct.name}">
<!--				<input type="text" placeholder="Category*" required id="productCategory" name="productCategory" minlength="5" maxlength="30">-->
				<!-- <label for="category">Choose a Category:</label>  -->
				<select name="category" id="category">
                    <option value="${currentProduct.catg_id}">${currentProductCategory}</option>
				    <c:forEach items="${categoryList}" var="current">
				        <option value="${current.id}">${current.name}</option>
                    </c:forEach>
				</select>
				<input type="number" placeholder="Price*" required id="productPrice" name="productPrice" min="0" max="50000" value="${currentProduct.price}">
				<input type="number" placeholder="Discount" value="0" id="productDiscount" name="productDiscount" min="0" max="100" value="${currentProduct.discount}">
				<input type="number" placeholder="Quantity*" required id="productQuantity" name="productQuantity" min="0" max="50000"value="${currentProduct.quantity}">
				<input type="number" placeholder="Priority*" id="productPriority" name="productPriority" min="0" max="1" step="1" value="0" value="${currentProduct.priority}">
				<input type="text" placeholder="Description*" required id="productDescription" name="productDescription" minlength="20" maxlength="1000" value="${currentProduct.description}">
				<input type="file" id="productImage" name="productImage">
				<input type="submit" value="Edit" >
			</form>
		</div>
	</div>
</div>
<!-- //register -->
