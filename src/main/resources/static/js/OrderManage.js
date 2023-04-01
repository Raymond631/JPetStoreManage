const table = document.getElementById('tableBody');
const prev = document.getElementById('previous');
const next = document.getElementById('next');

const pages = document.getElementById('pages');
// let change = $(".change");
let data = [];

let allData = [];
//默认设定每页十人
let num1 = 11;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

let changePositionID;

$(function(){
    $.ajax({
        url: "/jpetstore/orders",
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
    let settings = {
        "url": "/jpetstore/orders/status",
        "method": "PUT",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify(check),
    };

    $.ajax(settings).done(function (response) {
        if(response.code===200){
            for(let i=0;i<check.length;i++){
                allData.forEach((e) => {
                    if (parseInt(check[i]) === e.orderId) {
                        e.status=2;
                    }
                });
                data.forEach((e) => {
                    if (parseInt(check[i]) === e.orderId) {
                        e.status=2;
                    }
                });
            }
            render();
        }
        else{
            console.log(response)
        }
    });

}

function CancelOrder() {
    let order = document.getElementsByName("change");
    let check = [];
    for (let i in order) {
        if (order[i].checked)
            check.push(order[i].id);
    }
    console.log(check)
    let settings = {
        "url": "/jpetstore/orders",
        "method": "DELETE",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify(check),
    };

    $.ajax(settings).done(function (response) {
        if(response.code===200){
            for(let i=0;i<check.length;i++){
                // 将删除id了的从数组中移除
                allData = allData.reduce((total, current) => {
                    current.orderId !== parseInt(check[i]) && total.push(current);
                    return total;
                }, []);
                data = data.reduce((total, current) => {
                    current.orderId !== parseInt(check[i])  && total.push(current);
                    return total;
                }, []);
            }
            render();
        }
        else{
            console.log(response)
        }
    });
}

//用于展示数据
const render = function () {
        if (data.length - num1 * (page - 1) >= 11) {
            num2 = 11;
        } else {
            num2 = data.length - num1 * (page - 1);
        }
        console.log(data)
        //渲染该页对应的数据
        let str ='';
        for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
            str+=` <td><th><input type="checkbox" class="choose" name="change" id="${data[i].orderId}"></th></td>
                <td>${data[i].receiverName}</td>
                <td>${data[i].receiverAddress}</td>
                <td>${data[i].receiverPhone}</td>
                <td>${data[i].items}</td>
                <td>${data[i].orderCost}</td>
                <td>${data[i].orderTime}</td>`
            if (data[i].status === 2) {
                str += `<td>已发货<label class="OrderT"></label></td>`
            } else if (data[i].status === 1) {
                str += `<td>未发货<label class="OrderF"></label></td>`
            } else {
                str += `<td>已接收<label class="OrderFinish"></label></td>`
            }
            str+=`<td class="change" value="${data[i].orderId}" onclick="changePosition(this)">修改</td></tr>`;
        }
        table.innerHTML =str;
}
render();

//点击修改之后打开弹窗
function changePosition(val){
    document.getElementById('modal').className="changePosition";
    let value1 = $(val).parent().find("td");
    changePositionID = value1.eq(8).attr("value");
    $('#receiverName').val(value1.eq(1).text());
    $('#receiverAddress').val(value1.eq(2).text());
    $('#receiverPhone').val(value1.eq(3).text());
}

//修改完成之后点击确认
function fnClose(){
    let receiverName = $("#receiverName").val();
    let receiverPhone = $("#receiverPhone").val();
    let receiverAddress = $("#receiverAddress").val();
    document.getElementById('confirming').className="afterConfirm";
    setTimeout(confirming,3000);
    function confirming(){
        document.getElementById('confirming').className="hidden";
        document.getElementById('modal').className="hidden";
    }

    let settings = {
        "url": `/jpetstore/orders/${changePositionID}/receiver`,
        "method": "PUT",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data":JSON.stringify({
            receiverName:receiverName,
            receiverPhone:receiverPhone,
            receiverAddress:receiverAddress
        }),
    };

    $.ajax(settings).done(function (response) {
        if(response.code===200){
            allData.forEach((e) => {
                if (parseInt(changePositionID) === e.orderId) {
                    e.receiverName=receiverName;
                    e.receiverPhone=receiverPhone;
                    e.receiverAddress=receiverAddress;
                }
            });
            data.forEach((e) => {
                if (parseInt(changePositionID) === e.orderId) {
                    e.receiverName=receiverName;
                    e.receiverPhone=receiverPhone;
                    e.receiverAddress=receiverAddress;
                }
            });
            render();
        }
        else{
            console.log(response)
        }
    });
}

//弹窗中点击取消
function fnCancel(){
    document.getElementById('modal').className="hidden";
}

//绑定向前翻页事件
previous.onclick = function () {
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
    if (page < data.length / num1) {
        page++;
        pages.innerHTML=page;
        render();
    } else {
        alert('当前为最后一页！')
    }
}

//搜索
function search(){
    data=[];
    let res1 = $("#searchContent").val();
    const res = res1.replace(/\s/gi, "");
    let searchArr = allData;
    let receiverName;
    searchArr.forEach((e) => {
        receiverName = e.receiverName;
        if (receiverName.includes(res)) {
            data.push(e);
        }
    });
    page=1;
    render();
}


   