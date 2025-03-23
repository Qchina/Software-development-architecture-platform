var $waring = $('#feedback');

function checkUserName(username) {
    if (username === null || username == '' || username.length === 0) {
        return '  用户名不能为空';
    }
    if (username.length <= 5) {
        return '  用户名长度必须大于5';
    }
    return '';
}

$(function () {
    $('#username').on('blur', function () {
        var username = $(this).val();
        var validateMsg = checkUserName(username);

        if (validateMsg === '') {
            $.ajax({
                type: 'GET',
                url: 'usernameExist?username=' + username,
                success: function (data) {
                    if (data === 'Exist') {
                        if ($('#img-name').length)
                            $('#img-name').remove();
                        $waring.before('<img src="images/warning.png" id="img-name" height="18" width="18" ' +
                            'style="position: relative; left: 143px; top: 1px;" />');
                        $waring.text('  用户名已存在');
                    } else {
                        if ($('#img-name').length)
                            $('#img-name').remove();
                        $waring.before('<img src="images/correct.png" id="img-name" height="18" width="18" ' +
                            'style="position: relative; left: 143px; top: 1px;" />');
                        $waring.text('  用户名合法');
                    }
                },
                error: function (errorMsg) {
                    console.log(errorMsg);
                }
            });
        } else {
            if ($('#img-name').length)
                $('#img-name').remove();
            $waring.before('<img src="images/warning.png" id="img-name" height="18" width="18" ' +
                'style="position: relative; left: 143px; top: 1px;" />');
            $waring.text(validateMsg);
        }
    });
});
