

//Search button
function searchGrantsData() {
	$("#messages").empty();
	if($('#grantSearch').val().length == 0) {
		var errorMsg = "Please enter a Grant/Intramural/Contract #.";
		$("#messages").prepend('<div class="container"><div class="col-md-12"><div class="alert alert-danger"><h3><i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i>&nbsp;Error Status</h3><ul class="errorMessage"><li><span>' + errorMsg + '</span></li></ul></div></div></div>');
		window.scrollTo(0,0);
	} 
	else if($('#grantSearch').val().length < 6) {
		var errorMsg = "Please enter a minimum of 6 characters for Grant/Intramural/Contract #.";
		$("#messages").prepend('<div class="container"><div class="col-md-12"><div class="alert alert-danger"><h3><i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i>&nbsp;Error Status</h3><ul class="errorMessage"><li><span>' + errorMsg + '</span></li></ul></div></div></div>');
		window.scrollTo(0,0);
	} else {	
		
	    $('button.has-spinner').toggleClass('active');
	    var grantNum = $('#grantSearch').val().trim();
	    $('#grantSearch').val(grantNum);
		$form = $("#general_form");
	    fd = new FormData($form[0]);
		$.ajax({
		  	url: 'searchGrantsContractsAction.action',
		  	type: 'post',
		  	processData: false,
		    contentType: false,
		    data: fd,
		  	async:   true,
		  	success: function(msg){
				result = $.trim(msg);
				$('button.has-spinner').toggleClass('active');
				if(result.indexOf('<div') == 0) {
					$("#searchGrantsContracts").html(result);
					$("a.pop").hover(function() {
						$(this).popover({ trigger: "manual" , html: true, animation:false})
					    .on("mouseenter", function () {
					        var _this = this;
					        $(".popover").on("mouseleave", function () {
					            $(_this).popover('hide');
					        });
					    }).on("mouseleave", function () {
					        var _this = this;
					        setTimeout(function () {
					            if (!$(".popover:hover").length) {
					                $(_this).popover("hide");
					            }
					        }, 300);
					          }).popover("show");
					});
					$("#generalInfoSection").hide();
					$("#searchGrantsContracts").show();
					$("#general_form").removeClass( "dirty" )
				}
				else {
					bootbox.alert(result, function() {
			  			return true;
					});
				}
			}, 
			error: function(){}	
		});
		
		//$('#general_form').attr('action', "searchGrantsContractsAction.action").submit();
		$("#searchResults").focus();		

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

//confirm Edit
function confirmEdit(elem){
	if($(elem).attr("id") == 'confEdit') {
		var result = "By electing to edit the existing project or sub-project, the new submission will not be created.<br /> Do you wish to continue?";
		var id=$('#prevSubId').val();
		bootbox.confirm(result, function(ans) {
			if (ans) {
				  window.location = '../manage/navigateToSubmissionDetail.action?projectId='+id;
				return true;
			} else {
				return true;
			}
		});
	} 
}

//Cancel button
function cancel() {	
	$('#grantSearch').val('');
	$("#messages").empty();
	
	$("#searchGrantsContracts").hide();
	$("#generalInfoSection").show();


};


//Next button
function populateGrantsContractsData(){
	
	var idPrefix = $("#grantContractIdPrefix").val();
	var searchType = $("#searchType").val();
	$("#messages").empty();
	var grantContract = $("input[name=selectedGrantContract]:checked").val();
	
	if(grantContract == undefined) {
		var errorMsg = "Please select a Grant/Intramural/Contract #.";
		$("#messages").prepend('<div class="container"><div class="col-md-12"><div class="alert alert-danger"><h3><i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i>&nbsp;Error Status</h3><ul class="errorMessage"><li><span>' + errorMsg + '</span></li></ul></div></div></div>');
		window.scrollTo(0,0);
		return;
	}
	
	var json = jQuery.parseJSON(grantContract);	
		
	if (json.grantContractNum !== "undefined") {
		$("#" + idPrefix + "_grantsContractNum").val(json.grantContractNum);
	}
	
	if(searchType == 'extramural' || searchType == 'intramural') {
		if (json.applId !== "undefined") {
			$("#" + idPrefix + "_applId").val(json.applId);
		}
		
		if (json.projectTitle !== "undefined") {
			$("#" + idPrefix + "_projectTitle").val(json.projectTitle);
		}
	
		if (json.piFirstName !== "undefined") {
			$("#" + idPrefix + "_fnPI").val(json.piFirstName);
		}
	
		if (json.piLastName !== "undefined") {
			$("#" + idPrefix + "_lnPI").val(json.piLastName);
		}
	
		if (json.piEmailAddress !== "undefined") {
			$("#" + idPrefix  + "_piEmail").val(json.piEmailAddress);
		}
	
		if (json.piInstitution !== "undefined") {
			$("#" + idPrefix + "_PIInstitute").val(json.piInstitution);
		}
	}
	
	 //var applClassCode= json.applClassCode;	
	 //var docName=$('#DOC').find('option:selected').text();
	 //var code= $("input[type='radio'].grantSelection:checked").val();
	
	 //For Intramural grants don't display PD first name, last name and project start date, end date.
	 //if(applClassCode != "M"){
	 if(searchType == 'intramural'){
		//$("#" + idPrefix + "_grantsContractNum").prop('readOnly', false);
	    //$(".unlink-group").prop('disabled', false);
	    //$("#canAct").hide();
	    //$("#pdName").hide();
		//$("#pStartDate").hide();
		//$("#pEndDate").hide();	
		//$("#linkButton").hide();
		//$("#dataLinkFlag").val('N');
	}
	else if(searchType == 'extramural'){
		$("#linkedGrantContractNum").val(json.grantContractNum);
		$("#linkedProjectTitle").val(json.projectTitle);
		$("#" + idPrefix + "_grantsContractNum").prop('readOnly', true);
		$(".unlink-group").prop('disabled', true);
		
		if (json.pdFirstName !== "undefined") {
			$("#fnPD").val(json.pdFirstName);
		}
			
		if (json.pdLastName !== "undefined") {
			$("#lnPD").val(json.pdLastName);
		}
		
		if (json.projectPeriodStartDate !== "undefined" && json.projectPeriodStartDate != null && json.projectPeriodStartDate != "null") {
			$("#projectStartDate").val(json.projectPeriodStartDate);
			$("#linkedProjectStartDate").val(json.projectPeriodStartDate);
		} else {
			$("#projectStartDate").val("");
			$("#linkedProjectStartDate").val("");
		}
		
		if (json.projectPeriodEndDate !== "undefined" && json.projectPeriodStartDate != null && json.projectPeriodEndDate != "null") {
			$("#projectEndDate").val(json.projectPeriodEndDate);
			$("#linkedProjectEndDate").val(json.projectPeriodEndDate);
		} else {
			$("#projectEndDate").val("");
			$("#linkedProjectEndDate").val("");
		}
		
		if (json.cayCode !== "undefined") {
			$("#cancerActivity").val(json.cayCode);
			$("#linkedCayCode").val(json.cayCode);
		}
		
		//$("#canAct").show();
		//$("#pdName").show();
		//$("#pStartDate").show();
		//$("#pEndDate").show();	
		
		//Set grant to linked
		$("#link").css("background-color", "#d4d4d4");
		$("#unlink").css("background-color", "#FFF");
		$('#link').addClass('disabled');
		$("#unlink").removeClass('disabled');
		$("#linkButton").show();
		$(".unlink-group").prop('disabled', true);
		$("#dataLinkFlag").val('Y');
		$(".disabled-group").prop('disabled', true);
	}
		
	//Replace search icon with edit icon since we already have a grant
	$("#" + idPrefix + "_grantDiv i").removeClass("fa fa-search").addClass("fa fa-pencil");
	$("#" + idPrefix + "_grantDiv button").attr("title", "Edit");
	
	
	        //$("#" + idPrefix + "_grantsContractNum").attr("placeholder", "Click on Edit Icon");
	        //$("#" + idPrefix +"_div").find("i").removeClass("fa fa-search").addClass("fa fa-pencil");
			//$("#" + idPrefix +"_div").find("button").attr("title", "Edit");
			

	if (json.applId !== "undefined") {
		$("#applId").val(json.applId);			
	}
		
	$('#grantSearch').val('');
	$("#searchGrantsContracts").hide();
	$("#generalInfoSection").show();
}

//This function displays table of already linked submissions.
function showPrevLinkedSubmissions(){
	 var grantContract = $("input[name=selectedGrantContract]:checked").val();
	 //var project=$("input[name=projectId]").val();
	 var json = jQuery.parseJSON(grantContract);
	 var grantContractNum = json.grantContractNum;
	 var projectId=$("#projectId").val();
	 $.ajax({
		 url: 'getPrevLinkedSubmissionsForGrant.action',
		 dataType: 'html',
		 data: {grantContractNum: grantContractNum,projectId:projectId},
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



$(function(){
    $('a.has-spinner, button.has-spinner').click(function() {
        $(this).toggleClass('active');
    });
    
    /*if($("#grantsContractNum").val().length == 0 ||
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
    	}*/	
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






