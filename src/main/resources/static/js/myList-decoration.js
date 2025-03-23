$(function () {
   // 初始化界面
   $('li').hide().each(function (index) {
       $(this).delay(500 * index).fadeIn(1500);
   });
});