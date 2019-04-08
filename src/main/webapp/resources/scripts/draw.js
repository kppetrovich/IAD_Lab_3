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
    console.log(r);
    document.getElementById("form:hiddenX").value = (x + cursorpt.x - 227) * r / 217;
    document.getElementById("form:Y").value = -(y + cursorpt.y - 103) * r / 186;
    console.log("HiddenY: " + (-(y + cursorpt.y - 103) * r / 186));
    document.getElementById("form:areaClickBtn").click();
}