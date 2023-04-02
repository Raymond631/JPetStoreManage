const table = document.getElementById('tableBody');
const previous = document.getElementById('previous');
const next = document.getElementById('next');
const pages = document.getElementById('pages');


let change = $(".change");

let data = [];

let allData = [];
//默认设定每页十人
let num1 = 11;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

let changeInfoID;

$(function(){
    if(document.cookie.indexOf('token')===-1){
        console.log("未登录")
        window.location.href="http://localhost:8888/jpetstore/NotLogin.html"
    }
    else{
        collapse();
        $.ajax({
            url:  "/jpetstore/pets",
            type: "get",
            dataType: "json",
            success: function (obj) {
                console.log(obj)
                if(obj.code===200){
                    if (obj.data.length > 0) {
                        for (let key in obj.data) {
                            data.push(obj.data[key]);
                            allData.push(obj.data[key]);
                        }
                        console.log(data)
                        render();
                    }
                }
                else{
                    obj.code
                }
            }
        })
    }
})

function collapse() {
    //设置可折叠
    $('.collapse-link').on('click', function () {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');

        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function () {
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200);
            $BOX_PANEL.css('height', 'auto');
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
}


function addNew() {
    window.location.href="http://localhost:8888/jpetstore/ProductNew.html"
}

// function CancelOrder() {
// let order = document.getElementsByName("change");
// let check = [];
// for (let i in order) {
//     if (order[i].checked)
//         check.push(order[i].id);
// }
// console.log(check)
// $.ajax({
//     url: "../Pet/getSearchData?keyword=",
//     type: "get",
//     dataType: "json",
//     success: function (obj) {
//
//     }
// })
// }

for (let i = 0; i < change.length; i++) {
    change.eq(i).click(function () {
        console.log(i)
    })
}


const render = function () {
    // table.innerHTML =
    //     ``;
    // 判断当前选择的页码对应的人数
    if (data.length - num1 * (page - 1) >= 11) {
        num2 = 11;
    } else {
        num2 = data.length - num1 * (page - 1);
    }

    //渲染该页对应的数据
    let str ='';
    for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
        str+=` 
                <td>${data[i].productId}</td>
                <td>${data[i].productNameChinese}</td>
                <td>${data[i].productNameEnglish}</td>
                <td>${data[i].category}</td>`
        str+=`<td class="change" value="${data[i].id}" onclick="productDetails(this)">查看详情</td></tr>`;
    }
       table.innerHTML =str;
}
render();

//点击修改在上方显示数据
function productDetails(val){
    var value1 = $(val).parent().find("td");
    changeInfoID = value1.eq(0).text();
    sessionStorage.setItem('ProductID', changeInfoID)
    window.location.href="http://localhost:8888/jpetstore/ProductDetails.html"

}

//绑定向前翻页事件
previous.onclick = function () {
    console.log("previous")
    if (page > 1) {
        page--;
        pages.innerHTML=page;
        render();
    } else {
        alert('当前为第一页！')
    }
}

//绑定向后翻页事件
next.onclick = function () {
    console.log(data.length/num1)
    if (page < data.length / num1) {
        page++;
        pages.innerHTML=page;
        render();
    } else {
        alert('当前为最后一页！')
    }
}

function search(){
    data=[];
    let res1 = $("#searchContent").val();
    const res = res1.replace(/\s/gi, "");
    let searchArr = allData;
    let productId;
    let productNameChinese;
    let productNameEnglish;
    let category;
    searchArr.forEach((e) => {
        productId = e.productId;
        productNameChinese = e.productNameChinese;
        productNameEnglish = e.productNameEnglish;
        category = e.category;
        if (productNameChinese.includes(res)||productNameEnglish.includes(res)||category.includes(res)) {
            data.push(e);
        }
    });
    page=1;
    render();
}



