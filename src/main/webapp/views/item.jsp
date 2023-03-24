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
                    <h4>${product.name}</h4>
                    <p>${product.description}</p>
                </div>
                <div class="snipcart-item block">
                    <div class="snipcart-thumb agileinfo_single_right_snipcart">
                        <h4 class="m-sing">${product.price}
                            <c:if test="${product.discount>0}">
                                <span>${product.price+product.discount}</span></h4>
                            </c:if>
                        </h4>

                    </div>
                    <div class="snipcart-details agileinfo_single_right_details">
                        <form action="cart" method="post" onsubmit="addToCart(event,2)">
                            <fieldset>
                                <input type="hidden" name="pdId" value="${product.id}">
<%--                                <p>Please select your favorite Web language:</p>--%>
                                <c:forEach items="${sizes}" var="size" varStatus="counter">
                                     <input type="radio" id="${size.id}" name="sizes" value="${size.name}">
                                     <label for="${size.id}">${size.name}</label><br>
                                </c:forEach>
                                <input type="hidden" name="cancel_return" value=" ">
                                <input type="submit" name="submit" value="Add to cart" class="button"  >
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
        <h3>New offers</h3>
        <div class="agile_top_brands_grids">
            <div class="col-md-3 top_brand_left-1">
                <div class="hover14 column">
                    <div class="agile_top_brand_left_grid">
                        <div class="agile_top_brand_left_grid_pos">
                            <img src="images/offer.png" alt=" " class="img-responsive">
                        </div>
                        <div class="agile_top_brand_left_grid1">
                            <figure>
                                <div class="snipcart-item block">
                                    <div class="snipcart-thumb">
                                        <a href="products.jsp"><img title=" " alt=" " src="images/14.png"></a>
                                        <p>Fried-gram</p>
                                        <div class="stars">
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>
                                        </div>
                                        <h4>$35.99 <span>$55.00</span></h4>
                                    </div>
                                    <div class="snipcart-details top_brand_home_details">
                                        <form action="#" method="post">
                                            <fieldset>
                                                <input type="hidden" name="cmd" value="_cart">
                                                <input type="hidden" name="add" value="1">
                                                <input type="hidden" name="business" value=" ">
                                                <input type="hidden" name="item_name" value="Fortune Sunflower Oil">
                                                <input type="hidden" name="amount" value="35.99">
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
            <div class="col-md-3 top_brand_left-1">
                <div class="hover14 column">
                    <div class="agile_top_brand_left_grid">
                        <div class="agile_top_brand_left_grid_pos">
                            <img src="images/offer.png" alt=" " class="img-responsive">
                        </div>
                        <div class="agile_top_brand_left_grid1">
                            <figure>
                                <div class="snipcart-item block">
                                    <div class="snipcart-thumb">
                                        <a href="products.jsp"><img title=" " alt=" " src="images/15.png"></a>
                                        <p>Navaratan-dal</p>
                                        <div class="stars">
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>
                                        </div>
                                        <h4>$30.99 <span>$45.00</span></h4>
                                    </div>
                                    <div class="snipcart-details top_brand_home_details">
                                        <form action="#" method="post">
                                            <fieldset>
                                                <input type="hidden" name="cmd" value="_cart">
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
            <div class="col-md-3 top_brand_left-1">
                <div class="hover14 column">
                    <div class="agile_top_brand_left_grid">
                        <div class="agile_top_brand_left_grid_pos">
                            <img src="images/offer.png" alt=" " class="img-responsive">
                        </div>
                        <div class="agile_top_brand_left_grid_pos">
                            <img src="images/offer.png" alt=" " class="img-responsive">
                        </div>
                        <div class="agile_top_brand_left_grid1">
                            <figure>
                                <div class="snipcart-item block">
                                    <div class="snipcart-thumb">
                                        <a href="products.jsp"><img src="images/16.png" alt=" " class="img-responsive"></a>
                                        <p>White-peasmatar</p>
                                        <div class="stars">
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>
                                        </div>
                                        <h4>$80.99 <span>$105.00</span></h4>
                                    </div>
                                    <div class="snipcart-details top_brand_home_details">
                                        <form action="#" method="post">
                                            <fieldset>
                                                <input type="hidden" name="cmd" value="_cart">
                                                <input type="hidden" name="add" value="1">
                                                <input type="hidden" name="business" value=" ">
                                                <input type="hidden" name="item_name" value="Pepsi soft drink">
                                                <input type="hidden" name="amount" value="80.00">
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
            <div class="col-md-3 top_brand_left-1">
                <div class="hover14 column">
                    <div class="agile_top_brand_left_grid">
                        <div class="agile_top_brand_left_grid_pos">
                            <img src="images/offer.png" alt=" " class="img-responsive">
                        </div>
                        <div class="agile_top_brand_left_grid1">
                            <figure>
                                <div class="snipcart-item block">
                                    <div class="snipcart-thumb">
                                        <a href="products.jsp"><img title=" " alt=" " src="images/17.png"></a>
                                        <p>Channa-dal</p>
                                        <div class="stars">
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>
                                        </div>
                                        <h4>$35.99 <span>$55.00</span></h4>
                                    </div>
                                    <div class="snipcart-details top_brand_home_details">
                                        <form action="#" method="post">
                                            <fieldset>
                                                <input type="hidden" name="cmd" value="_cart">
                                                <input type="hidden" name="add" value="1">
                                                <input type="hidden" name="business" value=" ">
                                                <input type="hidden" name="item_name" value="Fortune Sunflower Oil">
                                                <input type="hidden" name="amount" value="35.99">
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
            <div class="clearfix"></div>
        </div>
    </div>
</div>
