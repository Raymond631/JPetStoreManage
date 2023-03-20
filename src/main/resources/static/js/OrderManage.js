$(function () {
    $.ajax({
        url: "/jpetstore/Backstage/getOrderManageData",
        type: "get",
        dataType: "json",
        success: function (obj) {
            console.log("开始")
            console.log(obj)
            if (obj.length > 0) {
                let str = '';
                for (let key in obj) {
                    str += `
                    <tr>
                      <td>
                      <th><input type="checkbox" class="flat" name="table_records" value=` + obj[key].id + `></th>
                      </td>
                      <td>${obj[key].name}</td>
                      <td>${obj[key].position}</td>
                      <td>${obj[key].category}</td>
                      <td>${obj[key].amount}</td>
                      <td>${obj[key].phone}</td>
                      <td>${obj[key].date}</td>
                    `;
                    if (obj[key].status === "已发货") {
                        str += `<td>已发货<label class="OrderT"></label></td></tr>`
                    } else if (obj[key].status === "未发货") {
                        str += `<td>未发货<label class="OrderF"></label></td></tr>`
                    } else {
                        str += `<td>已接收<label class="OrderFinish"></label></td></tr>`
                    }
                    document.getElementById('OrderTable').innerHTML = str;
                }
            }
        }
    })
});


function EnsureOrder() {                   //注册事件  处理程序
    let order = document.getElementsByName("table_records");
    let check = [];
    for (let i in order) {
        if (order[i].checked)
            check.push(order[i].value);
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

function CancelOrder() {
    let order = document.getElementsByName("table_records");
    let check = [];
    for (let i in order) {
        if (order[i].checked)
            check.push(order[i].value);
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
