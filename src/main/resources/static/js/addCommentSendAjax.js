
var main = {
    init : function () {
        /*var _this = this;
        $('#submit').on('click', function () {
            _this.dataSend();
        });

        $('#select-seats').on('click', function () {
            _this.reservationSeat();
        });*/
    },

    /*reservationSeat : function () {
        alert("ㅎㅇ");

        var movieId = document.querySelector('button').dataset.buttonValue;
        var selectedText = $('#selected').val()

        var data = {
            movieId: movieId,
            selectedText: selectedText,
        };

        alert("ㅎㅇ");
        alert("movieId : " + movieId);
        alert("selectedText : " + selectedText);

        $.ajax({
            type: 'POST',
            url: '/movies/' + movieId +'/reservation',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('예매가 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
            window.location.href = '/movies/' + movieId
        });
    },

    dataSend : function () {

        var memberId=$("#memberId").val();
        var content=$("#content").val();
        var requestUrl=window.location.pathname;
        $("#content").val("");
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
    }*/
}

main.init();
