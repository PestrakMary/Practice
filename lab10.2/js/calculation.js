function raschitat() {
    var female = document.getElementById('female').value;
    var male = document.getElementById('male').value;
    var height = document.getElementById('height').value;
    var weight = document.getElementById('weight').value;
    var age = document.getElementById('age').value;
    if(height != "" && weight!="" && age !="") {
        if (document.getElementById("male").checked) {
            var calor = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
            document.getElementById('calor').innerHTML = "Калорийность рациона: " + calor + " калорий";
        } else if (document.getElementById("female").checked) {
            var calor = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
            document.getElementById('calor').innerHTML = "Калорийность рациона: " + calor + " калорий";
        } else document.getElementById('calor').innerHTML = "Пожалуйста, заполните все необходимые поля!";
    } else document.getElementById('calor').innerHTML = "Пожалуйста, заполните все необходимые поля!";
}
function idealW() {
    var female = document.getElementById('female1').value;
    var male = document.getElementById('male1').value;
    var height = document.getElementById('height1').value;
    if(height!="") {
        if (document.getElementById("male1").checked) {
            var kg = (height - 100) * 1.15;
            document.getElementById('ideal').innerHTML = "Ваш идеальный вес: " + kg + " кг";
        } else if (document.getElementById("female1").checked) {
            var kg = (height - 100) - (height - 150) / 2;
            document.getElementById('ideal').innerHTML = "Ваш идеальный вес: " + kg + " кг";
        } else document.getElementById('ideal').innerHTML = "Пожалуйста, заполните все необходимые поля!";
    } else document.getElementById('ideal').innerHTML = "Пожалуйста, заполните все необходимые поля!";
}
