const table = document.getElementById('tableBody');
const prev = document.getElementById('previous');
const next = document.getElementById('next');

const pages = document.getElementById('pages');
let change = $(".change");
let data = [{}];

let allData = [{}];
//默认设定每页十人
let num1 = 11;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

let changePositionID;

$(function(){
    $.ajax({
        url: "/jpetstore/Backstage/getOrderManageData",
        type: "get",
        dataType: "json",
        success: function (obj) {
            if (obj.length > 0) {
                for (let key in obj) {
                    data.push(obj[key]);
                    allData.push(obj[key]);
                }
                render();
            }
        }
    })
})


function EnsureOrder() {                   //注册事件  处理程序
    let order = document.getElementsByName("change");
    let check = [];
    for (let i in order) {
        if (order[i].checked)
            check.push(order[i].id);
    }
    console.log(check)
    // $.ajax({
    //     url: "../Pet/getSearchData?keyword=",
    //     type: "get",
    //     dataType: "json",
    //     success: function (obj) {
    //     }
    // })
};

function CancelOrder() {
    let order = document.getElementsByName("change");
    let check = [];
    for (let i in order) {
        if (order[i].checked)
            check.push(order[i].id);
    }
    console.log(check)
    // $.ajax({
    //     url: "../Pet/getSearchData?keyword=",
    //     type: "get",
    //     dataType: "json",
    //     success: function (obj) {
    //
    //     }
    // })

}

for (let i = 0; i < change.length; i++) {
    change.eq(i).click(function () {
        console.log(i)
    })
}


const render = function () {
    // table.innerHTML =
    //     ``;
    //判断当前选择的页码对应的人数
    if (data.length - num1 * (page - 1) >= 11) {
        num2 = 11;
    } else {
        num2 = data.length - num1 * (page - 1);
    }

    //渲染该页对应的数据
    let str ='';
    for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
        str+=` <td><th><input type="checkbox" class="choose" name="change" id="${data[i].id}"></th></td>
                <td>${data[i].name}</td>
                <td>${data[i].position}</td>
                <td>${data[i].phone}</td>
                <td>${data[i].category}</td>
                <td>${data[i].amount}</td>
                <td>${data[i].date}</td>`
        if (data[i].status === "已发货") {
            str += `<td>已发货<label class="OrderT"></label></td>`
        } else if (data[i].status === "未发货") {
            str += `<td>未发货<label class="OrderF"></label></td>`
        } else {
            str += `<td>已接收<label class="OrderFinish"></label></td>`
        }
        str+=`<td class="change" value="${data[i].id}" onclick="changePosition(this)">修改</td></tr>`;
    }
    table.innerHTML =str;
}
render();

//点击修改之后打开弹窗
function changePosition(val){
    console.log("change")
    document.getElementById('modal').className="changePosition";
    var value1 = $(val).parent().parent().find("td");
    changePositionID = value1.eq(8).attr("value");
}

//修改完成之后点击确认
function fnClose(){
    console.log(changePositionID);
    let newPosition = document.getElementById("newPosition");
    let newInput = newPosition.value;
    console.log(newInput)
    document.getElementById('confirming').className="afterConfirm";
    console.log(document.getElementById('confirming').className)
    setTimeout(confirming,3000);
    function confirming(){
        newPosition.value='';
        document.getElementById('confirming').className="hidden";
        document.getElementById('modal').className="hidden";

    }
    // $.ajax({
    //     url: "../Pet/getSearchData",
    //     type: "post",
    //     ContentType:application/json,
    //     dataType: "json",
    //     success: function (obj) {

    //     }
    // })
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
    let searchArr = allData;
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


   