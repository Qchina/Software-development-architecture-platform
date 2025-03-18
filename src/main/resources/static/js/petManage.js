$("#btn-new").click(function () {
    $(".pet-add").show();
});

$("#close-form-btn").click(function () {
    $(".pet-add").hide();
});

//翻页

// 定义表格和翻页链接的变量
var $table = $('#pet-table');
var $prev = $('.nav-bottom div:first');
var $next = $('.nav-bottom div:last');
var $currentPage = $('.current-page');

// 定义每页行数和当前页码的变量
var numRowsPerPage = 9;
var currentPageNum = 1;
var numRows;

function initRow() {
    currentPageNum = 1;
// 根据行数和每页行数计算总页数
    numRows = $table.find('tr:visible').length - 1;
    var numPages = Math.ceil(numRows / numRowsPerPage);

// 隐藏除前numRowsPerPage行之外的所有行
    $table.find('tr:visible:gt(' + (numRowsPerPage) + ')').hide();

// 更新总行数和当前页码
    $('.total').text('共' + numRows + '条 ' + numPages + '页 ');
    $currentPage.text('第' + currentPageNum + '页');
}

initRow();

// 监听上一页链接的点击事件
$prev.click(function () {
    if (currentPageNum > 1) {
        // 隐藏当前行
        var startRow = (currentPageNum - 1) * numRowsPerPage + 1;
        var endRow = (currentPageNum) * numRowsPerPage;
        $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').hide();

        // 显示上一页的行
        startRow = (currentPageNum - 2) * numRowsPerPage + 1;
        endRow = (currentPageNum - 1) * numRowsPerPage;
        $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').show();

        // 更新当前页码
        currentPageNum--;
        $currentPage.text('第' + currentPageNum + '页');
    }
})

//下一页点击事件
$next.click(function () {
    console.log(numRows)
    var numPages = Math.ceil(numRows / numRowsPerPage);
    if (currentPageNum < numPages) {
        // 隐藏当前行
        var startRow = (currentPageNum - 1) * numRowsPerPage + 1;
        var endRow = (currentPageNum) * numRowsPerPage;
        $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').hide();

        // 显示下一页的行
        startRow = (currentPageNum) * numRowsPerPage + 1;
        endRow = (currentPageNum + 1) * numRowsPerPage;
        $table.find('tr:gt(' + (startRow - 1) + '):lt(' + (endRow - startRow + 1) + ')').show();

        // 更新当前页码
        currentPageNum++;
        $currentPage.text('第' + currentPageNum + '页');
    }
});

//关闭编辑窗口
$('#close-edit-form-btn').click(function (){
    $('#edit-form').hide();
})




