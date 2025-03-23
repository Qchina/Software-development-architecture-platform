$(document).ready(function () {
    // 定义表格和翻页链接的变量
    var $table = $('#order-table');
    var $prev = $('.nav-bottom div:first');
    var $next = $('.nav-bottom div:last');
    var $currentPage = $('.current-page');

    // 定义每页行数和当前页码的变量
    var numRowsPerPage = 8;
    var currentPageNum = 1;
    var numRows;

    // 初始化分页
    function initRow() {
        currentPageNum = 1;
        // 根据行数和每页行数计算总页数
        numRows = $table.find('tr:visible').length - 1; // 排除表头行
        var numPages = Math.ceil(numRows / numRowsPerPage);

        // 隐藏除前 numRowsPerPage 行之外的所有行
        $table.find('tr:visible:gt(' + numRowsPerPage + ')').hide();

        // 更新总行数和当前页码
        $('.total').text('共' + numRows + '条 ' + numPages + '页 ');
        $currentPage.text('第' + currentPageNum + '页');
    }

    // 初始化分页
    initRow();

    // 上一页点击事件
    $prev.click(function () {
        if (currentPageNum > 1) {
            // 隐藏当前页的行
            var startRow = (currentPageNum - 1) * numRowsPerPage + 1;
            var endRow = currentPageNum * numRowsPerPage;
            $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').hide();

            // 显示上一页的行
            startRow = (currentPageNum - 2) * numRowsPerPage + 1;
            endRow = (currentPageNum - 1) * numRowsPerPage;
            $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').show();

            // 更新当前页码
            currentPageNum--;
            $currentPage.text('第' + currentPageNum + '页');
        }
    });

    // 下一页点击事件
    $next.click(function () {
        var numPages = Math.ceil(numRows / numRowsPerPage);
        if (currentPageNum < numPages) {
            // 隐藏当前页的行
            var startRow = (currentPageNum - 1) * numRowsPerPage + 1;
            var endRow = currentPageNum * numRowsPerPage;
            $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').hide();

            // 显示下一页的行
            startRow = currentPageNum * numRowsPerPage + 1;
            endRow = (currentPageNum + 1) * numRowsPerPage;
            $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').show();

            // 更新当前页码
            currentPageNum++;
            $currentPage.text('第' + currentPageNum + '页');
        }
    });

    // 重置筛选条件
    $('#btn-reset').on('click', function () {
        $('#order-table tr').show(); // 显示所有行
        initRow(); // 重新初始化分页
    });

    // 编辑订单条目
    $(document).delegate('#edit-order-btn', 'click', function () {
        $('#edit-form').show(); // 显示编辑表单

        // 获取当前行的数据
        var orderId = $(this).closest('tr').find('td:eq(0)').text(),
            userId = $(this).closest('tr').find('td:eq(1)').text(),
            orderDate = $(this).closest('tr').find('td:eq(2)').text(),
            totalAmount = $(this).closest('tr').find('td:eq(3)').text(),
            status = $(this).closest('tr').find('td:eq(4)').text(),
            add1=$(this).closest('tr').find('td:eq(5)').text(),
            add2=$(this).closest('tr').find('td:eq(6)').text();

        // 将数据填充到编辑表单中
        $('#edit-orderId').val(orderId);
        $('#edit-userId').val(userId);
        $('#edit-orderDate').val(orderDate);
        $('#edit-totalAmount').val(totalAmount);
        $('#edit-status').val(status);
        $('#edit-add1').val(add1);
        $('#edit-add2').val(add2);
    });

    //发货
    function handleDispatch() {
        // 弹出输入框，获取物流单号
        const logisticsNumber = prompt('Please enter logistics order number:');
        if (logisticsNumber === null || logisticsNumber.trim() === '') {
            alert('Logistics order number is required.');
            return false; // 阻止表单提交
        }

        // 动态创建一个隐藏的 input 元素，用于传递物流单号
        const logisticsInput = document.createElement('input');
        logisticsInput.type = 'hidden';
        logisticsInput.name = 'logisticsNumber';
        logisticsInput.value = logisticsNumber;
        document.querySelector('form').appendChild(logisticsInput);

        return true; // 允许表单提交
    }

    // 删除订单条目
    // $(document).delegate('#delete-order-btn', 'click', function () {
    //     var orderId = $(this).closest('tr').find('td:eq(0)').text();
    //     // 发送删除请求
    //     axios.delete('/deleteOrder', {
    //         params: {
    //             orderId: orderId
    //         }
    //     }).then(response => {
    //         // 删除成功后的操作
    //         location.reload(); // 刷新页面
    //     }).catch(error => {
    //         // 删除失败后的操作
    //         alert('删除失败，请重试！');
    //     });
    // });

    // 关闭编辑窗口
    $('#close-edit-form-btn').click(function () {
        $('#edit-form').hide();
    });

    //搜索
    $(document).ready(function () {
        $('#search-btn').on('click', function () {
            var orderId = $('#search-input').val();
            if (orderId) {
                window.location.href = '/orderManage/getOrderById?orderId=' + encodeURIComponent(orderId);
            }else{
                alert("该订单不存在!");
            }
        });
    });

    //发货按钮

});