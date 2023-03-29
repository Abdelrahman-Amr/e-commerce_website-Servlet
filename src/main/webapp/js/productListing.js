jQuery(function ($) {
    $('.categories li a').click(function (evt) {
            evt.preventDefault();
            $.get($(this).attr('href'), function (data) {
                $('#product-list').html(data);
            });
        }
    );
});

jQuery(function ($) {
    const categories = document.querySelectorAll('.categories ul li a');
    categories.forEach(category => {
        category.addEventListener('click', () => {
            categories.forEach(c => c.classList.remove('active'));
            category.classList.add('active');
        });
    });
});
jQuery(function ($) {
    const categories = document.querySelectorAll('.price-filter');
    categories.forEach(category => {
        category.addEventListener('click', () => {
            categories.forEach(c => c.classList.remove('active'));
            category.classList.add('active');
        });
    });
});

function loadList(el, evt) {
    console.log("nooooooo");

    var currentCategory = $('.categories ul li a.active').attr('id');
    console.log($('#catInput').val());

    $('#currentCat').html($('#catInput').val());

    var priceFilter = el.value;
    evt.preventDefault();
    if (currentCategory === undefined)
        currentCategory = '';
    else currentCategory = 'catId=' + currentCategory;
    if (priceFilter === 'default')
        priceFilter = '';
    else priceFilter = 'price=' + priceFilter;
    $.get($(el).attr('href') + '&' + currentCategory + '&' + priceFilter, function (data) {
        $('#product-list').html(data);
    });
}

function filterProducts(el, evt) {
    console.log("ihhhh");
    var currentCategory = $('.categories ul li a.active').attr('id');
    console.log($('#catInput').val());
    $('#currentCat').html($('#catInput').val());
    var priceFilter = el.value;
    evt.preventDefault();
    if (currentCategory === undefined)
        currentCategory = '';
    else currentCategory = 'catId=' + currentCategory;
    if (priceFilter === 'default')
        priceFilter = '';
    else priceFilter = 'price=' + priceFilter;
    $.get('products?' + priceFilter + '&' + currentCategory, function (data) {
        $('#product-list').html(data);
    });

}

function  setCat(cat)
{
    console.log(cat);
    $('#currentCat').html(cat);

}
