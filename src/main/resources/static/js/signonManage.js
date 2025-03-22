$(document).delegate('#delete-signon-btn','click',function (){
    axios.delete('/deleteSignon', {
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

$(document).delegate('#edit-signon-btn','click',function (){
    $('#edit-form').show();
    var username = $(this).closest('tr').find('td:eq(0)').text(),
        password= $(this).closest('tr').find('td:eq(1)').text()
    $('#edit-username').val(username);
    $('#edit-password').val(password);

})