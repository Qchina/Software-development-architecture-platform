document.querySelectorAll(".tableCategoryId").forEach(td => console.log(td.innerText));


//删除h2标签
const tdElements = document.querySelectorAll("#tableProductName");
tdElements.forEach(tdElement => {
    const textContent = tdElement.textContent.replace(/<h2>/g, "").replace(/<\/h2>/g, "");
    tdElement.textContent = textContent;
});

//点击筛选按钮
const categorySelect = document.getElementById('category-comboBox');
$("#btn-select").click(function () {
    $('#pet-table tr').show();
    const selectedCategory = categorySelect.value;
    //选择不为空
    if (selectedCategory!== "null") {
        $('#pet-table tr:not(.table-title)').filter(function () {
            return $(this).find('td:nth-child(2)').text() !== selectedCategory;
        }).hide();
    }

    initRow();
});

//重置表单
$('#btn-reset').on('click', function () {
    $('#pet-table tr').show()
    categorySelect.options[0].selected = true;
    initRow();
})

//编辑品种条目
$(document).delegate('#edit-product-btn','click',function (){
    $('#edit-form').show();
    var productId=$(this).closest('tr').find('td:eq(0)').text(),
        categoryId=$(this).closest('tr').find('td:eq(1)').text(),
        name= $(this).closest('tr').find('td:eq(2)').text(),
        descn= $(this).closest('tr').find('td:eq(3)').text()

    $('#edit-productId').val(productId);
    $('#edit-categoryId').val(categoryId);
    $('#edit-name').val(name);
    $('#edit-descn').val(descn);
})

//删除品种条目
$(document).delegate('#delete-product-btn','click',function (){
    console.log($('#tableProductId').text());
    axios.delete('/deleteProduct', {
        params: {
            productId: $(this).closest('tr').find('td:eq(0)').text()
        }
    }).then(response => {
        // 删除成功后的操作
        location.reload();
    }).catch(error => {
        // 删除失败后的操作
    })
})