<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Coffee Point</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Coffee Point, Coffee, ITI, JETS, ECOMMERCE"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <link href='//fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>

    <%--	Our styles--%>
    <link href="css/myStyles.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/customerTable.css" rel="stylesheet" type="text/css" media="all">

    <!-- Our scripts -->
    <script src="js/previewCustomerScript.js"></script>
    <script src="js/login.js"></script>
    <script src="js/logout.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/registerjs.js"></script>
    <script src="js/profile.js"></script>


    <!-- start-smoth-scrolling -->
</head>
<body>
<!-- header -->
<div class="agileits_header">
    <div class="container">
        <%--        <div class="w3l_offers">--%>
        <%--            <p>SALE UP TO 70% OFF. USE CODE "SALE70%" . <a href="products.html">SHOP NOW</a></p>--%>
        <%--        </div>--%>
        <div class="agile-login">
            <ul>
                <c:if test="${isLogin !='true'}">
                    <li><a href="Register"> Create Account </a></li>
                </c:if>
                <c:if test="${isLogin !='true'}">
                    <li><a href="login">Login</a></li>
                </c:if>
                <c:if test="${isLogin =='true'}">
                    <li onclick="logout()"><a id="logout-link">Logout</a></li>
                </c:if>
                <%--					<li><a href="contact.html">Help</a></li>--%>
                <c:if test="${isLogin =='true'}">
                    <li ><a href="Profile">Welcome, ${customer.userName}</a></li>
                </c:if>
            </ul>
        </div>
        <div class="product_list_header">
            <form action="#" method="post" class="last">
                <input type="hidden" name="cmd" value="_cart">
                <input type="hidden" name="display" value="1">
                <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down"
                                                                                    aria-hidden="true"></i></button>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<div class="logo_products">
    <div class="container">
        <!-- <div class="w3ls_logo_products_left1">
                <ul class="phone_email">
                    <li><i class="fa fa-phone" aria-hidden="true"></i>Order online or call us : (+0123) 234 567</li>

                </ul>
            </div> -->
        <div class="w3ls_logo_products_left">
            <h1><a href="home">Coffee Point</a></h1>
        </div>
        <div class="w3l_search">
            <form action="#" method="post">
                <input type="search" name="Search" placeholder="Search for a Product..." required="">
                <button type="submit" class="btn btn-default search" aria-label="Left Align">
                    <i class="fa fa-search" aria-hidden="true"> </i>
                </button>
                <div class="clearfix"></div>
            </form>
        </div>

        <div class="clearfix"></div>
    </div>
</div>
<!-- //header -->
<!-- navigation -->
<div class="navigation-agileits">
    <div class="container">
        <nav class="navbar navbar-default">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header nav_2">
                <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                        data-target="#bs-megadropdown-tabs">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="products" class="act" style="color: red">Shop</a></li>
                    <c:forEach items="${cats}" var="cat">
                        <li class="active"><a href="products?catId=${cat.id}" class="act">${cat.name}</a></li>
                    </c:forEach>
                    <!-- Mega Menu -->
                    <%--                    <li class="dropdown">--%>
                    <%--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cold Coffee<b class="caret"></b></a>--%>
                    <%--                        <ul class="dropdown-menu multi-column columns-3">--%>
                    <%--                            <div class="row">--%>
                    <%--                                <div class="multi-gd-img">--%>
                    <%--                                    <ul class="multi-column-dropdown">--%>
                    <%--                                        <h6>All Cold Coffee</h6>--%>
                    <%--                                        <li><a href="groceries.html">Iced Coffee</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Coffee with Milk</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Caffè Americano</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Espresso</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Flat White</a></li>--%>
                    <%--                                        <li><a href="groceries.html"> Iced Caffè Latte</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Caramel Macchiato</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced White Chocolate Mocha</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Iced Caffè Mocha</a></li>--%>
                    <%--                                    </ul>--%>
                    <%--                                </div>--%>

                    <%--                            </div>--%>
                    <%--                        </ul>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="dropdown">--%>
                    <%--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hot Coffee<b class="caret"></b></a>--%>
                    <%--                        <ul class="dropdown-menu multi-column columns-3">--%>
                    <%--                            <div class="row">--%>
                    <%--                                <div class="multi-gd-img">--%>
                    <%--                                    <ul class="multi-column-dropdown">--%>
                    <%--                                        <h6>All Hot Coffee</h6>--%>
                    <%--                                        <li><a href="groceries.html">Coffee</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Coffee with Milk</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Caffè Americano</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Espresso</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Flat White</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Caffè Latte</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Caramel Macchiato</a></li>--%>
                    <%--                                        <li><a href="groceries.html">White Chocolate Mocha</a></li>--%>
                    <%--                                        <li><a href="groceries.html">Caffè Mocha</a></li>--%>
                    <%--                                    </ul>--%>
                    <%--                                </div>--%>


                    <%--                            </div>--%>
                    <%--                        </ul>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="dropdown">--%>
                    <%--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Bakery<b class="caret"></b></a>--%>
                    <%--                        <ul class="dropdown-menu multi-column columns-3">--%>
                    <%--                            <div class="row">--%>
                    <%--                                <div class="multi-gd-img">--%>
                    <%--                                    <ul class="multi-column-dropdown">--%>
                    <%--                                        <h6>Croissants</h6>--%>
                    <%--                                        <li><a href="personalcare.html">Ham & Swiss Croissant</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Butter Croissant</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Chocolate Croissant</a></li>--%>
                    <%--                                        <h6>Cakes</h6>--%>
                    <%--                                        <li><a href="personalcare.html">Cinnamon Cake</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Lemon Cake</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Chocolate Cake</a></li>--%>
                    <%--                                        <h6>Donuts</h6>--%>
                    <%--                                        <li><a href="personalcare.html">Glazed Donut</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Boston Cream Donut</a></li>--%>
                    <%--                                        <li><a href="personalcare.html">Chocolate Donut</a></li>--%>
                    <%--                                    </ul>--%>
                    <%--                                </div>--%>

                    <%--                            </div>--%>
                    <%--                        </ul>--%>
                    <%--                    </li>--%>
                    <li><a href="offers.html">Offers</a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<!-- //navigation -->
<!-- breadcrumbs -->
<%--<div class="breadcrumbs">--%>
<%--    <div class="container">--%>
<%--        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">--%>
<%--            <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>--%>
<%--            <li class="active">Singlepage</li>--%>
<%--        </ol>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- //breadcrumbs -->
