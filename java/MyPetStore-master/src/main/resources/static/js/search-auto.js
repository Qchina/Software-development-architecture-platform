$(function () {
    $('#keyword').on('keyup', function () {
        var keyword = $(this).val();

        if (keyword !== '' && keyword !== null && keyword.length !== 0) {
            $.ajax({
                type : 'GET',
                url : '/catalog/searchAutoComplete?keyword=' + keyword,
                success : function (data) {
                    console.log(data);
                    var innerHTML = '';

                    for (var i = 0; i < data.length; i++) {
                        innerHTML += '<li class="productAutoItem" data-productId="' +
                            data[i].productId + '">' +
                            data[i].categoryId + ': ' +
                            data[i].name + '</li>';
                    }
                    $('#productAutoList').html(innerHTML);
                    $('#productAutoComplete').show();
                },
                error : function (errorMsg) {
                    console.log(errorMsg);
                }
            });
        } else {
            $('#productAutoComplete').hide();
        }
    });

    $(document).on('click', '.productAutoItem', function () {
        var productId = $(this).data('productid');
        console.log(productId);
        $('#productAutoComplete').hide();
        $('#keyword').val('');

        window.location.href = '/catalog/viewProduct?productId=' + productId;
    });

    $(document).on('mouseover', '.productAutoItem', function () {
        $(this).css('color', '#3CB371');
    });

    $(document).on('mouseout', '.productAutoItem', function () {
        $(this).css('color', '#005e21');
    });

    $('#productAutoComplete').on('mouseleave', function () {
        $(this).hide();
        $('#keyword').val('');
    })
});