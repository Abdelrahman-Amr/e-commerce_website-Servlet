<!-- //navigation -->
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!-- breadcrumbs -->
<%--	<div class="breadcrumbs">--%>
<%--		<div class="container">--%>
<%--			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">--%>
<%--				<li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>--%>
<%--				<li class="active">Products</li>--%>
<%--			</ol>--%>
<%--		</div>--%>
<%--	</div>--%>
<!-- //breadcrumbs -->
<!--- products --->
<div class="products">
    <div class="container">
        <div>
            <div class="categories">
                <%--                <h2>Categories</h2>--%>
                <ul>
                    <c:forEach items="${cats}" var="cat">

                        <li>
                            <a href="products?catId=${cat.id}">
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                                    ${cat.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                </ul>
            </div>
        </div>
        <div class="col-md-12 products-right">
            <div class="products-right-grid">
                <div class="products-right-grids">
                    <div class="sorting">
                        <select id="country" onchange="change_country(this.value)" class="frm-field required sect">
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Default sorting
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Sort by popularity
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Sort by average
                                rating
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Sort by price
                            </option>
                        </select>
                    </div>
                    <div class="sorting-left">
                        <select id="country1" onchange="change_country(this.value)" class="frm-field required sect">
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Item on page 9
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Item on page 18
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>Item on page 32
                            </option>
                            <option value="null"><i class="fa fa-arrow-right" aria-hidden="true"></i>All</option>
                        </select>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <c:forEach items="${products}" var="prod" varStatus="counter">
                <c:if test="${counter.count == 1 || (counter.count) % 4 == 0}">
                    <div class="agile_top_brands_grids">
                </c:if>

                <div class="col-md-4 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <c:if test="${prod.discount>0}">
                                <div class="agile_top_brand_left_grid_pos">
                                    <img src="images/offer.png" alt=" " class="img-responsive">
                                </div>
                            </c:if>
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                                <%--TODO: add product image--%>
                                            <a href="item?productId=${prod.id}"><img title=" " alt=" " src="image"></a>

                                            <p>${prod.name}</p>
                                            <br>
                                            <h4>${prod.price}
                                                <span class="currency">$</span>
                                                <c:if test="${prod.discount>0}">
                                                <span>${prod.price+prod.discount} $</span></h4>
                                            </c:if>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart">
                                                    <input type="hidden" name="add" value="1">
                                                    <input type="hidden" name="business" value=" ">
                                                    <input type="hidden" name="item_name" value="${prod.name}">
                                                    <input type="hidden" name="amount" value="35.99">
                                                    <input type="hidden" name="discount_amount" value="1.00">
                                                    <input type="hidden" name="currency_code" value="USD">
                                                    <input type="hidden" name="return" value=" ">
                                                    <input type="hidden" name="cancel_return" value=" ">
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button">
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${counter.count % 3 == 0}">
                    <div class="clearfix"></div>
                    </div>
                </c:if>
            </c:forEach>


            <div class="clearfix"></div>
            <nav class="numbering">
                <ul class="pagination paging">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>
                    <c:forEach begin="1" end="${pagination}" varStatus="loop">
                    <li><a href="#">${loop.index}</a></li>
                        </c:forEach>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <br>
        <div class="clearfix"></div>
    </div>
</div>
