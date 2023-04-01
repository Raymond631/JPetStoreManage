
const table = document.getElementById('tableBody');
const pages = document.getElementById('pages');
let change = $(".change");

let Alldata = [{
        user_id:1,
        nickname:'pr',
        receiver_name:'张三',
        receiver_phone:'15852358372',
        receiver_address:'中南大学'
    },
    {
        user_id:15,
        nickname:'大白小黑',
        receiver_name:'',
        receiver_phone:'',
        receiver_address:''
    },
    {
        user_id:16,
        nickname:'7686303953',
        receiver_name:'',
        receiver_phone:'',
        receiver_address:''
    },
    {
        user_id:17,
        nickname:'大白加小黑',
        receiver_name:'',
        receiver_phone:'',
        receiver_address:''
    }
];
//默认设定每页十人
let num1 = 10;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;

const render = function (){
    // 判断当前选择的页码对应的人数
    if (Alldata.length - num1 * (page - 1) >= 10) {
        num2 = 10;
    } else {
        num2 = Alldata.length - num1 * (page - 1);
    }
    //渲染该页对应的数据
    let str ='';
    for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
        str+=` 
                <td>${Alldata[i].user_id}</td>
                <td>${Alldata[i].nickname}</td>
                <td>${Alldata[i].receiver_name}</td>
                <td>${Alldata[i].receiver_phone}</td>
                <td>${Alldata[i].receiver_address}</td>`
        str+=`<td class="change" onclick="changeInfomation(this)">修改</td></tr>`;
    }
    table.innerHTML =str;
}
render();

function changeInfomation(val){
    for(let i = 0;i <= Alldata.length;i++) {
        $(val).parent().find("td").eq(i).html("");
        $(val).parent().find("td").eq(i).append("<input value=''/>");
    }
    $(val).parent().find("td").eq(5).html("保存");
    $(val).parent().find("td").eq(5).removeAttr("onclick");
    setTimeout(function (){
        $(val).parent().find("td").eq(5).attr("onclick","saveInfomation(this);");
    },100)
}
function saveInfomation(inp){
    let inputs = $(inp).parent().find("input");
    let val = $(inp).parent().find("td");
    for (let i = 0;i < inputs.length;i++){
        val.eq(i).html(inputs[i].value)
        inputs[i].remove();
    }
    val.eq(5).html("修改");
    val.eq(5).removeAttr("onclick");
    setTimeout(function (){
        val.eq(5).attr("onclick","changeInfomation(this);");
    },100)

}