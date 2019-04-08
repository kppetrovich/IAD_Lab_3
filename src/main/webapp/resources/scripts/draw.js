var pt;

document.addEventListener('DOMContentLoaded', function () {
    pt = document.getElementById('picture').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    console.log(x);
    var y = event.clientY;
    console.log(y);
    var cursorpt = pt.matrixTransform(document.getElementById('picture').getScreenCTM().inverse());
    var r = parseFloat(document.getElementById('form:R_text').innerHTML);
    document.getElementById("form:hiddenX").value = (x + cursorpt.x - 125) * r / 100;
    document.getElementById("form:Y").value = -(y + cursorpt.y - 125) * r / 100;
    document.getElementById("form:areaClickBtn").click();
}
