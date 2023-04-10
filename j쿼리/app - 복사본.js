
$(document).ready(function(){
  menu()
  slide()
  // tab()
  tab2()
  popup()
})

function menu(){
  $(".gnb>li").hover(
    function(){//mouseover
      $(".lnb").stop().slideDown()
    },
    function(){//mouseout
      $(".lnb").stop().slideUp()
    }
  )
}

function slide(){
  setInterval(slideMove,1000)
}

var idx = 0; //슬라이드 인덱스
var curTop = 0;//현제 top위치
function slideMove(){

  idx++;
  curTop = -300 * idx;
  if(curTop == -900){
    curTop = 0;
    idx = 0;
  }

  $("#slide_contents").animate({"top":curTop})
}

function tab(){
  $(".tab_btn > a").eq(0).click(function(){
    $(".tab_contents>div").eq(1).hide()  
    $(".tab_contents>div").eq(0).show()
  })

  $(".tab_btn > a").eq(1).click(function(){
    $(".tab_contents>div").eq(1).show()  
    $(".tab_contents>div").eq(0).hide()
  })
}

function tab2(){
  $(".tab_btn > a").click(function(){
    var i = $(this).index() //클릭한 것의 인덱스 번호
    $(".tab_contents>div").hide()
    $(".tab_contents>div").eq(i).show()

  })
   
}

function popup(){ 
  $(".pop").click(function(){
    $('#popup').show()

  })
  $("#closeBtn").click(function(){
    $('#popup').hide()
    
  })
}
