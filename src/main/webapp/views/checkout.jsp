<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ page session="false" %>--%>

<!-- checkout -->
	<div class="checkout">
		<div class="container">
			<h2>Your shopping cart contains: <span>${cartSize} Products</span></h2>
			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
						<tr>
							<th>SL No.</th>	
							<th>Product</th>
							<th>Quality</th>
							<th>Product Name</th>
						
							<th>Price</th>
							<th>Remove</th>
						</tr>
					</thead>

						<c:forEach items="${cart}" var="order" varStatus="counter">

							<tr class="rem1">
						<td class="invert">1</td>
						<td class="invert-image"><a href="item.jsp"><img src="images/1.png" alt=" " class="img-responsive" /></a></td>
						<td class="invert">
							 <div class="quantity"> 
								<div class="quantity-select">                           
									<div class="entry value-minus" onclick="decreaseQuan()"></div>
									<div class="entry value"><span>${order.quantity}</span></div>
									<div class="entry value-plus active" onclick="increaseQuan()">&nbsp;</div>
								</div>
							</div>
						</td>
						<td class="invert">${order.product.name}</td>
						
						<td class="invert">${order.product.price}</td>
						<td class="invert">
							<div class="rem">
								<div class="close1"> </div>
							</div>

						</td>
					</tr>
						</c:forEach>

								<!--quantity-->
<%--									<script>--%>
<%--									$('.value-plus').on('click', function(){--%>
<%--										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;--%>
<%--										divUpd.text(newVal);--%>
<%--									});--%>

<%--									$('.value-minus').on('click', function(){--%>
<%--										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;--%>
<%--										if(newVal>=1) divUpd.text(newVal);--%>
<%--									});--%>
<%--									</script>--%>
								<!--quantity-->
				</table>
			</div>
			<div class="checkout-left">	
				<div class="checkout-left-basket">
					<h4>Continue to basket</h4>
					<ul>
						<c:forEach items="${cart}" var="order" varStatus="counter">

							<li>${order.product.name} <i>-</i> <span>${order.total}</span></li>
						</c:forEach>
<%--						<li>Product2 <i>-</i> <span>$25.00 </span></li>--%>
<%--						<li>Product3 <i>-</i> <span>$29.00 </span></li>--%>
						<li>Total Service Charges <i>-</i> <span>${dev}</span></li>
						<li>Total <i>-</i> <span>${cartTotal}</span></li>
					</ul>
				</div>
				<div class="checkout-right-basket">
					<a href="products"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Continue Shopping</a>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //checkout -->
