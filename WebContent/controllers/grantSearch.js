

//Search button
function searchGrantsData() {
	$("#messages").empty();
	if($('#grantSearch').val().length == 0) {
		var errorMsg = "Please enter Intramural (Z01)/Grant/Contract #.";
		$("#messages").prepend('<div class="container"><div class="col-md-12"><div class="alert alert-danger"><h3><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>&nbsp;Error Status</h3><ul class="errorMessage"><li><span>' + errorMsg + '</span></li></ul></div></div></div>');
		window.scrollTo(0,0);
	} else {	
		
	    $('button.has-spinner').toggleClass('active');
	    var grantNum = $('#grantSearch').val().replace(/\s+/g, '');
	    $('#grantSearch').val(grantNum);
		$form = $("#general_form");
	    fd = new FormData($form[0]);
		$.ajax({
		  	url: 'searchGrantsContractsAction.action',
		  	type: 'post',
		  	processData: false,
		    contentType: false,
		    data: fd,
		  	async:   false,
		  	success: function(msg){
				result = $.trim(msg);
			}, 
			error: function(){}	
		});
		$('button.has-spinner').toggleClass('active');
		if(result.indexOf('<div') == 0) {
			$("#searchGrantsContracts").html(result);
			$("#generalInfoSection").hide();
			$("#searchGrantsContracts").show();
		}
		else {
			bootbox.alert(result, function() {
	  			return true;
			});
		}
		
		//$('#general_form').attr('action', "searchGrantsContractsAction.action").submit();
	}
	
};


//Reset button
function resetData() {
	
	$("#messages").empty();
	$('#grantSearch').val('');
	var parent = $(".tableContent").parent();
	$(".tableContent").remove();
	$(".tableContentOdd").remove();
	parent.append('<tr class="tableContent"><td colspan="4">Nothing found to display.</td></tr>');
	 $("#prevLinkedSubmissions").hide();
};


//Cancel button
function cancel() {	
	$('#grantSearch').val('');
	$("#messages").empty();
	
	if($("#grantsContractNum").val().length > 0) {
		$("#searchGrantsContracts").hide();
		$("#generalInfoSection").show();
	} else {
		$('#general_form').attr('action', "newSubmission.action").submit();
	}

};


//Next button
function populateGrantsContractsData(){
	
	$("#messages").empty();
	var grantContract = $("input[name=selectedGrantContract]:checked").val();
	
	if(grantContract == undefined) {
		var errorMsg = "Please select Intramural (Z01)/Grant/Contract #.";
		$("#messages").prepend('<div class="container"><div class="col-md-12"><div class="alert alert-danger"><h3><i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i>&nbsp;Error Status</h3><ul class="errorMessage"><li><span>' + errorMsg + '</span></li></ul></div></div></div>');
		window.scrollTo(0,0);
		return;
	}
	
	var json = jQuery.parseJSON(grantContract);	
		
	if (json.grantContractNum !== "undefined") {
		$("#grantsContractNum").val(json.grantContractNum);
		$("#grantsContractNum").prop('readOnly', true);
	}
	
	if (json.projectTitle !== "undefined") {
		$("#projectTitle").val(json.projectTitle);
		$("#projectTitle").prop('disabled', true);
	}
	
	if (json.piFirstName !== "undefined") {
		$("#fnPI").val(json.piFirstName);
		$("#fnPI").prop('disabled', true);
	}
	
	if (json.piLastName !== "undefined") {
		$("#lnPI").val(json.piLastName);
		$("#lnPI").prop('disabled', true);
	}
	
	if (json.piEmailAddress !== "undefined") {
		$("#piEmail").val(json.piEmailAddress);
		$("#piEmail").prop('disabled', true);
	}
	
	
	if (json.piInstitution !== "undefined") {
		$("#PIInstitute").val(json.piInstitution);
		$("#PIInstitute").prop('disabled', true);
	}
	
	var applClassCode= json.applClassCode;	
	
	//For Intramural grants don't display PD first name, last name and project start date, end date.
	if(applClassCode != "M"){
		if (json.pdFirstName !== "undefined") {
			$("#fnPD").val(json.pdFirstName);
			$("#fnPD").prop('disabled', true);	
		}
			
		if (json.pdLastName !== "undefined") {
			$("#lnPD").val(json.pdLastName);
			$("#lnPD").prop('disabled', true);
		}
		
		if (json.projectPeriodStartDate !== "undefined") {
			$("#projectStartDate").val(json.projectPeriodStartDate);
			$("#projectStartDate").prop('disabled', true);
		}
		
		if (json.projectPeriodEndDate !== "undefined") {
			$("#projectEndDate").val(json.projectPeriodEndDate);
			$("#projectEndDate").prop('disabled', true);
		}
		$("#pdName").show();
		$("#pStartDate").show();
		$("#pEndDate").show();	
	}
	else{
		$("#pdName").hide();
		$("#pStartDate").hide();
		$("#pEndDate").hide();			
	}
	
	if (json.applId !== "undefined") {
		$("#applId").val(json.applId);			
	}
		
	$('#grantSearch').val('');
	$("#searchGrantsContracts").hide();
	$("#generalInfoSection").show();
	$("#link").hide();
	$("#unlink").show();
	$(".unlink-group").prop('disabled', true);
	$("#dataLinkFlag").val('Y');
	
}

