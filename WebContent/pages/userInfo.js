var resourcesDT;
var option = 0;
var gTeacherId;
var SESSIONID;
$(function() {
    // $.adjustHeight();
    $.ajax({
        url : rooturl + 'getUserInfo',
        type : 'post',
        dataType : 'json',
        data : {
            userId : sessionStorage.userId,
            userDevice : "web",
            sessionId : SESSIONID
        },
        success : function(msg) {
            // console.log(sessionStorage);
            if (msg.createDate != "") {
                $("#loginName").val(msg.dateStr);
                $("#teacherName").val(msg.userName);
                $("#teacherCard").val(msg.dateStr);
                if (msg.userSex == '女')
                    $("input[name='teacherSex'][value='女']").attr("checked", true);
                else
                    $("input[name='teacherSex'][value='男']").attr("checked", true);
                $("#teacherPhone").val(msg.phone);
                $("#teacherEmail").val(msg.userName);
                $("#teacherBirthday").val(msg.email);
                $("#teacherIntroduce").val();
                $("#teacherId").val();
                $("#actorName").val();
                if (msg.teacherPhoto != "" && msg.teacherPhoto != null) {
                    $("#showPhoto").attr("src", msg.teacherPhoto);
                } else {
                    if (msg.userSex == "男") {
                        $("#showPhoto").attr('src', 'img/user_men.png');
                    } else if (msg.userSex == "女") {
                        $("#showPhoto").attr('src', 'img/user_women.png');
                    }
                }
            }
        },
        error : function() {

        }
    });
    $("input[type='text'],input[type='email']").attr('readonly', true);
    $("input[type='radio']").attr('disabled', true);
    $("#teacherBirthday").attr('disabled', true);
    $("input[type='text'],input[type='email'],input[type='radio'],textArea").css('border', 'none');
    $('#oldpassword').blur(function() // 失去焦点时触发的时间
    {
       $.ajax({
           url : rooturl + "judgePwd",
           type : "POST",
           dataType : "JSON",
           data : {
               id : sessionStorage.userId,
               pwd : $("#oldpassword").val()
           },
           success : function(data) {
               if(data!=1)
                   {
                   alert("原密码输入错误");
                   $("#oldpassword").val("");
                  
                   }
           }
       });
    })
});
// 修改信息
$.modify = function() {
    if (option == 1) {
        var sex = $("#teacherSex").val();
        var phone = $("#teacherPhone").val();
        var email = $("#teacherBirthday").val();
        $.ajax({
            url : rooturl + "updateUserInfo",
            type : "POST",
            dataType : "JSON",
            data : {
                "id" : sessionStorage.userId,
                phone : phone,
                email : email
            },
            success : function(data) {
                alert("修改成功");
                $.cancel();
            }
        });
    } else {
        $("#upheadDiv").fadeIn(300);
        $("#cancel").fadeIn(300);
        $("#modify").attr("value", '保   存');
        $("input[type='text'],input[type='email'],textArea").attr('readonly', false);
        $("input[type='radio'],input[type='text'],input[type='email'],textArea").css('border', '2px solid #DBDBDB');
        $("#loginName,#teacherCard,#actorName,#teacherName").attr('readonly', true);
        $("#loginName,#teacherCard,#actorName,#teacherName,#teacherSex").css('border', 'none');
        $("input[type='radio']").attr('disabled', false);
        $("#teacherBirthday").attr('disabled', false);
    }
    option = 1;
};

$.cancel = function() {
    $("#upheadDiv").fadeOut(300);
    $("#cancel").fadeOut(300);
    $("#modify").attr("value", '修改信息');
    $("input[type='text'],input[type='email'],textArea").attr('readonly', true);
    $("input[type='radio']").attr('disabled', true);
    $("#teacherBirthday").attr('disabled', true);
    $("input[type='text'],input[type='email'],input[type='radio']").css('border', 'none');
    option = 0;
};
$.checkPwd = function() {
    if ($("#newpassword").val() != $("#repeatpassword").val()) {
        $("#repeatpassword").val("");
        $("#repeatpassword").focus();
        $("i[data-bv-icon-for='repeatpassword']").removeClass("glyphicon-ok");
        $("#passwordrepeat").removeClass("has-success").addClass("has-error");
        swal("No","两次密码不一致","error");
        pwIsEqual = false;
    } else {
        pwIsEqual = true;
    }
};
$.ChangePwd = function() {
    $('#sessionId').val(SESSIONID);
    if ($("#newpassword").val() == "" || $("#repeatpassword").val() == "") {
    	swal("No","密码不得为空","error");
    } else {
        $.ajax({
            url : rooturl + "editPwd",
            type : "POST",
            dataType : "JSON",
            data : {
                "id" : sessionStorage.userId,
                pwd : $("#newpassword").val()
            },
            success : function(data) {
                swal("OK","修改成功","success");
                $("#newpassword").val("");
                $("#repeatpassword").val("");
                $("#oldpassword").val("");
                $('#passwordModal').modal('hide');
            }
        });
    }
};
$.closeDefaultModel = function(obj) {
    $("#newpassword").val("");
    $("#repeatpassword").val("");
    $("#oldpassword").val("");
    obj.modal("hide");
};

// //日历控件
// $('#teacherBirthday').datetimepicker({
// format: 'yyyy-mm-dd',
// autoclose: true,
// todayBtn: 'linked',
// language: 'zh-CN',
// // startView : 4,
// minView: 3,
// });

// 关闭图片上次Modal
$.closeUpPhModal = function() {
    $("#upPhotoModal").modal("hide");
    $("#ossfile").empty();
    clearOssData();
    $("#editHonorInfo").modal({
        backdrop : 'static',
        keyboard : false,
        show : true
    });
};
// 弹出上传图片的框
$.uploadPhoto = function() {
    $("#upPhotoModalLabel").text("上传图片");
    $("#editHonorInfo").modal("hide");
    $("#upPhotoModal").modal({
        backdrop : 'static',
        keyboard : true,
        show : true
    });
};
// 获取图片信息
$.doneUpPhoto = function() {
    var newUrl;
    var fileInfo = getOssFileInfo();
    if ((fileInfo.length) == 0) {
        $.alert("未上传图片", 3);
    } else if ((fileInfo.length) > 1) {
        $.alert("只需上传一张图片作为证件照片", 3);
    } else {
        newUrl = fileInfo[0].fileUrl;
        $("#showPhoto").attr("src", newUrl);
        $("#teacherPhoto").val(newUrl);
        $.closeUpPhModal();
    }
};
// 判断文件类型(在需要的js文件添加，文件名务必保持一致)
function judgeFileType(suffix) {
    // 图片文件
    if (!(suffix == ".jpg" || suffix == ".JPG" || suffix == ".png" || suffix == ".PNG" || suffix == ".jpeg" || suffix == ".JPEG" || suffix == ".gif" || suffix == ".GIF" || suffix == ".bmp" || suffix == ".BMP")) {
        alert("请选择图片文件！");
        return false;
    }
}
$.cancel = function() {
    $("#upheadDiv").fadeOut(300);
    $("#cancel").fadeOut(300);
    $("#modify").attr("value", '修改信息');
    $("input[type='text'],input[type='email'],textArea").attr('readonly', true);
    $("input[type='radio']").attr('disabled', true);
    $("#teacherBirthday").attr('disabled', true);
    $("input[type='text'],input[type='email'],input[type='radio']").css('border', 'none');
    option = 0;
};
