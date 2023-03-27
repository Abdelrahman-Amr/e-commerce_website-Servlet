<!-- //navigation -->


<%@taglib prefix="c" uri="jakarta.tags.core" %>


    <div class="dashboard">
    <div class="container">
    <div class="cart-container">
        <div class="stats">
            <ul class="row">
                <li class="col-md-3 col-sm-6 category-tabs column">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fa fa-coffee"></i> Total Products</h5>
                            <p class="card-text">${totalProducts}</p>
                            <a href="AddingProduct">
                                <button class="btn btn-primary btn-sm" style="margin-right: 10px;">Add Product</button>
                            </a>
                            <a href="adminProductListing">
                                <button class="btn btn-secondary btn-sm">View Products</button>
                            </a>
                        </div>
                    </div>
                </li>

                <li class="col-md-3 col-sm-6 category-tabs column">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fa fa-shopping-cart"></i> Total Orders</h5>
                            <p class="card-text">${totalOrders}</p>
                            <a href="order">
                                <button class="btn btn-primary btn-sm" style="margin-right: 10px;">order details</button>
                            </a>
                        </div>
                    </div>
                </li>

                <li class="col-md-3 col-sm-6 category-tabs column">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fa fa-users"></i> Total Customers</h5>
                            <p class="card-text">${totalCustomers}</p>
                            <a href="PreviewCustomer">
                                <button class="btn btn-primary btn-sm" style="margin-right: 10px;">customer details</button>
                            </a>
                        </div>
                    </div>
                </li>

                <li class="col-md-3 col-sm-6 category-tabs column">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fa fa-dollar"></i> Total Revenue</h5>
                            <p class="card-text">${totalRevenue} $</p>
                        </div>
                    </div>
                </li>
            </ul>

        </div>
        <h2 class="cat-header " id="currentCat">${currentCat}</h2>

    </div>

<%--<div id="product-list" class=" products-right">--%>
<%--    <div class="products-right-grid">--%>
<%--        <div class="products-right-grids">--%>
<%--            <div class="clearfix"></div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--            <div class="agile_top_brands_grids">--%>

<%--        <div class="col-md-4 top_brand_left">--%>
<%--            <div class="hover14 column">--%>
<%--                <div class="agile_top_brand_left_grid">--%>
<%--                        <div class="agile_top_brand_left_grid_pos">--%>
<%--                            <img src="images/offer.png" alt=" " class="img-responsive">--%>
<%--                        </div>--%>
<%--                    <div class="agile_top_brand_left_grid1">--%>
<%--                        <figure>--%>
<%--                            <div class="snipcart-item block">--%>
<%--                                <div class="snipcart-thumb">--%>
<%--                                        &lt;%&ndash;TODO: add product image&ndash;%&gt;--%>
<%--                                    <a href="item?productId=${prod.id}"><img title=" " alt=" " src="image?imgName=${prod.imageUrl}"></a>--%>

<%--                                    <h4>${prod.name}</h4>--%>
<%--                                    <br>--%>
<%--                                    <h4>$${prod.price}--%>
<%--                                            &lt;%&ndash;                                        <span class="currency">$</span>&ndash;%&gt;--%>
<%--                                        <span>$${prod.price + prod.price*(prod.discount/100)} </span></h4>--%>
<%--                                </div>--%>
<%--                                <div class="snipcart-details top_brand_home_details">--%>
<%--                                    <form  method="post" action="cart" onsubmit="addToCartSmall(event)">--%>
<%--                                        <fieldset>--%>
<%--                                            <input type="hidden" name="pdId" value="${prod.id}">--%>
<%--                                            <input type="hidden" name="quantity" value="1">--%>
<%--                                            <input type="hidden" name="business" value=" ">--%>
<%--                                            <input type="hidden" name="item_name" value="${prod.name}">--%>
<%--                                            <input type="hidden" name="amount" value="35.99">--%>
<%--                                            <input type="hidden" name="discount_amount" value="1.00">--%>
<%--                                            <input type="hidden" name="currency_code" value="USD">--%>
<%--                                            <input type="hidden" name="return" value=" ">--%>
<%--                                            <input type="hidden" name="cancel_return" value=" ">--%>
<%--                                            <input type="submit" name="submit" value="Add to cart"--%>
<%--                                                   class="button">--%>
<%--                                        </fieldset>--%>
<%--                                    </form>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </figure>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--            <div class="clearfix"></div>--%>
<%--            </div>--%>


<%--    <div class="clearfix"></div>--%>
<%--        <nav class="numbering">--%>
<%--            <ul class="pagination paging">--%>
<%--                    &lt;%&ndash;                <li>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    <a href="#" aria-label="Previous">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <span aria-hidden="true">&laquo;</span>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    </a>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                </li>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    <li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>&ndash;%&gt;--%>

<%--                    <li>--%>
<%--                    <li><a href="${pageUrl}" onclick="loadList(this,event)">${loop.index}</a></li>--%>
<%--                    &lt;%&ndash;                <li>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    <a href=" #" aria-label="Next">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <span aria-hidden="true">&raquo;</span>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    </a>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                </li>&ndash;%&gt;--%>
<%--            </ul>--%>
<%--        </nav>--%>

<%--</div>--%>

    <br>
    <div class="clearfix"></div>
    </div>
    </div>

