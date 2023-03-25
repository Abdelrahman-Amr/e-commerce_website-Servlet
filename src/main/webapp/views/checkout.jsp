<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ page session="false" %>--%>

<!-- checkout -->
<div class="checkout">
	<div class="container">
		<%--			<div class="c">--%>
		<h2 class="cart-header ">Shopping Cart</h2>
		<c:if test="${cartSize==null || cartSize==0}">

			<div class="checkout-right-basket">
				<p id="empty-cart" >Your cart is empty !!</p>
				<a href="products"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Continue Shopping</a>
			</div>
		</c:if>
		<%--			</div>--%>
		<c:if test="${cartSize!=null && cartSize>0}">

			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
					<tr>
							<%--							<th>SL No.</th>	--%>
						<th>Product</th>
						<th>Size</th>
						<th>Quantity</th>
							<%--							<th>Name</th>--%>
						<th>Price</th>
							<%--							<th>Remove</th>--%>
					</tr>
					</thead>

					<c:forEach items="${cart}" var="order" varStatus="counter">

						<tr class="rem1" id="r${order.product.id}${order.size}">
								<%--						<td class="invert">1</td>--%>

							<td class="invert-image" id="row"><a href="item?productId=${order.product.id}">
								<figure>
									<img src="image?imgName=${order.product.imageUrl}" alt=" " class="img-responsive cart-img" />
									<figcaption>${order.product.name}</figcaption>
								</figure>
							</a>
							</td>
							<td class="invert" >${order.size}</td>
							<td class="invert">
								<div class="quantity">
									<div class="quantity-select">
										<div class="entry value-minus" onclick="decreaseQuan(${order.product.id},'${order.size}')"></div>
										<input type="hidden" id="${order.product.id}${order.size}" value="${order.product.id}">
											<%--									<input type="hidden" id="q${order.product.id}" value="${order.quantity}">--%>
										<input type="hidden" id="pdSize" value="${order.size}">
										<div class="entry value"><span id="q${order.product.id}${order.size}">${order.quantity}</span></div>
										<div class="entry value-plus active" onclick="increaseQuan(${order.product.id},'${order.size}')"></div>
									</div>
								</div>
							</td>
								<%--						<td class="invert" >${order.product.name}</td>--%>
							<td class="invert">$<span id="p${order.product.id}${order.size}">${order.total / order.quantity}</span></td>
								<%--							<td class="invert">--%>
								<%--								<div class="rem">--%>
								<%--									<div class="close1"> </div>--%>
								<%--								</div>--%>

								<%--							</td>--%>
						</tr>
					</c:forEach>


				</table>
			</div>

			<div class="checkout-left">
				<div class="checkout-left-basket">
					<h4>Summary</h4>
					<ul>
						<c:forEach items="${cart}" var="order" varStatus="counter">
							<li id="rr${order.product.id}${order.size}">${order.product.name}(${order.size}) <i>-</i><span id="t${order.product.id}${order.size}">${order.total}</span> <span>$</span></li>
						</c:forEach>
							<%--						<li>Product2 <i>-</i> <span>$25.00 </span></li>--%>
							<%--						<li>Product3 <i>-</i> <span>$29.00 </span></li>--%>
						<li>Delivery fee <i>-</i> <span>$${dev}</span></li>
						<li>Total <i>-</i> <span id="total">${cartTotal}</span><span>$</span></li>
					</ul>

					<p>Payment: </p>
					<input type="radio" id="credit" name="payment" value="1">
					<label for="credit">Credit</label>
					<input type="radio" id="cash" name="payment" value="2" checked="checked">
					<input type="hidden" id="isLogin" value="${isLogin}">
					<input type="hidden" id="creditVal" value="${customer.creditLimit}">

					<label for="cash">Cash</label>
				</div>
				<div class="checkoutBtn">
					<button class="button" onclick="order()">Confirm Order</button>

				</div>
					<%--				<div class="checkout-right-basket">--%>
					<%--					<a href="products"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Continue Shopping</a>--%>
					<%--				</div>--%>
				<div class="clearfix"> </div>
			</div>
		</c:if>
	</div>
</div>
<!-- //checkout -->
