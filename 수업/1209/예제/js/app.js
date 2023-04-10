$(document).ready(function () {

              
    $('header').each(function () {
         
        var $win = $(window);  // 윈도우화면창을 제이쿼리 오브젝트화   
        var $header = $(this);   // 헤더를 객체화해서 담아둠 
        var hot = $header.offset().top;  // 헤더의 현재 원래의 기본위치를 검색해서 확인!  
        /* offset() 선택한 요소의 좌표를 찾는 역할! (절대좌표 상단기준0)
          .top : 상단요소의 기준위치를 찾는 역할 
          .left :  좌측요소의 기준위치를 찾는 역할   */

        $win.scroll(function () {
            console.log("scroll");
            if ($win.scrollTop() > hot) {
                $header.addClass('fixed');  
            } else {
                $header.removeClass('fixed');
            }

        });
        
        //  윈도우의 스크롤 이벤트를 강제로 발생시키는 부분 
        // 선택된 요소의 이벤트를 강제로 발생시킴!!   

    });


});