const table = document.getElementById('tableBody');
const prev = document.getElementById('previous');
const next = document.getElementById('next');

const pages = document.getElementById('pages');
let change = $(".change");
let data = [{
    id:9,
    name:'234',
    Ename:'ray',
    character:'cute',
    disease:'too cute',
    introduction:'this is a cute。。',
    price:10,
    amount:6
},
    {
        id:345,
        name:'312',
        Ename:'jol',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
    {
        id:14,
        name:'111',
        Ename:'pet',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
    {
        id:1,
        name:'145',
        Ename:'mnj',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
];

let Alldata = [{
    id:9,
    name:'234',
    Ename:'ray',
    character:'cute',
    disease:'too cute',
    introduction:'this is a cute。。',
    price:10,
    amount:6
},
    {
        id:345,
        name:'312',
        Ename:'jol',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
    {
        id:14,
        name:'111',
        Ename:'pet',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
    {
        id:1,
        name:'145',
        Ename:'mnj',
        character:'cute',
        disease:'too cute',
        introduction:'this is a cute。。',
        price:10,
        amount:6
    },
];
//默认设定每页十人
let num1 = 3;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

let changeInfoID;

$(function(){
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

    // $.ajax({
    //     url: "/jpetstore/Backstage/getOrderManageData",
    //     type: "get",
    //     dataType: "json",
    //     success: function (obj) {
    //         if (obj.length > 0) {
    //             let str = '';
    //             for (let key in obj) {
    //                 str += `
    //                 <tr>
    //                   <td>
    //                   <th><input type="checkbox" class="flat" name="table_records" value=` + obj[key].id + `></th>
    //                   </td>
    //                   <td>${obj[key].name}</td>
    //                   <td>${obj[key].position}</td>
    //                   <td>${obj[key].phone}</td>
    //                   <td>${obj[key].category}</td>
    //                   <td>${obj[key].amount}</td>
    //                   <td>${obj[key].date}</td>
    //                 `;
    //                 if (obj[key].status === "已发货") {
    //                     str += `<td>已发货<label class="OrderT"></label></td></tr>`
    //                 } else if (obj[key].status === "未发货") {
    //                     str += `<td>未发货<label class="OrderF"></label></td></tr>`
    //                 } else {
    //                     str += `<td>已接收<label class="OrderFinish"></label></td></tr>`
    //                 }
    //             }
    //             table.innerHTML = str;
    //         }
    //     }
    // })
})


// function EnsureOrder() {                   //注册事件  处理程序
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
//     }
// })
// };

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
    if (data.length - num1 * (page - 1) >= 3) {
        num2 = 3;
    } else {
        num2 = data.length - num1 * (page - 1);
    }

    //渲染该页对应的数据
    let str ='';
    for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
        str+=` 
                <td>${data[i].id}</td>
                <td>${data[i].name}</td>
                <td>${data[i].Ename}</td>
                <td>${data[i].price}</td>
                <td>${data[i].amount}</td>`
        str+=`<td class="change" value="${data[i].id}" onclick="changeInfomation(this)">修改</td></tr>`;
    }
    table.innerHTML =str;
}
render();

//点击修改在上方显示数据
function changeInfomation(val){
    var value1 = $(val).parent().find("td");
    changeInfoID = value1.eq(0).text();
    let targetID;
    data.forEach((e) => {
        targetID = e.id;
        if (targetID==changeInfoID) {
            $("#idNoChange").text(targetID);
            $("#nameChange").val(e.name);
            $("#EnameChange").val(e.Ename);
            $("#characterChange").val(targetID);
            $("#diseaseChange").val(targetID);
            $("#introductionChange").val(targetID);
            $("#priceChange").val(targetID);
            $("#quantityChange").val(e.name);
        }
    });
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
    console.log(res)
    let searchArr = Alldata;
    let name;
    searchArr.forEach((e) => {
        name = e.name;
        if (name.includes(res)) {
            if (data.indexOf(e) == "-1") {
                data.push(e);
            }
        }
    });
    console.log(data);
    page=1;
    render();
}


//修改数据提交
function changeSubmit(){
    let ID= $("#idNoChange").text();
    let name= $("#nameChange").val();
    let Ename =  $("#EnameChange").val();
    let character = $("#characterChange").val();
    let disease = $("#diseaseChange").val();
    let introduction = $("#introductionChange").val();
    let price = $("#priceChange").val();
    let quantity =$("#quantityChange").val();
    console.log(ID);
    console.log(name)
}

function changeReset(){
    // $("#idNoChange").text('不可修改');
    $("#nameChange").val('');
    $("#EnameChange").val('');
    $("#characterChange").val('');
    $("#diseaseChange").val('');
    $("#introductionChange").val('');
    $("#priceChange").val('');
    $("#quantityChange").val('');
}

//新数据添加
function newSubmit(){
    let name= $("#nameNew").val();
    let Ename =  $("#EnameNew").val();
    let character = $("#characterNew").val();
    let disease = $("#diseaseNew").val();
    let introduction = $("#introductionNew").val();
    let price = $("#priceNew").val();
    let quantity =$("#quantityNew").val();
    console.log(ID);
    console.log(name)
}

function newReset(){
    $("#nameNew").val('');
    $("#EnameNew").val('');
    $("#characterNew").val('');
    $("#diseaseNew").val('');
    $("#introductionNew").val('');
    $("#priceNew").val('');
    $("#quantityNew").val('');
}