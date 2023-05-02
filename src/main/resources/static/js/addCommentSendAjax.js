function dataSend() {


    var memberId=$("#memberId").val();
    var content=$("#content").val();
    var requestUrl=window.location.pathname;

    if(content.length==0){
        alert("댓글 내용을 입력해주세요");
        return;
    }

    var addCommentDTO={
        memberId:memberId,
        content:content
    };

    var jsonStr = JSON.stringify(addCommentDTO);

    $.ajax({
        url: requestUrl,
        data: jsonStr,
        type:"POST",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (jqXHR, settings) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        },
        success:function(data){
            var html = '';
            var comments = data.comments;
            var index = 0;
            $.each(comments, function(i, comment) {
                index = parseInt(i)+parseInt('1')
                html += '<tr>';
                html += '<td>' + index + '</td>';
                html += '<td>' + comment.memberName + '</td>';
                html += '<td>' + comment.content + '</td>';
                html += '<td>' + comment.lastCreateAt + '</td>';
                html += '</tr>';
            });
            $("#CommentTbody").html(html);
        },
        error : function (request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}