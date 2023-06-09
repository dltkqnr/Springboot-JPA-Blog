let index={
    init:function(){
        $("#btn-save").on("click",()=>{ // funtion(){} 대신 ()=>{} 를 사용. this를 바인딩 하기 위해서 
            this.save();
        });
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });
    },

    save:function(){

        let data = {
            title:$("#title").val(), //# id 값을 찾아서 .val() 그 값을 변수에 바이팅
            content:$("#content").val()
        };

        //ajax 호출 시 default가 비동기 호출
        //ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), //http body 데이터 
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType:"json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) =>javascript 오브젝트로 변경해줌 

            //회원가입 수행 요청 성공하면 done, 실패하면 fail 실행
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },
    deleteById:function(){
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType:"json"

        }).done(function(resp){
            alert("삭제가 완료되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },
    update:function(){

        let id = $("#id").val();

        let data = {
            title:$("#title").val(), //# id 값을 찾아서 .val() 그 값을 변수에 바이팅
            content:$("#content").val()
        };

        //ajax 호출 시 default가 비동기 호출
        //ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType:"json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) =>javascript 오브젝트로 변경해줌

            //회원가입 수행 요청 성공하면 done, 실패하면 fail 실행
        }).done(function(resp){
            alert("글수정이 완료되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },
    replySave:function(){

        let data = {
            userId:$("#userId").val(),
            boardId:$("#boardId").val(),
            content:$("#reply-content").val()
        };


        //ajax 호출 시 default가 비동기 호출
        //ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            type:"POST",
            url:`/api/board/${data.boardId}/reply`,
            data:JSON.stringify(data), //http body 데이터 
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType:"json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) =>javascript 오브젝트로 변경해줌 

            //회원가입 수행 요청 성공하면 done, 실패하면 fail 실행
        }).done(function(resp){
            alert("댓글 작성이 완료되었습니다.")
            location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },    
    replyDelete:function(boardId,replyId){
        $.ajax({
            type:"DELETE",
            url:`/api/board/${boardId}/reply/${replyId}`,
            dataType:"json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) =>javascript 오브젝트로 변경해줌 

            //회원가입 수행 요청 성공하면 done, 실패하면 fail 실행
        }).done(function(resp){
            alert("댓글이 삭제 되었습니다.")
            location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

}

index.init();