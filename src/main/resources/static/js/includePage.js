//左侧导航栏 宠物管理
$(document).ready(function () {
    var petManageState = localStorage.getItem("petManageState");
    if (petManageState === "visible") {
        $("#petItemManage").show();
        $("#petProductManage").show();
        $("#petCategoryManage").show();
    } else {
        $("#petItemManage").hide();
        $("#petProductManage").hide();
        $("#petCategoryManage").hide();
    }
});

//左边导航栏高度适应右边
// 定义高度变化时要调用的函数
function adjustNavHeight() {
    var height = $('.w').height();
    $('.nav-left').height(height);
}

// 创建新的MutationObserver
var observer = new MutationObserver(function (mutations) {
    // 循环遍历变异
    mutations.forEach(function (mutation) {
        // 调用函数以调整导航高度
        adjustNavHeight();
    });
});

// 开始观察具有“w”类的元素的“style”属性的更改
observer.observe(document.body, {
    attributes: true,
    attributeFilter: ['style'],
    subtree: true
});


$("#petManage").on("click", function () {
    var x = document.getElementById("petItemManage");
    var y = document.getElementById("petProductManage");
    var z = document.getElementById("petCategoryManage");
    if (x.style.display === "none") {
        x.style.display = "block";
        y.style.display = "block";
        z.style.display = "block";
        localStorage.setItem("petManageState", "visible");
    } else {
        x.style.display = "none";
        y.style.display = "none";
        z.style.display = "none";
        localStorage.setItem("petManageState", "hidden");
    }
});

//图标动画
//弹跳动画
function bounce(e, eIcon) {
    e.mouseenter(function () {
        if (!$(this).hasClass('animated')) {
            $(this).addClass('animated');
            eIcon.css('animation', 'bounce 1s 1');
            setTimeout(function () {
                eIcon.css('animation', '');
                e.removeClass('animated');
            }, 1000);
        }
    });
}

$('.dashboard').mouseenter(bounce($('.dashboard'), $('#dashboardIcon')));
$('.petProductManage').mouseenter(bounce($('.petProductManage'), $('#petProductManageIcon')));
$('.petItemManage').mouseenter(bounce($('.petItemManage'), $('#petItemManageIcon')));
$('.userManage').mouseenter(bounce($('.userManage'), $('#userManageIcon')));
$('.orderManage').mouseenter(bounce($('.orderManage'), $('#orderManageIcon')));
