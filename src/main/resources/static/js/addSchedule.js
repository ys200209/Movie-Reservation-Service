function addSchedules() {

    var date=$("#datePicker").val();
    var movie=$("#movie").val();
    var start=$("#startTime").val();
    var theater=$("#theater").val();
    var requestUrl=window.location.pathname;

    if(date == "" || movie == ""){
        alert("조건을 설정 해주세요");
        return;
    }

    var addScheduleDTO={
        movies_seq:movie,
        date:date,
        start_time : start,
        theaters_seq : theater
    };

    var jsonStr = JSON.stringify(addScheduleDTO);

    console.log(jsonStr);

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
            alert("저장에 성공했습니다.");
            alert(data);
        },
        error : function (request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}