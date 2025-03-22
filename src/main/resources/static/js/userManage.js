$(document).delegate('#delete-account-btn','click',function (){
    axios.delete('/deleteAccount', {
        params: {
            userid: $(this).closest('tr').find('td:eq(0)').text()
        }
    }).then(response => {
        // 删除成功后的操作
        location.reload();
    }).catch(error => {
        // 删除失败后的操作
    })
})

$(document).delegate('#edit-account-btn','click',function (){
    $('#edit-form').show();
    var userid = $(this).closest('tr').find('td:eq(0)').text(),
        email= $(this).closest('tr').find('td:eq(1)').text(),
        firstName= $(this).closest('tr').find('td:eq(2)').text(),
        lastName= $(this).closest('tr').find('td:eq(3)').text(),
        status=$(this).closest('tr').find('td:eq(4)').text(),
        address1= $(this).closest('tr').find('td:eq(5)').text(),
        address2= $(this).closest('tr').find('td:eq(6)').text(),
        city= $(this).closest('tr').find('td:eq(7)').text(),
        state= $(this).closest('tr').find('td:eq(8)').text(),
        zip= $(this).closest('tr').find('td:eq(9)').text(),
        country= $(this).closest('tr').find('td:eq(10)').text(),
        phone=$(this).closest('tr').find('td:eq(11)').text()

    $('#edit-userid').val(userid);
    $('#edit-email').val(email);
    $('#edit-firstname').val(firstName);
    $('#edit-lastname').val(lastName);
    $('#edit-status').val(status);
    $('#edit-addr1').val(address1);
    $('#edit-addr2').val(address2);
    $('#edit-city').val(city);
    $('#edit-state').val(state);
    $('#edit-zip').val(zip);
    $('#edit-country').val(country);
    $('#edit-phone').val(phone);
})