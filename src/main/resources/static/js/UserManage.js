const table = document.getElementById('tableBody');
const prev = document.getElementById('previous');
const next = document.getElementById('next');
const pages = document.getElementById('pages');
let change = $(".change");

//所有数据
let allData = [];
//当前显示数据
let data = [];
//默认设定每页十人
let num1 = 11;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

$(function(){
    $.ajax({
        url: "/jpetstore/users/all",
        type: "get",
        dataType: "json",
        success: function (obj) {
            if (obj.data.length > 0) {
                for (let key in obj.data) {
                    data.push(obj.data[key]);
                    allData.push(obj.data[key]);
                }
                render();
            }
        }
    })
})


const render = function (){
    // 判断当前选择的页码对应的人数
    if (data.length - num1 * (page - 1) >= 11) {
        num2 = 11;
    } else {
        num2 = data.length - num1 * (page - 1);
    }
    console.log(data)
    //渲染该页对应的数据
    let str ='';
    for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
        str+=` 
                <td>${data[i].userId}</td>
                <td>${data[i].nickname}</td>
                <td>${data[i].receiverName}</td>
                <td>${data[i].receiverPhone}</td>
                <td>${data[i].receiverAddress}</td>
`
        str+=`<td class="change" value="${data[i].userId}" onclick="changeInformation(this)">修改</td></tr>`;
    }
    table.innerHTML =str;
}
render();

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
    if (page < data.length/num1) {
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
    let nickname;
    searchArr.forEach((e) => {
        nickname = e.nickname;
        if (nickname.includes(res)){
            data.push(e);
        }
    });
    page=1;
    render();
}

function changeInformation(val){
    let userId = $(val).parent().find("td").eq(0).text()
    for(let i = 1;i <= 4;i++) {
        let value = $(val).parent().find("td").eq(i).text()
        $(val).parent().find("td").eq(i).html("");
        $(val).parent().find("td").eq(i).append(`<input value=${value}>`);
    }
    $(val).parent().find("td").eq(5).html("保存");
    $(val).parent().find("td").eq(5).removeAttr("onclick");
    setTimeout(function (){
        $(val).parent().find("td").eq(5).attr("onclick",`saveInformation(this,${userId});`);
    },100)
}

function saveInformation(inp,id){
    console.log(id)
    let inputs = $(inp).parent().find("input");
    let val = $(inp).parent().find("td");
    let nickname = inputs[0].value;
    let receiverName = inputs[1].value;
    let receiverPhone = inputs[2].value;
    let receiverAddress = inputs[3].value;

    for (let i = 0;i < inputs.length;i++){
        val.eq(i+1).html(inputs[i].value)
        inputs[i].remove();
    }
    val.eq(5).html("修改");
    val.eq(5).removeAttr("onclick");
    setTimeout(function (){
        val.eq(5).attr("onclick","changeInformation(this);");
    },100)

    let settings = {
        "url": "/jpetstore/users",
        "method": "PUT",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            userId:parseInt(id),
            nickname:nickname,
            receiverName:receiverName,
            receiverPhone:receiverPhone,
            receiverAddress:receiverAddress
        }),
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
    });
}