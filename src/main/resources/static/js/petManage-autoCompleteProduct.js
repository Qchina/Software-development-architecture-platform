
    //自动补全
    $(document).ready(function () {
    // 防抖函数
    function debounce(func, wait) {
        let timeout;
        return function () {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, arguments), wait);
        };
    }

    // 搜索框输入事件
    $('#search-input').on('input', debounce(function () {
    var keyword = $(this).val().trim();
    if (keyword) {
    $.ajax({
    url: '/petManage/searchProducts',
    method: 'GET',
    data: {keyword: keyword},
    success: function (data) {
    var $list = $('#auto-complete-list');
    $list.empty();

    if (data.length > 0) {
    data.forEach(function (product) {
    $list.append(`
                                    <li data-name="${product.name}">
                                        ${product.name}
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
    $('#auto-complete-list').on('click', 'li', function () {
    var name = $(this).data('name');
    $('#search-input').val(name);
    $('#auto-complete').hide();
});

    // 点击页面其他地方隐藏自动补全
    $(document).on('click', function (e) {
    if (!$(e.target).closest('.pet-search').length) {
    $('#auto-complete').hide();
}
});

    // 原有的搜索按钮点击事件保持不变
    $(document).ready(function () {
    $('#search-btn').on('click', function () {
    var name = $('#search-input').val();
    if (name) {
    window.location.href = '/petManage/searchProductByName?name=' + encodeURIComponent(name);
} else {
    alert("该商品不存在!");
}
});
});
    $('#btn-reset').on('click', function () {
    $('*').css('transition', 'none');
// 添加随机参数确保从服务器获取最新数据
    window.location.href = '/petManage/productManage?t=' + new Date().getTime();
});
});
