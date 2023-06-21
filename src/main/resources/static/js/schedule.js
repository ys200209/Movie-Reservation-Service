function searchSchedules() {

    var date=$("#datePicker").val();
    var movie=$("#movie").val();
    var requestUrl=window.location.pathname + "/search";

    if(date == "" || movie == ""){
        alert("조건을 설정 해주세요");
        return;
    }

    var searchDTO={
        movies_seq:movie,
        date:date
    };

    var jsonStr = JSON.stringify(searchDTO);

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
            var html = '';
            if(!data){
                html = "<h1>조건에 맞는 스케줄이 없습니다.</h1>"
                $("#schedules").html(html);
                return;
            }
            console.log(data);
            var schedules = data;
            var first = '<h4>1관</h4>';
            var second = '<h4>2관</h4>';
            var third = '<h4>3관</h4>';
            var open = '<div class="">';
            var close = '</div>';
            $.each(schedules, function(i, schedule) {
                if(schedule.theaters_seq == 1) {
                    first += `<a href="/${schedule.schedules_seq}/reservation">${schedule.start_time}</a>`;
                }
                if(schedule.theaters_seq == 2) {
                    second += `<a href="/${schedule.schedules_seq}/reservation">${schedule.start_time}</a>`;
                }
                html += '<p> 3관 </p>';
                if(schedule.theaters_seq == 3) {
                    third += `<a href="/${schedule.schedules_seq}/reservation">${schedule.start_time}</a>`;
                }
            });
            html = open + first + close + "<br>" + open + second + close+ "<br>" + open + third+ close;
            $("#schedules").html(html);
        },
        error : function (request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}