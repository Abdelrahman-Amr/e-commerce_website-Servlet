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
<c:if test="${!isAJAX}">

    <div class="products">
    <div class="container">
    <div class="cart-container">
        <div class="categories">
                <%--                <h2>Categories</h2>--%>
            <ul class="row">
                <c:forEach items="${cats}" var="cat">
                    <li class="col-md-3 col-sm-6 category-tabs column" >
                        <a id="${cat.id}" href="products?catId=${cat.id}">
                            <i class="fa fa-arrow-right" aria-hidden="true"></i>
                                ${cat.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>

        </div>
        <h2 class="cat-header ">${currentCat}</h2>

    </div>
    <div class="sorting">
        <select href="/products?" id="filters" onchange="filterProducts(this, event)" class="frm-field required sect">
            <option class="price-filter" value="default"><i class="fa fa-arrow-right" aria-hidden="true" ></i>Default sorting
            </option>
            <option class="price-filter" value="asc"><i class="fa fa-arrow-right" aria-hidden="true"></i>Sort by price Asc
            </option>
            <option class="price-filter" value="desc"><i class="fa fa-arrow-right" aria-hidden="true" ></i>Sort by price Desc
            </option>
        </select>
    </div>
</c:if>
<div id="product-list" class=" products-right">
    <div class="products-right-grid">
        <div class="products-right-grids">
            <div class="clearfix"></div>
        </div>
    </div>

    <c:forEach items="${products}" var="prod" varStatus="counter">
        <c:if test="${counter.count == 1 || (counter.count) == 4 ||(counter.count) == 7 }">
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
                                    <a href="item?productId=${prod.id}"><img title=" " alt=" " src="image?imgName=${prod.imageUrl}"></a>

                                    <p>${prod.name}</p>
                                    <br>
                                    <h4>${prod.price}
                                        <span class="currency">$</span>
                                        <c:if test="${prod.discount>0}">
                                        <span>${prod.price+prod.discount} $</span></h4>
                                    </c:if>
                                </div>
                                <div class="snipcart-details top_brand_home_details">
                                    <form  method="post" action="cart" onsubmit="addToCartSmall(event)">
                                        <fieldset>
                                            <input type="hidden" name="pdId" value="${prod.id}">
                                            <input type="hidden" name="quantity" value="1">
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
    <c:if test="${pagination>1}">
        <nav class="numbering">
            <ul class="pagination paging">
<%--                <li>--%>
<%--                    <a href="#" aria-label="Previous">--%>
<%--                        <span aria-hidden="true">&laquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
                    <%--                    <li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>--%>
                <c:forEach begin="1" end="${pagination}" varStatus="loop">
                    <c:url var="pageUrl" value="products">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'page'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="page" value="${loop.index}"/>
                    </c:url>
                    <li>
                    <li><a href="${pageUrl}" onclick="loadList(this,event)">${loop.index}</a></li>
                </c:forEach>
<%--                <li>--%>
<%--                    <a href=" #" aria-label="Next">--%>
<%--                        <span aria-hidden="true">&raquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
            </ul>
        </nav>
    </c:if>
</div>

<c:if test="${!isAJAX}">

    <br>
    <div class="clearfix"></div>
    </div>
    </div>
</c:if>
