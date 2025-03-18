//重置表单
$('#btn-reset').on('click', function () {
    $('#pet-table tr').show()
    initRow();
})

//编辑品种条目
$(document).delegate('#edit-category-btn','click',function (){
    $('#edit-form').show();
    var categoryId = $(this).closest('tr').find('td:eq(0)').text(),
        name = $(this).closest('tr').find('td:eq(1)').text(),
        descn = $(this).closest('tr').find('td:eq(2)').text();

    $('#edit-categoryid').val(categoryId);
    $('#edit-name').val(name);
    $('#edit-descn').val(descn);
})

//删除品种条目
$(document).delegate('#delete-category-btn','click',function (){
    axios.delete('/deleteCategory', {
        params: {
            categoryId: $(this).closest('tr').find('td:eq(0)').text()
        }
    }).then(response => {
        // 删除成功后的操作
        location.reload();
    }).catch(error => {
        // 删除失败后的操作
    })
})