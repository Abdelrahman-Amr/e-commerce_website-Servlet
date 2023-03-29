<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ page session="false" %>--%>
<div class="products">
    <div class="container">
        <div class="agileinfo_single">
            <div class="col-md-4 agileinfo_single_left">
                <img id="example" src="image?imgName=${product.imageUrl}" alt=" " class="img-responsive">
            </div>
            <div class="col-md-8 agileinfo_single_right">
                <h2></h2>

                <div class="w3agile_description">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                </div>
                <div class="snipcart-item block">
                    <div class="snipcart-thumb agileinfo_single_right_snipcart">
                        <input type="hidden" id="pdPrice" value="${product.price}">
                        <input type="hidden" id="price" value="${product.price}">
                        <input type="hidden" id="discount" value="${product.discount}">


                        <h3 class="m-sing" style="display: inline; margin-right: 10px" id="orgPrice">$${product.price}</h3>
                        <c:if test="${product.discount>0}">
                            <span id="discSpan" style="text-decoration: line-through">$${product.price + product.price*(product.discount/100)}</span>
                        </c:if>

                        <%--                        </input>--%>

                    </div>
                    <div class="snipcart-details agileinfo_single_right_details">
                        <form action="cart" method="post" onsubmit="addToCart(event,2)" onchange="updatePrice()">
                            <fieldset>
                                <ul style="  list-style-type: none;">
                                    <input type="hidden" name="pdId" value="${product.id}">
                                    <%--                                <p>Please select your favorite Web language:</p>--%>
                                    <c:forEach items="${sizes}" var="size" varStatus="counter">
                                        <li>
                                            <c:if test="${size.name =='Small'}">
                                                <input type="radio" id="${size.id}"  name="sizes" value="${size.id}" checked="checked">
                                                <label for="${size.id}">${size.name}</label>
                                            </c:if>
                                            <c:if test="${size.name !='Small'}">
                                                <input type="radio" id="${size.id}" name="sizes" value="${size.id}">
                                                <label for="${size.id}">${size.name}</label>
                                            </c:if>
                                                <%--                                     <input type="radio" id="${size.id}" name="sizes" value="${size.id}">--%>
                                            <input type="hidden" name="$sizePrec" id="p${size.id}" value="${size.percentage}">
                                                <%--                                     <label for="${size.id}">${size.name}</label>--%>
                                            <input type="hidden" id="n${size.id}" value="${size.name}">
                                        </li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" name="cancel_return" value=" ">
                                <input type="submit" name="submit" value="Add to cart" class="button" id="addTo"  style="margin-top: 15px; width: fit-content; padding: 10px 20px;" >
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- new -->
<div class="newproducts-w3agile">
    <div class="container">
        <h3>Related Products</h3>
        <div class="agile_top_brands_grids">
            <c:forEach items="${relatedProducts}" var="prod">

                <div class="col-md-3 top_brand_left-1">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid_pos">
                                <c:if test="${prod.discount>0}">
                                    <img src="images/offer.png" alt=" " class="img-responsive">
                                </c:if>
                            </div>
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="item?productId=${prod.id}"><img title=" " alt=" " src="image?imgName=${prod.imageUrl}"></a>
                                            <h4>${prod.name}</h4>
                                            <br>
                                            <h4>$${prod.price}
                                                <c:if test="${prod.discount>0}">
                                                    <span>$${prod.price + prod.price* (prod.discount/100)}</span>

                                                </c:if>
                                            </h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form  method="post" action="cart" onsubmit="addToCartSmall(event)">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart">
                                                    <input type="hidden" name="pdId" value="${prod.id}">
                                                    <input type="hidden" name="item_name" value="${prod.name}">
                                                    <input type="hidden" name="add" value="1">
                                                    <input type="hidden" name="business" value=" ">
                                                    <input type="hidden" name="item_name" value="basmati rise">
                                                    <input type="hidden" name="amount" value="30.99">
                                                    <input type="hidden" name="discount_amount" value="1.00">
                                                    <input type="hidden" name="currency_code" value="USD">
                                                    <input type="hidden" name="return" value=" ">
                                                    <input type="hidden" name="cancel_return" value=" ">
                                                    <input type="submit" name="submit" value="Add to cart" class="button">
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
