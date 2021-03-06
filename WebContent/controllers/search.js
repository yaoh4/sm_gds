//For Search Submission Result Data table
$(document).ready(function(){
	
	$('.stickyDiv').removeClass('stickyDiv');
	
	$.fn.dataTable.ext.errMode = function ( settings, helpPage, message ) { 
		bootbox.alert(message.substr(message.indexOf("-") + 1), function() {
      		return true;
    	});
	};

	// Converts object with "name" and "value" keys
	// into object with "name" key having "value" as value
	$.fn.serializeObject = function(){
	   var obj = {};
	    
	   $.each( this.serializeArray(), function(i,o){
	      var n = o.name, v = o.value;
	        
	      obj[n] = obj[n] == undefined ? v
	         : $.isArray( obj[n] ) ? obj[n].concat( v )
	         : [ obj[n], v ];
	   });
	    
	   return obj;
	};
	
	//data table initialization
	var submissionTable = $("#submissionTable").DataTable ( {
            "responsive": false,
            "autoWidth": false,
            "processing": false,
            "serverSide": true,
            "stateSave": true,
            "destroy": true,  
            "fixedHeader": true,
            
     
            "deferLoading": 0,
            "ajax": {
                "url": "search.action",
                "type": "POST",
                "data": function ( d ) {
                    $.extend( d, $("#search-form").serializeObject());
                },
                "error": function(xhr, error, thrown) {
                	bootbox.alert(error, function() {
              			return true;
            		});
                }
            },
            "dom": "<'row'<'col-sm-6' <'export'>>>" + "<'row'<'col-sm-12'l <'legend'>><'col-sm-6'f>>"
            +"<'row'<'col-sm-5'i><'col-sm-7'p><br/>>" + 
           
            "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-5'i><'col-sm-7'p>>" + "<'row'<'col-sm-12'l>>",
            "columns": [
                { "data": "id"},
                { "data": "projectSubmissionTitle"},
                { "data": "extGrantContractNum"},
                { "data": "extPiLastName"},
                { "data": "extPiFirstName"},
                { "data": "extPiEmailAddress"},
                { "data": "gdsPlanPageStatusCode"},
                { "data": "dataSharingExcepStatusCode"},
                { "data": "icPageStatusCode"},
                { "data": "bsiPageStatusCode"},
                { "data": "repoCount"},
                { "data": "subprojectCount"},
                { "data": "expandSubproject"},
                { "data": "expandRepository"},
                { "data": "subprojectEligibleFlag"},
                { "data": "projectStatusCode"},
                { "data": "newVersionEligibleFlag"},
                { "data": "intGrantContractNum"},
                { "data": "intPiLastName"},
                { "data": "intPiFirstName"},
                { "data": "intPiEmailAddress"},
                { "data": "versionNum"},
                { "data": "repositoryPageStatusCode"},
                { "data": null}
            ],
            "searching": false,
            "pageLength": 50,
            "lengthMenu": [5, 10, 25, 50, 100],
            "language": {
                "info": "_TOTAL_ project submissions (_START_ to _END_)  ",
                        "lengthMenu":     "_MENU_ per page",
                "infoEmpty": "0 project submissions ",
                "emptyTable": "No project submissions found for the given search criteria",
                "paginate": {
                	previous: '<i id="paginationicon" class="fa fa-caret-left" aria-hidden="true"></i>',
                	next: '<i id="paginationicon" class="fa fa-caret-right" aria-hidden="true"></i>'
                }
            },
            "columnDefs": [ 
                {
                "targets": [ 0, 4, 5, 7, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ],
                "visible": false,
                
                },
                {
                "targets": -1, // Last column, action
                "orderable": false,
                "render": function (data, type, row, meta) {
                	if($("#readonly").val() == "true") {
                		return '<div style="white-space: nowrap; font-size: 14px;"><a href="../manage/navigateToSubmissionDetail.action?projectId=' + row.id + '"><i class="fa fa-file-text fa-lg" aria-hidden="true" alt="View" title="View"></i></a></div>'
                	}
                	var addNewVersion = '', addSubproject = '', deleteSubmission = '';
                	if(row.subprojectEligibleFlag == "Y") {
                		addSubproject = '&nbsp;&nbsp;&nbsp;<a href="../manage/createSubproject.action?projectId=' + row.id + '"><i class="fa fa-folder-open fa-lg" aria-hidden="true" alt="Add New Sub-project" title="Add New Sub-project"></a>';
                	}
                	if(row.newVersionEligibleFlag == "Y") {
                		addNewVersion = '&nbsp;&nbsp;&nbsp;<a onclick="newVersion(' + row.id + ',' + row.subprojectCount + ')" href="javascript: void(0)"><i class="fa fa-clone fa-lg" aria-hidden="true" title="Add New Version" alt="Add New Version"></i></a>';
                	}
                	if($("#gpa").val() == "true") {
                		deleteSubmission = '&nbsp;&nbsp;&nbsp;<a onclick="deleteSubmission(' + row.id + ',' + row.subprojectCount + ')" href="javascript: void(0)"><i class="fa fa-trash fa-lg" aria-hidden="true" alt="Delete" title="Delete"></i></a>';
                	}
                    return '<div style="white-space: nowrap; font-size: 14px;"><a href="../manage/navigateToSubmissionDetail.action?projectId=' + row.id + '"><i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="Edit" title="Edit"></i></a>' +
                    deleteSubmission + addNewVersion + addSubproject + '</div>';
                } },
                {
                "targets": -2, // Repository
                
                "className": "text-nowrap",
                "orderable": true,
                "render": function (data, type, row, meta) {
                	 {
                		 if(type == 'display') {
                     		if(data == "INPROGRESS") {
                     			return '<div class="searchProgess"><img src="../images/inprogress.png" alt="In Progress" title="In Progress" width="18px" height="18px" />&nbsp;&nbsp;</div>'
                     		}
                     		else if(data == "COMPLETED") {
                     			return '<div class="searchProgess"><img src="../images/complete.png" alt="Completed" title="Completed" width="18px" height="18px"/>&nbsp;&nbsp;</div>'
                     		}
                     		else if(data == "NOTSTARTED") {
                     			return '<div class="searchProgess"><img src="../images/pending.png" alt="Not Started" title="Not Started" width="18px" height="18px">&nbsp;&nbsp;</div>'
                     	   	} else {
                     	   		return '<div style="text-align: center;">N/A</div>';
                     	   	}
                		 }
                	}
                	return "";
                } },
                {
                "targets": 1, // First visible column, submission title.  id is column 0.
                 
                "render": function (data, type, row, meta) {
                	if(type == 'display') {
                		fullText = data + ' (v' + row.versionNum + ')';
                		if(data.length > 100) {
                			data = '<span class="hoverOverText" style="font-weight: bold; color:#2d699e;font-size: 14px;" title="' + fullText + '">' + data.substring(0,97) + '...' + '</span>';
                		} else {
                			data = '<span class="hoverOverText" style="font-weight: bold; color:#2d699e;font-size: 14px;">' + fullText + '</span>';
                		}
                		if(row.subprojectCount != null && row.subprojectCount > 0 ||
                				row.repoCount != null && row.repoCount > 0) {
                			if(row.expandSubproject || row.expandRepository) {
                				cssClass = 'detail-control match';
                			} else {
                				cssClass = 'detail-control';
                			}
                			return '<a style="margin-right: 5px;" class="' + cssClass + '" href="javascript: void(0)"><i class="expand fa fa-plus-square" aria-hidden="true"></i></a>' +
            				data;
                		}
                		return data;
                	}
                	return data;
                } },
                {
                    "targets": 2, // Grant #
                     
                    "render": function (data, type, row, meta) {
                    	grant = '';
                    	if(data != null) {
                    		grant = data;
                    	}
                    	if(row.intGrantContractNum != null) {
                    		if(grant != '') {
                    			grant = grant + '<br>'
                    		}
                    		grant = grant + row.intGrantContractNum
                    	}
                    	if(type == 'display') {
                    		return grant;
                    	}
                    	return data;
                } },
                {
                "targets": 3, // PI email and name
                 
                "render": function (data, type, row, width,meta) {
                	extPi = '';
                    if (type == 'display' && row.extPiEmailAddress != null && row.extPiEmailAddress != "" &&
                    		row.extPiLastName != null && row.extPiLastName != "" &&
                    		row.extPiFirstName != null && row.extPiFirstName != "") {
                    	extPi = '<a href="mailto: ' + row.extPiEmailAddress + '">' + data + ', ' + row.extPiFirstName + '</a>';
                    } else if (row.extPiLastName != null && row.extPiLastName != "" &&
                    		row.extPiFirstName != null && row.extPiFirstName != ""){
                    	extPi =  data + ', ' + row.extPiFirstName;
                    }
                    intPi = '';
                    if (type == 'display' && row.intPiEmailAddress != null && row.intPiEmailAddress != "" &&
                    		row.intPiLastName != null && row.intPiLastName != "" &&
                    		row.intPiFirstName != null && row.intPiFirstName != "") {
                    	intPi = '<a href="mailto: ' + row.intPiEmailAddress + '">' + row.intPiLastName + ', ' + row.intPiFirstName + '</a>';
                    } else if (row.intPiLastName != null && row.intPiLastName != "" &&
                    		row.intPiFirstName != null && row.intPiFirstName != ""){
                    	intPi =  row.intPiLastName + ', ' + row.intPiFirstName;
                    }
                    if(extPi == '') {
                    	return intPi;
                    }
                    if(intPi == '') {
                    	return extPi
                    }
                    return extPi + '<br>' + intPi;
                } },
                {
                "targets": [6, 7, 8, 9, 13], // Status columns
                "orderable": true,
                "render": function (data, type, row, meta) {
                	if(type == 'display') {
                		if(data == "INPROGRESS") {
                			return '<div class="searchProgess"><img src="../images/inprogress.png" alt="In Progress" title="In Progress" width="18px" height="18px" /></div>'
                		}
                		if(data == "COMPLETED") {
                			return '<div class="searchProgess"><img src="../images/complete.png" alt="Completed" title="Completed" width="18px" height="18px"/></div>'
                		}
                		if(data == "NOTSTARTED") {
                			return '<div class="searchProgess"><img src="../images/pending.png" alt="Not Started" title="Not Started" width="18px" height="18px"></div>'
                		} else {
                 	   		return '<div style="text-align: center;">N/A</div>';
                 	   	}
                	}
                	return data;
                } },
            ]
        });
        
		
	if($("#readonly").val() == "true") {
		$("div.legend").html('<div style="display:inline; float: right;"> \
            <table style="margin-bottom: 10px; margin-right: 10px;"> \
            <tbody><tr><td style="text-align:right; font-weight:bold;">Legend:</td> \
            <td style="text-align: center; width:55px;"><img src="../images/pending.png" alt="Not Started" title="Not Started" width="18px" height="18px"></td> \
            <td style="text-align: center; width:55px;"><img src="../images/inprogress.png" alt="In Progress" title="In Progress" width="18px" height="18px"></td> \
            <td style="text-align: center; width:50px;"><img src="../images/complete.png" alt="Completed" title="Completed" width="18px" height="18px"></td> \
            <td style="text-align: center; width:40px;"><i class="fa fa-file-text fa-lg" aria-hidden="true" alt="View" title="View" style="color: #2d699e;"></i></td> \
            </tr> \
            <tr><td>&nbsp;</td> \
            <td class="legendText">Not Started</td> \
            <td class="legendText">In Progress</td> \
            <td class="legendText">Completed</td> \
            <td class="legendText">View</td> \
            </tr> \
            </tbody></table> \
            </div>');
	} else {
		$("div.legend").html('<div style="display:inline; float: right;"> \
            <table style="margin-bottom: 10px; margin-right: 10px;"> \
                <tbody><tr><td style="text-align:right; font-weight:bold;">Legend:</td> \
                <td style="text-align: center; width:55px;"><img src="../images/pending.png" alt="Not Started" title="Not Started" width="18px" height="18px"></td> \
                <td style="text-align: center; width:55px;"><img src="../images/inprogress.png" alt="In Progress" title="In Progress" width="18px" height="18px"></td> \
                <td style="text-align: center; width:50px;"><img src="../images/complete.png" alt="Completed" title="Completed" width="18px" height="18px"></td> \
                <td style="text-align: center; width:40px;"><i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="Edit" title="Edit" style="color: #2d699e;"></i></td> \
                <td style="text-align: center; width:40px;"><i class="fa fa-trash fa-lg" aria-hidden="true" alt="Delete" title="Delete" style="color: #990000;"></i></td> \
                <td style="text-align: center; width:50px;"><i class="fa fa-clone fa-lg" aria-hidden="true" title="Add New Version" alt="Add New Version" style="color: #2d699e;"></i></td> \
                <td style="text-align: center; width:55px;"><i class="fa fa-folder-open fa-lg" aria-hidden="true" alt="Add New Sub-project" title="Add New Sub-project" style="color: #2d699e;"></i></td> \
                </tr> \
                <tr><td>&nbsp;</td> \
                <td class="legendText">Not Started</td> \
                <td class="legendText">In Progress</td> \
                <td class="legendText">Completed</td> \
                <td class="legendText">Edit</td> \
                <td class="legendText">Delete</td> \
                <td class="legendText">Add New<br> Version</td> \
                <td class="legendText">Add New<br> Sub-project</td> \
                </tr> \
                </tbody></table> \
            </div>');
	}

	$("div.export").html("<a id='export-btn' href='#' aria-controls='submissionTable' tabindex='0' class='dt-button buttons-excel buttons-html5'><span>Export to Excel</span></a>");

	$("#search-form").on('click', '#search-btn', function () {
		submissionTable.ajax.reload(null , true);
		$("#searchResult").show();
	});
	
	$('#submissionTable')
    .on( 'processing.dt', function ( e, settings, processing ) {
        if(processing) {
        	$('button.has-spinner').addClass('active');
        } else {
        	$('.detail-control.match').click();
        	$('.subproject-control.match').click();
        	$('.repository-control.match').click();
        	$('button.has-spinner').removeClass('active');
        }
    } )
    .dataTable();
    
	$("#search-form").on('click', '#export-btn', function (e) {
		e.preventDefault();
		var queryString = $('#search-form').serialize();
		var order = submissionTable.order();
        var orderProperty = submissionTable.column(order[0][0]).dataSrc();
        var url = "export.action?" + queryString + "&criteria.sortBy=" + orderProperty + "&criteria.sortDir=" + order[0][1];
        
		window.location.href = url;
	});
	
	//for search.htm page -- shows/hids input field when Type of Submission is selected 
    $("div.desc").hide();
    $("input[name$='optradio']").click(function() {
        var test = $(this).val();
        $("div.desc").hide();
        $("#" + test).show();
    });

    // Sub-Project Repository Submission Status
    $('body').on('click', 'a.repoExpand', function() {
        $(this).parent().next("div").slideToggle('500');
        $(this).children("i.expand.fa").toggleClass('fa-plus-square fa-minus-square');
    });

    $("#directorSelect").change(function() {
    	if($(this).find("option:selected").val() != "")
    		$("#directorName").val($(this).find("option:selected").text());
    	else
    		$("#directorName").val("");
    });

    // Add event listener for opening and closing row details
	$('#submissionTable tbody').on('click', 'a.detail-control', function() {
	  var tr = $(this).closest('tr');
	  var row = submissionTable.row(tr);
	  if (row.child.isShown()) {
		  row.child.hide();
		  tr.removeClass('shown');
		  $(this).find("i.expand.fa").toggleClass('fa-plus-square fa-minus-square');
	  } else {
		if(row.data().expandSubproject) {
			cssClassSub = 'subproject-control match';
		} else {
			cssClassSub = 'subproject-control';
		}
		if(row.data().expandRepository) {
			cssClassRepo = 'repository-control match';
		} else {
			cssClassRepo = 'repository-control';
		}
		if(row.data().subprojectCount != null && row.data().subprojectCount > 0 &&
				row.data().repoCount != null && row.data().repoCount > 0) {
			row.child([
			$(
				'<div class="repository-div"><a style="font-size: 12px; margin-left: 25px;" class="' + cssClassRepo + '" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Project Submission Status</a></div>'
			),
			$(
				'<div class="subproject-div"><a style="font-size: 12px; margin-left: 25px;" class="' + cssClassSub + '" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Sub-projects</a></div>'
             )
             ], 
             row.node().className + " subrow"
			).show();
		}
		else if(row.data().repoCount != null && row.data().repoCount > 0) {
			row.child(
			$(
				'<div class="repository-div"><a style="font-size: 12px; margin-left: 25px;" class="' + cssClassRepo + '" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Project Submission Status</a></div>'
             ), 
             row.node().className + " subrow"
			).show();
		}
		else if(row.data().subprojectCount != null && row.data().subprojectCount > 0) {
			row.child(
			$(
				'<div class="subproject-div"><a style="font-size: 12px; margin-left: 25px;" class="' + cssClassSub + '" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Sub-projects</a></div>'
             ), 
             row.node().className + " subrow"
			).show();
		}
		$(this).find("i.expand.fa").toggleClass('fa-plus-square fa-minus-square');
	  }
	});
	
    // Add event listener for opening and closing subproject
	$('#submissionTable tbody').on('click', 'a.subproject-control', function() {
		//Check if repository child row exists
		var hasRepo = $(this).closest('tr').prev().find("div:first").hasClass("repository-div");
		if(hasRepo) {
			var tr = $(this).closest('tr').prev().prev();
		} else {
			var tr = $(this).closest('tr').prev();
		}
		var row = submissionTable.row(tr);
	    var id = row.data().id;
	    var accessionNum = $('#accessionNumber').val();

	    // If subproject is shown, this is a toggle to close it.
		if($(this).hasClass('shown')) {
			if(!hasRepo) {
				//It does not have repo, so just collapse it and toggle to plus sign
				row.child(
			    	$(
			    		'<div class="subproject-div"><a style="font-size: 12px; margin-left: 25px;" class="subproject-control" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Sub-projects</a></div>'
			         ), 
			             tr.get(0).className + " subrow"
			   	).show();
			} else {
				//It has repo, so collapse and recreate with the repo row
				row.child( [
				               tr.next(),
						       $(
						        '<div class="subproject-div"><a style="font-size: 12px; margin-left: 25px;" class="subproject-control" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Sub-projects</a></div>'
						        )
						        
						    ], tr.get(0).className + " subrow" ).show();
			}
		} else {
			// Need subproject expand, so retrieve the data
			$.ajax({
				url: 'getSubprojects.action',
				data: {'projectId': id, 'criteria.accessionNumber': accessionNum},
				type: 'post',
				async:   false,
				success: function(msg){
					result = $.trim(msg);
					table = $(result).find(".subproject-table").html();
					if (hasRepo) { // If repository is also expanded, then add the child row
						row.child( [
						        tr.next(),
						        table, 
						    ], tr.get(0).className + " subrow" ).show();
					} else { // If repository child doesn't exist, just add the subproject row
						row.child(table, tr.get(0).className + " subrow").show();
					}
				}, 
				error: function(){}	
			});
			
		}
	});
	
	// Add event listener for opening and closing repository
	$('#submissionTable tbody').on('click', 'a.repository-control', function() {
		var tr = $(this).closest('tr').prev();
		var row = submissionTable.row(tr);
		var id = row.data().id;
		var hasSub = tr.next().next().find("div:first").hasClass("subproject-div");

		// If repo is shown, this is a toggle to close it.
		if($(this).hasClass('shown')) {
			if(!hasSub) {
				//It does not have subproject, so just collapse it and toggle to plus sign
				row.child(
		    		$(
		    			'<div class="repository-div"><a style="font-size: 12px; margin-left: 25px;" class="repository-control" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Project Submission Status</a></div>'
		             ), 
		             tr.get(0).className + " subrow"
		   		).show();
			}
			else {
				//It has subproject, so collapse and recreate with the subproject row
				row.child( [
					       $(
					        '<div class="repository-div"><a style="font-size: 12px; margin-left: 25px;" class="repository-control" href="javascript: void(0)">' + '<i class="expand fa fa-plus-square" aria-hidden="true"></i>&nbsp;Project Submission Status</a></div>'
					        ), 
					        tr.next().next()
					    ], tr.get(0).className + " subrow" ).show();
			}
		} else {
			// Need repo expand, so retrieve the data
			$.ajax({
				url: 'getRepoInfo.action',
				data: {projectId: id},
				type: 'post',
				async:   false,
				success: function(msg){
					result = $.trim(msg);
					table = $(result).find(".repository-table").html();
					if (hasSub) { // If subproject is also expanded, then add the child row
						row.child( [
						        table,
						        tr.next().next()
						    ], tr.get(0).className + " subrow" ).show();
					} else { // If subproject child doesn't exist, just add the repository row
						row.child(table, tr.get(0).className + " subrow").show();
					}
				}, 
				error: function(){}	
			});
			
		}
	});

	$("#selectFrom").change(function () {
		var submissionFromId = $("#selectFrom").val();
		if(submissionFromId == 40) {
			$("#directorSelect").val($("#directorSelect option:first").val());
			$('#directorSelect').attr('disabled', 'disabled');
		} else {
			$('#directorSelect').removeAttr('disabled');
		}
	});
	$("#selectFrom").change();
});

