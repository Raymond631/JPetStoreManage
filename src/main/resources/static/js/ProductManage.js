$(function(){

    let str;
    for (let i=0;i<12;i++) {
        str+=`	  <tr>
       <td>Jonas Alexander</td>
       <td>Developer</td>
       <td>San Francisco</td>
       <td>30</td>
       <td>2010/07/14</td>
       <td><div class="change">修改</div></td>
     </tr>`
    }
    document.getElementById('mytable').innerHTML = str;
    let change = document.getElementsByClassName("change");
    let orderTable = document.getElementById("datatable");

    for(let i=0;i<change.length;i++){
        change[i].onclick=function () {

            console.log(orderTable.rows[i+1].cells[0].innerHTML)
        }
    }
})