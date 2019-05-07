var mouseOver = function (str) {
    return function () {
        document.getElementById(str).style.backgroundColor = "white";
        document.getElementById(str).style.color = "black";
    }
}

var mouseOut = function (str) {
    return function () {
        document.getElementById(str).style.backgroundColor = "black";
        document.getElementById(str).style.color = "white";
    }
}

for (i = 1; i <= 3; i++) {
    document.getElementById(i).addEventListener("mouseover", mouseOver(i));
    document.getElementById(i).addEventListener("mouseout", mouseOut(i));
}