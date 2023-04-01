let itemID = ["categorySmall","priceSmall","amountSmall"];
let i=1;

$(function(){
    collapse();
})

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

function addNewSmall(){
    i++;
    let str=`
<div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>数据${i}</h2>
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
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">具体类别</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="categorySmall"></input>
                                    </div>
                                    </div>
                                    <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">价格</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="priceSmall"></input>
                                    </div>
                                    </div>
                                    <div class="item form-group">
                                    <label class="col-form-label col-md-3 col-sm-3 label-align">数量</label>
                                    <div class="col-md-6 col-sm-6 ">
                                    <input class="form-control" name="amountSmall"></input>
                                    </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        `
    $('#father').append(str)
}

function backToAll(){
    window.location.href="http://localhost:8888/jpetstore/ProductManage.html"
}

function newSubmit(){
    let petItemList=[];
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
    for(let i=0;i<petItemList.length/3;i++){
        tempList.push({"itemSpecification":1,"itemPrice":1,"itemStock":1})
    }
    let j=0;
    for(let i=0;i<petItemList.length;i++){
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
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
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
        console.log(response.code);
    });
}

const addImage = document.getElementById('addImage');

addImage.onchange=function(){

    var image = document.getElementById('addImagePosition')
    // let img = $('#imagePosition');
    // var addImage = document.getElementById('addImage')
    // 获取得到file 对象
    var file = addImage.files[0]
    // 创建url
    var imgUrl = window.URL.createObjectURL(file)
    image.setAttribute("src", imgUrl)
    // 更改img url 以后释放 url
    image.onload = function() {
        console.log('图片加载成功')
        URL.revokeObjectURL(imgUrl)
    }
}

function addNewImage(){
    let formData = new FormData();
    let img = addImage;
    let fileObj = img.files[0];
    formData.append("multipartFile",fileObj)
    $.ajax({
        url: "/jpetstore/image/upload",
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