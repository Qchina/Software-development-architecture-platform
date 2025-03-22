//自动补全
$(document).ready(function () {
    // 防抖函数
    function debounce(func, wait) {
        let timeout;
        return function() {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, arguments), wait);
        };
    }

    // 搜索框输入事件
    $('#search-itemid-input').on('input', debounce(function() {
        var keyword = $(this).val().trim();
        if (keyword) {
            $.ajax({
                url: '/petManage/searchItems',
                method: 'GET',
                data: { keyword: keyword },
                success: function(data) {
                    var $list = $('#auto-complete-list');
                    $list.empty();

                    if (data.length > 0) {
                        data.forEach(function(item) {
                            $list.append(`
                                    <li data-itemid="${item.itemId}">
                                        ${item.itemId} - ${item.productId}
                                    </li>
                                `);
                        });
                        $('#auto-complete').show();
                    } else {
                        $('#auto-complete').hide();
                    }
                }
            });
        } else {
            $('#auto-complete').hide();
        }
    }, 300));

    // 点击候选项事件
    $('#auto-complete-list').on('click', 'li', function() {
        var itemId = $(this).data('itemid');
        $('#search-itemid-input').val(itemId);
        $('#auto-complete').hide();
    });

    // 点击页面其他地方隐藏自动补全
    $(document).on('click', function(e) {
        if (!$(e.target).closest('.pet-search').length) {
            $('#auto-complete').hide();
        }
    });

    // 原有的搜索按钮点击事件保持不变
    $('#search-itemid-btn').on('click', function () {
        var itemId = $('#search-itemid-input').val();
        if (itemId) {
            window.location.href = '/petManage/getItemManageById?itemId=' + encodeURIComponent(itemId);
        }
    });
    $('#btn-reset').on('click', function () {
        $('*').css('transition', 'none');
        // 添加随机参数确保从服务器获取最新数据
        window.location.href = '/petManage/itemManage?t=' + new Date().getTime();
    });
});