<!-- //navigation -->


<%@taglib prefix="c" uri="jakarta.tags.core" %>


<div class="dashboard">
    <div class="container">
        <h2>Admin Dashboard</h2>
        <div class="cart-container">
            <div class="stats">
                <ul class="row">
                    <li class="col-md-3 col-sm-6 category-tabs column">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><i class="fa fa-coffee"></i> Total Products</h5>
                                <p class="card-text">${totalProducts}</p>
                                <a href="AddingProduct">
                                    <button class="btn btn-primary btn-sm" style="margin-right: 10px;">Add Product
                                    </button>
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
                                <a href="PreviewOrder">
                                    <button class="btn btn-primary btn-sm" style="margin-right: 10px;">order details
                                    </button>
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
                                    <button class="btn btn-primary btn-sm" style="margin-right: 10px;">customer
                                        details
                                    </button>
                                </a>
                            </div>
                        </div>
                    </li>

                    <li class="col-md-3 col-sm-6 category-tabs column">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><i class="fa fa-dollar"></i> Total Revenue</h5>
                                <p class="card-text">$ ${totalRevenue} </p>
                            </div>
                        </div>
                    </li>
                </ul>

            </div>
            <h2 class="cat-header " id="currentCat">${currentCat}</h2>

        </div>


        <br>
        <div class="clearfix"></div>
    </div>
</div>

