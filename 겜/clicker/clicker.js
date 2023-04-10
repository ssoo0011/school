
$(document).ready(function(){
    gold()
    total_gold_func()
    bg_click()
    gold_intv()
    cat()
})

var gold_i = 1;
var i = 7;
function gold(){
    $("#gold").click(function(){
        gold_i+= i
        $("#gold_text").text(gold_i +'원')
    })
}
var click_gold = 4;
var click = 4;
function bg_click(){
    $("#bg").click(function(){
        var sum =  click_gold += click
        $("#total_gold").text(sum)
        $("#cat").animate({"top" : 220}, 100)
        $("#cat").animate({"top" : 270}, 100)
    })

}

function gold_intv(){
    setInterval(secgold, 500)
}
var sec_gold = 0;
var result = null;

function secgold(){
    $("#total_gold").text(sec_gold)
    sec_gold++
}

var totalGold = 0;
function total_gold_func(){
    totalGold = sec_gold + click_gold;
    $("#total_gold").text(totalGold)
}
