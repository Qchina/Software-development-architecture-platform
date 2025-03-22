$(function () {
    function updateCount() {
        var itemCount = $('tr[class = cart-item]').length;
        $('#count').text(itemCount);
    }

    updateCount();
});