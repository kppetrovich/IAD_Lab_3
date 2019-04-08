var pt;

document.addEventListener('DOMContentLoaded', function(){
    pt = document.getElementById('picture').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('picture').getScreenCTM().inverse());
    var r = document.getElementById('form:r').value;
    document.getElementById("form:hiddenX").value = (x+cursorpt.x - 227) * r /217;
    document.getElementById("form:hiddenY").value = -(y+cursorpt.y - 103) * r / 186;
    document.getElementById("form:areaClickBtn").click();
}