//This function displays table of already linked submissions.
function showPrevLinkedSubmissions(){
	 var grantContract = $("input[name=selectedGrantContract]:checked").val();
	 var json = jQuery.parseJSON(grantContract);	
	 var grantContractNum = json.grantContractNum;
	 $.ajax({
		 url: 'getPrevLinkedSubmissionsForGrant.action',
		 dataType: 'html',
		 data: {grantContractNum: grantContractNum},
		 type: 'post',
		 success: function(html) {   			
			 $("#prevLinkedSubmissions").html(html);
			 $("#prevLinkedSubmissions").show();
			 if(html.indexOf("prevLinkedSubmissionsTable") > 0){
				 $("#prevLinkedSubmissions").focus();
			 }
		 }
	 })
}


function openDetailsReport(id) {
	var url = "/gds/manage/viewSubmissionDetails.action?projectId=" + id;
	var winName = "Submission Details Report";
	var features = "menubar=yes,scrollbars=yes,resizable=yes,width=800,height=800";
	var newWin = window.open(url, winName, features);
}



$(function(){
    $('a.has-spinner, button.has-spinner').click(function() {
        $(this).toggleClass('active');
    });
    
    if($("#grantsContractNum").val().length == 0 ||
    		$("#grantSearch").val().length != 0) {
    		//The project has no grant number specified, or a
    		//grant search request was made
    		$("#generalInfoSection").hide();
    		$("#searchGrantsContracts").show();
    		$("#grantSearch").focus();
			
			//If user hits Enter key : 
			$("#general_form").keydown(function( event ) {
				if ( event.which == 13) {				
					//Prevent default submit
					event.preventDefault();						
					//Hit Search
					$( "#searchGrants" ).click();					
				}
			});	
			
    	} else {
    		$("#searchGrantsContracts").hide();
    		$("#generalInfoSection").show();
    	}	
});


$('.panel-heading span.clickable').click (function(){
    var $this = $(this);
  if(!$this.hasClass('panel-collapsed')) {
    $this.parents('.panel').find('.panel-body').slideUp();

    $this.addClass('panel-collapsed');
    $this.find('i').removeClass('fa-minus-square').addClass('fa-plus-square');
  } else {
    $this.parents('.panel').find('.panel-body').slideDown();
    $this.removeClass('panel-collapsed');
    $this.find('i').removeClass('fa-plus-square').addClass('fa-minus-square');
  }
});


$('.icDetails').on('click', function(e) {
	  e.preventDefault();
	  var id = $(this).attr("id").replace("icDetails", "contentDivImg");
	  var expandId = $(this).attr("id").replace("icDetails", "");
	  $("#" + id).slideToggle('500');
	  $("#" + expandId + "expand").toggleClass('fa-plus-square fa-minus-square');
})


$("#close").click(function(){
    window.close();
});




