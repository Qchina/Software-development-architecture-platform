//筛选宠物

//选择栏，选中种类时，出现对应品种
const categorySelect = document.getElementById('category-comboBox');
const productSelect = document.getElementById('product-comboBox');

categorySelect.addEventListener('change', () => {
    const selectedCategory = categorySelect.value;
    productSelect.options[0].selected = true;
    //筛选对应品种
    for (let i = 0; i < productSelect.options.length; i++) {
        const option = productSelect.options[i];
        const categoryId = option.value.split("_")[1]; // 取出第二个元素，即categoryId
        if (selectedCategory !== "null") {
            if (categoryId === selectedCategory || option.value === "null") {
                option.disabled = false;
                option.hidden = false;
            } else {
                option.disabled = true;
                option.hidden = true;
            }
        }
    }
});

//点击筛选按钮
$("#btn-select").click(function () {
    $('#pet-table tr').show();
    //品种选择不为空
    if (productSelect.value !== "null") {
        const productId = productSelect.value.split("_")[0]; // 取出第一个元素，即productid
        $('#pet-table tr:not(.table-title)').filter(function () {
            return $(this).find('td:nth-child(2)').text() !== productId;
        }).hide();
    } else {
        if (categorySelect.value !== 'null') {
            $('#pet-table tr:not(.table-title)').hide();
            for (let i = 0; i < productSelect.options.length; i++) {
                const option = productSelect.options[i];
                const categoryId = option.value.split("_")[1]; // 取出第二个元素，即categoryId
                if (categoryId === categorySelect.value) {
                    const productId = option.value.split("_")[0];
                    $('#pet-table tr:not(.table-title)').filter(function () {
                        return $(this).find('td:nth-child(2)').text() === productId;
                    }).show();
                }
            }
        }
    }

    initRow();
});

//筛选时去除h2标签
const options = productSelect.querySelectorAll("option");

options.forEach(option => {
    const text = option.textContent;
    option.textContent = text.replace(/<h2>/g, "").replace(/<\/h2>/g, "");
});

//删除宠物条目
$(document).delegate('#delete-item-btn','click',function (){
    axios.delete('/deleteItem', {
        params: {
            itemId: $(this).closest('tr').find('td:eq(0)').text()
        }
    }).then(response => {
        // 删除成功后的操作
        location.reload();
    }).catch(error => {
        // 删除失败后的操作
    })
})

//编辑宠物条目
$(document).delegate('#edit-item-btn','click',function (){
    $('#edit-form').show();
    var itemId = $(this).closest('tr').find('td:eq(0)').text(),
        listPrice= $(this).closest('tr').find('td:eq(2)').text(),
        unitCost= $(this).closest('tr').find('td:eq(3)').text(),
        supplierId= $(this).closest('tr').find('td:eq(4)').text(),
        productId=$(this).closest('tr').find('td:eq(1)').text(),
        status= $(this).closest('tr').find('td:eq(5)').text(),
        attribute1= $(this).closest('tr').find('td:eq(6)').text()

    $('#edit-itemid').val(itemId);
    $('#edit-listprice').val(listPrice);
    $('#edit-unitcost').val(unitCost);
    $('#edit-supplier').val(supplierId);
    $('#edit-productid').val(productId);
    $('#edit-status').val(status);
    $('#edit-attr').val(attribute1);
})

//重置表单
$('#btn-reset').on('click', function () {
    $('#pet-table tr').show()
    productSelect.options[0].selected = true;
    categorySelect.options[0].selected = true;
    initRow();
})