//html에서 화면이 다 만들어지면 실행되는 함수! 이걸 해주면 맨 위에서
//자바스크립트 링크를 써도 된다!
document.addEventListener("DOMContentLoaded", function(){   
    document.getElementById('confirm-btn').addEventListener('click', confirmCheck) //확인버튼!           
    mainMenuClickEnroll()
    sideMenuClickEnroll()
    addMenu()
    decMenu()
})

var num = 1

function confirmCheck(){

    alert("장바구니에 메뉴를 추가했씁니다.")

    var radiobtn = document.getElementsByClassName("main-menu")
    var checkBtn = document.getElementsByClassName("side-menu")

    for(var i = 0; i < radiobtn.length ; i++){
        if(radiobtn[i].checked === true){

        }
    }

    for(var i = 0; i < checkBtn.length ; i++){
        if(checkBtn[i].checked === true){

        }
    }
}

function mainMenuClickEnroll(){ //라디오 버튼에 클릭 이벤트 등록하는 함수
    var radiobtn = document.getElementsByClassName("main-menu")
    for(var i = 0; i<radiobtn.length ; i++){
            radiobtn[i].addEventListener('click', mainMenuClick )     
    }

}

function mainMenuClick(e){//클릭 시 이벤트 객체 정보가 매개변수 e에 들어옴
    // alert("메인메뉴 클릭")
    console.log(e.target.value)
    //이벤트가 일어난 곳의 value값을 읽어온다.
    sumPrice()
}


function sideMenuClickEnroll(){ //라디오 버튼에 클릭 이벤트 등록하는 함수
    var checkBtn = document.getElementsByClassName("side-menu")
    for(var i = 0; i<checkBtn.length ; i++){
        checkBtn[i].addEventListener('click', sideMenuClick )     
    }

}

function sideMenuClick(e){//클릭 시 이벤트 객체 정보가 매개변수 e에 들어옴
    // alert("메인메뉴 클릭")
    // alert(e.target.value)
    //이벤트가 일어난 곳의 value값을 읽어온다.
    sumPrice()

}

function sumPrice(){
    
    var sum = 0;
    var radiobtn = document.getElementsByClassName("main-menu")
    var checkBtn = document.getElementsByClassName("side-menu")
    var sumprice = document.getElementById("total-price-count")
    var totlaprice = document.getElementById("total-price-count")

    for(var i = 0; i < radiobtn.length ; i++){
        sumprice = parseInt(radiobtn[i].value)
        if(radiobtn[i].checked === true){
            sum += (sumprice)
        }
    }

    for(var i = 0; i < checkBtn.length ; i++){
        sumprice = parseInt(checkBtn[i].value)
        if(checkBtn[i].checked === true){
            sum += (sumprice)
        }
    } 
    // alert(sum)
    totlaprice.innerText = sum*num
    return sum
  
}

function addMenu(){
    var add = document.getElementById("inc-btn")
    add.addEventListener('click', addcount)
    var dec = document.getElementById("dec-btn")
    dec.addEventListener('click', deccount)

}

function addcount(){
    num++
    var sum = sumPrice()
    var totlaprice = document.getElementById("total-price-count")
    var count = document.getElementById("item-count")
    count.innerText = num

}

function deccount(){
    num--
    if(num < 1){
        num = 1
        alert("뺄 메뉴가 없당 !")
    }
    var sum = sumPrice()
    var totlaprice = document.getElementById("total-price-count")
    var count = document.getElementById("item-count")

    count.innerText = num
}
