//用于存储petItemList
let data = [];
let itemID = [];

$(function(){
    if(document.cookie.indexOf('token')===-1){
        console.log("未登录")
        window.location.href="http://localhost:8888/jpetstore/NotLogin.html"
    }
    else{
        let productID = sessionStorage.getItem('ProductID')
        console.log(productID)
        let settings = {
            "url": `/jpetstore/pets/${productID}`,
            "method": "GET",
            "timeout": 0,
            "headers": {
            },
        };
        $.ajax(settings).done(function (response) {
            if(response.code===200){
                $('#idNoChange').val(response.data.productId);
                $('#category').val(response.data.category);
                $('#productNameChinese').val(response.data.productNameChinese);
                $('#productNameEnglish').val(response.data.productNameEnglish);
                $('#productCharacter').val(response.data.productCharacter);
                $('#productAncestry').val(response.data.productAncestry);
                $('#productDisease').val(response.data.productDisease);
                $('#productLife').val(response.data.productLife);
                $('#productIntroduce').val(response.data.productIntroduce);
                $('#changeImagePosition').attr("src",`/jpetstore/image/look/${response.data.productImage}`)
                console.log(response.data.productImage)
                if (response.data.petItemList.length > 0) {
                    for (let key in response.data.petItemList) {
                        data.push(response.data.petItemList[key]);
                        itemID.push(response.data.petItemList[key].itemId.toString());
                    }
                    showPetItem()
                }
            }
            else{
                console.log(response)
            }
        });
    }
})


function showPetItem(){
    console.log(data);
    console.log(itemID);
    let str ='';
    for (let i = 0; i < data.length; i++) {
        str+=`
<div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>数据${i+1}</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <br />
                  
                                <div data-parsley-validate class="form-horizontal form-label-left">

                                    <div>
                                        <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">小类ID</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="${data[i].itemId}" value="${data[i].itemId}" readonly="readonly"></input>
                                    </div>
                                    </div>
                                    <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">具体类别</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="${data[i].itemId}" value="${data[i].itemSpecification}"></input>
                                    </div>
                                    </div>
                                    <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">价格</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="${data[i].itemId}" value="${data[i].itemPrice}"></input>
                                    </div>
                                    </div>
                                    <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">数量</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="${data[i].itemId}" value="${data[i].itemStock}"></input>
                                    </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        `
    }
    str+=`
        <div class="item form-group">
        <div class="col-md-6 col-sm-6 offset-md-3">
        <button onclick="backToAll()" class="reset">返回</button>
        <button onclick="changeSubmit()" class="ensure" style="margin-left: 200px">更新</button>
        </div>
        </div>
        `
    $('#father').after(str);
    collapse()
}

//提交表单
function changeSubmit(){
    let petItemList=[];
    let productId = $('#productId').val();
    let category = $('#category').val();
    let productNameChinese = $('#productNameChinese').val();
    let productNameEnglish = $('#productNameEnglish').val();
    let productCharacter = $('#productCharacter').val();
    let productAncestry = $('#productAncestry').val();
    let productDisease = $('#productDisease').val();
    let productLife = $('#productLife').val();
    let productIntroduce = $('#productIntroduce').val();
    itemID.forEach((e) => {
        $(`input[name= ${e}]`).each(function(j,item){
            petItemList.push(item.value);
        });
    });
    let tempList = []
    for(let i=0;i<petItemList.length/4;i++){
        tempList.push({"itemId":1,"itemSpecification":1,"itemPrice":1,"itemStock":1})
    }
    let j=0;

    for(let i=0;i<petItemList.length;i++){
        tempList[j].itemId=petItemList[i];
        i++;
        tempList[j].itemSpecification=petItemList[i];
        i++;
        tempList[j].itemPrice=petItemList[i];
        i++;
        tempList[j].itemStock=petItemList[i];
        j++
    }
    console.log(tempList)
    let settings = {
        "url": "/jpetstore/pets",
        "method": "PUT",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            productId:productId,
            category:category,
            productNameChinese:productNameChinese,
            productNameEnglish:productNameEnglish,
            productCharacter:productCharacter,
            productAncestry:productAncestry,
            productDisease:productDisease,
            productLife:productLife,
            productIntroduce:productIntroduce,
            petItemList:tempList
        }),
    };

    $.ajax(settings).done(function (response) {
        if(response.code===200){
            alert("修改成功")
        }
        else{
            alert("修改失败")
        }
    });
}

//返回上一页面
function backToAll(){
    sessionStorage.clear()
    window.location.href="http://localhost:8888/jpetstore/ProductManage.html"
}

function collapse(){
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

const changeImage = document.getElementById('changeImage');
changeImage.onchange=function(){

    var image = document.getElementById('changeImagePosition')
    // let img = $('#imagePosition');
    // var addImage = document.getElementById('addImage')
    // 获取得到file 对象
    var file = changeImage.files[0]
    // 创建url
    var imgUrl = window.URL.createObjectURL(file)
    image.setAttribute("src", imgUrl)
    // 更改img url 以后释放 url
    image.onload = function() {
        console.log('图片加载成功')
        URL.revokeObjectURL(imgUrl)
    }
}

function changePreviousImage(){
    let formData = new FormData();
    let img = changeImage;
    let fileObj = img.files[0];
    formData.append("multipartFile",fileObj);
    let productID = sessionStorage.getItem('ProductID')
    $.ajax({
        url: `/jpetstore/image/upload?productId=${productID}`,
        dataType: "json",
        async: false,
        processData: false,
        contentType: false,
        data: formData,
        method: "POST",
        success(res) {
            console.log(res)
        },
        error(err) {
            console.log(err)
        }
    })
}