function performSearch()
{
  var searchFlag = $("#isReturnToSearch").val();
  if(searchFlag === "true") {
		$("#search-btn").click();
  }
}

function deleteSubmission(projectId, subprojectCount)
{
	var result = "", msg = "Are you sure you want to delete this submission?";
	if(subprojectCount != null && subprojectCount > 0) {
		msg = msg + " If you select 'OK' all associated sub-projects will also be deleted.";
	}
	bootbox.confirm(msg, function(ans) {
		  if (ans) {
			  $.ajax({
					url: "deleteProject.action",
					type: "post",
					data: {projectId: projectId},
					success: function(msg){
						result = $.trim(msg);
					}, 
					error: function(){}		
				}).done(function() {
					if(result.indexOf("success") > 0) {
						$('#submissionTable').DataTable().ajax.reload(null , false);
						if($('#existingSubProjects').length) { 
							$('#existingSubProjects').modal('hide');
						}
						bootbox.alert(result, function() {
				  			return true;
						});
					}
					else {
						bootbox.alert(result, function() {
				  			return true;
						});
					}
				});
			    return true;
		  }
	});
}

function newVersion(projectId, subprojectCount)
{
	var url = "../manage/createNewProjectVersion.action?projectId="+ projectId;
	var msg = "Any associated sub-projects will be copied over to the new version of the Project.";
	if(subprojectCount != null && subprojectCount > 0) {
		bootbox.alert(msg, function() {
			window.location.href = url;
			return true;
		});
	} else {
		window.location.href = url;
	}
}

$(".helpfile").click(function(){
	
	var url = "/documentation/application/Find_Submissions_help.pdf";
	var winName = "Find Submissions Help File";
	var features = "menubar=yes,scrollbars=yes,resizable=yes,width=800,height=800";
	var newWin = window.open(url, winName, features);
});




