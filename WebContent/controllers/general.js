//for general.htm page -- 

$(document).ready(function () {

//toggle for question 1

$('input[name="project"]').bind('change',function(){
    var showOrHide = ($(this).val() == 1) ? true : false;
    $('#whyProject').toggle(showOrHide);
 });

//date picker for Project Start
$('#pStartDate .input-group.date').datepicker({
          orientation: "bottom auto",
          todayHighlight: true
          });

//date picker for Project End    

$('#pEndDate .input-group.date').datepicker({
          orientation: "bottom auto",
          todayHighlight: true
          });      

});