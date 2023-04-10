
// 아이디를 가진 버튼을 클릭하면 함수로 감!
document.getElementById("btnAdd").addEventListener('click', addTodo)
document.getElementById("DeleteSel").addEventListener('click', deleteSel)
document.getElementById("btnDelLast").addEventListener('click', deleteLast)
document.getElementById("btnDelAll").addEventListener('click', deleteAll)

var inputText = document.getElementById("text-basic") //입력
var showList = document.getElementById("listBody") //테이블 본문
var delCheckList = document.getElementsByClassName("btn-chk")

var localList = []
var index = 0


function key(e){

    if(window.event.keyCode === 13){    

        var addInput = showList.insertRow() // 테이블 한 줄 생성
        var checkList = addInput.insertCell(0); //셀 생성
        var listText = addInput.insertCell(1);
        checkList.innerHTML = '<input type="checkbox" class="btn-chk">'
        listText.innerText = inputText.value 
        localList.push(inputText.value)

        localStorage.setItem('todo', JSON.stringify(localList))
        var result = localStorage.getItem('todo')
        result = JSON.parse(result)
        index++
        
        console.log("리절트렝쓰" , result.length)       

        inputText.value = "" 
    }   

    console.log(localList.length)
}



function addTodo(){

    var addInput = showList.insertRow() // 테이블 한 줄 생성
    var checkList = addInput.insertCell(0);
    var listText = addInput.insertCell(1);

    checkList.innerHTML = '<input type="checkbox" class="btn-chk">'
    listText.innerText = inputText.value
    localList.push({todo : inputText.value }) // 배열 드가기 확인!

    inputText.value = ""

}


function deleteSel(){

   for(var i = 0; i <= showList.rows.length; i++){

        if(delCheckList[i].checked === true){
            showList.deleteRow(i);
            i--;
        }   
   }
    
}

function deleteLast(){

    showList.deleteRow(showList.rows.length-1)

}

function deleteAll(){

    console.log(showList.rows.length)

     for(var i = 0; i < showList.rows.length; i++){
        showList.deleteRow(i)
        i--;
    }

}
