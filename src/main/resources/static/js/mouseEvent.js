$(function () {
    $(document).on('mouseover', 'area', function(){
        var categoryId = $(this).attr('class');
        var informId = $(this).data('inform');
        var $inform = $('#' + informId);
        $.ajax({
            type : 'GET',
            url : 'categoryShowJsServlet?categoryId=' + categoryId,
            success : function (data) {
                console.log(data);
                // 定义正则表达式，使用全局和非贪婪匹配
                let regex = /src="([^"]*)"/;
                var innerHTML = '';

                for (var i = 0; i < data.length; i++) {
                    let matches = data[i].description.match(regex);
                    console.log(matches[1]);
                    innerHTML += '<div style="vertical-align: middle">';
                    innerHTML += '<img src="' + matches[1] + '" width="90" height="90" style="vertical-align: middle"/>';
                    innerHTML += '&nbsp;&nbsp;'
                    innerHTML += '<span style="vertical-align: middle">&nbsp;' + data[i].name + '</span><br>';
                    innerHTML += '</div>';
                }
                $inform.html(innerHTML);
                $inform.show();
            },
            error : function (errorMsg) {
                console.log(errorMsg);
            }
        });
    });

    $(document).on('mouseleave', '.BIRDS', function () {
        $('.inform').hide();
    });

    $(document).on('mouseleave', '.REPTILES', function () {
        $('.inform').hide();
    });

    $(document).on('mouseleave', '.CATS', function () {
        $('.inform').hide();
    });

    $(document).on('mouseleave', '.BIRDS', function () {
        $('.inform').hide();
    });

    $(document).on('mouseleave', '#inform2', function () {
        $('.inform').hide();
    });

    $(document).on('mouseleave', '#inform3', function () {
        $('.inform').hide();
    });
})