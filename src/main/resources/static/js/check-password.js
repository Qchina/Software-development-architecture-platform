var $remind = $('#security');

$(function () {
    $('#password').on('blur', function (){
        var password = $(this).val();
        var lengthCriteria = password.length >= 8;
        var complexityCriteria = /[A-Z]/.test(password) + /[a-z]/.test(password) +
            /\d/.test(password) + /[!/@#$%^&*]/.test(password);

        if (!lengthCriteria) {
            if ($('#img-pwd').length)
                $('#img-pwd').remove();
            $remind.before('<img src="images/circle.png" id="img-pwd" height="16" width="16" ' +
                'style="position: relative; left: 143px; top: 2px;" />');
            $remind.text("   强度：弱");
            $remind.css("color","#e3430d");
            return;
        }

        if (password.length <= 12) {
            if (complexityCriteria === 1) {
                if ($('#img-pwd').length)
                    $('#img-pwd').remove();
                $remind.before('<img src="images/circle1.png" id="img-pwd" height="16" width="16" ' +
                    'style="position: relative; left: 143px; top: 2px;" />');
                $remind.text("   强度：中");
                $remind.css("color","#FFD43B");
            }
            if (complexityCriteria === 2) {
                if ($('#img-pwd').length)
                    $('#img-pwd').remove();
                $remind.before('<img src="images/circle2.png" id="img-pwd" height="16" width="16" ' +
                    'style="position: relative; left: 143px; top: 2px;" />');
                $remind.text("   强度：强");
                $remind.css("color","#2dbceb");
            }
            if (complexityCriteria === 3 || complexityCriteria === 4) {
                if ($('#img-pwd').length)
                    $('#img-pwd').remove();
                $remind.before('<img src="images/circle3.png" id="img-pwd" height="16" width="16" ' +
                    'style="position: relative; left: 143px; top: 2px;" />');
                $remind.text("   强度：非常强");
                $remind.css("color","#3CB371");
            }
        } else {
            if (complexityCriteria === 1) {
                if ($('#img-pwd').length)
                    $('#img-pwd').remove();
                $remind.before('<img src="images/circle1.png" id="img-pwd" height="16" width="16" ' +
                    'style="position: relative; left: 143px; top: 2px;" />');
                $remind.text("   强度：中");
                $remind.css("color","#FFD43B");
            }
            if (complexityCriteria > 1) {
                if ($('#img-pwd').length)
                    $('#img-pwd').remove();
                $remind.before('<img src="images/circle3.png" id="img-pwd" height="16" width="16" ' +
                    'style="position: relative; left: 143px; top: 2px;" />');
                $remind.text("   强度：非常强");
                $remind.css("color","#3CB371");
            }
        }
    });
});