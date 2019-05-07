var mouseOver = function (str) {
    return function () {
        document.getElementById(str).style.backgroundColor = "#FFC0CB";
    }
}

var mouseOut = function (str) {
    return function () {
        document.getElementById(str).style.backgroundColor = "#CD5C5C";
    }
}

for (i = 1; i <= 3; i++) {
    document.getElementById(i).addEventListener("mouseover", mouseOver(i));
    document.getElementById(i).addEventListener("mouseout", mouseOut(i));
}