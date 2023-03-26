<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!-- register -->
<div class="register">
	<div class="container">
		<h2>Adding Product</h2>
		<div class="login-form-grids">
			<!-- <h5>profile information</h5> -->
			<form method="post" onsubmit="addProductAdmin(event)" enctype="multipart/form-data">
				<input type="text" placeholder="Name*" required minlength="3" maxlength="30"  id="productName" name="productName" >
<!--				<input type="text" placeholder="Category*" required id="productCategory" name="productCategory" minlength="5" maxlength="30">-->
				<!-- <label for="category">Choose a Category:</label>  -->
				<select name="category" id="category">
				    <c:forEach items="${categoryList}" var="current">
				        <option value=${current.id}>${current.name}</option>
                    </c:forEach>
				</select>
				<input type="number" placeholder="Price*" required id="productPrice" name="productPrice" min="0" max="50000">
				<input type="number" placeholder="Discount" id="productDiscount" name="productDiscount" min="0" max="100">
				<input type="number" placeholder="Quantity*" required id="productQuantity" name="productQuantity" min="0" max="50000">
				<input type="number" placeholder="Priority*" id="productPriority" name="productPriority" min="0" max="1" step="1" >
				<input type="text" placeholder="Description*" required id="productDescription" name="productDescription" minlength="20" maxlength="1000">
				<input type="file" required id="productImage" name="productImage">
				<input type="submit" value="Add" >
			</form>
		</div>
	</div>
</div>
<!-- //register -->
