<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- main-slider -->
<%--<%@ page session="false" %>--%>
<ul id="demo1" >
    <li>
        <img src="images/coffee2.jpg" alt=""/>
        <!--Slider Description example-->
        <div class="slide-desc">
<%--            <h3>Buy Rice Products Are Now On Line With Us</h3>--%>
        </div>
    </li>
    <li>
        <img src="images/slider3.jpg" alt=""/>
        <div class="slide-desc">
<%--            <h3>Whole Spices Products Are Now On Line With Us</h3>--%>
        </div>
    </li>
    <li>
        <img src="images/slider2.jpg" alt=""/>
        <div class="slide-desc">
            <%--            <h3>Whole Spices Products Are Now On Line With Us</h3>--%>
        </div>
    </li>
    <li>
        <img src="images/slider3.jpg" alt=""/>
        <div class="slide-desc">
            <%--            <h3>Whole Spices Products Are Now On Line With Us</h3>--%>
        </div>
    </li>

    <li>
        <img src="images/7088595_3538081.jpg" alt=""/>
        <div class="slide-desc">
<%--            <h3>Whole Spices Products Are Now On Line With Us</h3>--%>
        </div>
    </li>
</ul>
<!-- //main-slider -->
<!-- //top-header and slider -->
<!-- top-brands -->
<div class="top-brands">
    <div class="container">
        <h2>Top Coffees</h2>
        <div class="grid_3 grid_5">
            <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                <ul id="myTab" class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#expeditions" id="expeditions-tab" role="tab"
                                                              data-toggle="tab" aria-controls="expeditions"
                                                              aria-expanded="true" onclick="getPriorityProducts()">Advertised Coffee</a>
                    </li>
                    <li role="presentation"><a href="#tours" role="tab" id="tours-tab" data-toggle="tab"
                                               aria-controls="tours" onclick="getMostSellingProducts()">Top Selling</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="expeditions"
                         aria-labelledby="expeditions-tab">
                        <div class="agile-tp">
                            <h5>Advertised this week</h5>
                            <p class="w3l-ad">We've pulled together all our advertised coffee into one place, so you
                                won't miss out on a great coffee.</p>
                        </div>
                        <div class="agile_top_brands_grids" id="adList">
                            <c:forEach items="${priorityProducts}" var="prod">

                                <div class="col-md-4 top_brand_left-1">
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
                                                            <a href="item?productId=${prod.id}"><img title=" " id="ii${prod.id}" alt=" " src="image?imgName=${prod.imageUrl}"></a>
                                                            <h4 id="nn${prod.id}">${prod.name}</h4>
                                                            <br>
                                                            <h4 id="p${prod.id}">$${prod.price}
                                                                <c:if test="${prod.discount>0}">
                                                                    <span id="dd${prod.id}">$${prod.price + prod.price* (prod.discount/100)}</span>

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
<%--                        </div>--%>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
                        <div class="agile-tp">
                            <h5>This week</h5>
                            <p class="w3l-ad">We've pulled together all our top selling coffee into one place, so you
                                won't miss out on a great coffee.</p>
                        </div>
                        <div class="agile_top_brands_grids" id="mostList">
                            <c:forEach items="${mostProducts}" var="prod">

                                <div class="col-md-4 top_brand_left-1">
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
                                                            <a href="item?productId=${prod.id}"><img title=" " id="i${prod.id}" alt=" " src="image?imgName=${prod.imageUrl}"></a>
                                                            <h4 id="n${prod.id}">${prod.name}</h4>
                                                            <br>
                                                            <h4 id="p${prod.id}">$${prod.price}
                                                                <c:if test="${prod.discount>0}">
                                                                    <span id="d${prod.id}">$${prod.price + prod.price* (prod.discount/100)}</span>

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
<%--                        <div class="agile_top_brands_grids">--%>
<%--                            --%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //top-brands -->

<!-- new -->
<div class="newproducts-w3agile">
    <div class="container">
        <h3>New offers</h3>
        <div class="agile_top_brands_grids">

        <c:forEach items="${offerProducts}" var="prod">

            <div class="col-md-4 top_brand_left-1">
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
<%--<script type="text/javascript">--%>
<%--    getPriorityProducts();--%>
<%--</script>--%>
