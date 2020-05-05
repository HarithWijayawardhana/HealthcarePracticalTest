$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
		System.out.println("MAMA jquery");
	}
	$("#alertError").hide();
});

//UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidItemIDSave").val(
					$(this).closest("tr").find('#hidItemIDUpdate').val());
			$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#password").val($(this).closest("tr").find('td:eq(1)').text());
			$("#mobile").val($(this).closest("tr").find('td:eq(2)').text());
			$("#email").val($(this).closest("tr").find('td:eq(3)').text());
			$("#address").val($(this).closest("tr").find('td:eq(4)').text());
		});

$(document).on("click", "#btnSave", function(event) {
	// Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	// If not valid
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "patientAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "patientAPI",
		type : "DELETE",
		data : "userId=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



function validateItemForm() {
	// Validations
	// NAME
	if ($("#userName").val().trim() == "") {
		return "Insert name.";
	}

	// PASSWORD
	if ($("#password").val().trim() == "") {
		return "Insert password.";
	}

	// MOBILE
	if ($("#mobile").val().trim() == "") {
		return "Insert mobile.";
	}

	// EMAIL
	if ($("#email").val().trim() == "") {
		return "Insert email.";
	}
	// ADDRESS
	if ($("#address").val().trim() == "") {
		return "Insert address.";
	}
	return true;
}

/*
 * function getStudentCard(name, gender, year) { var student = ""; // Generate
 * card
 * 
 * var title = (gender == "Male") ? "Mr." : "Ms."; var yearNumber = ""; switch
 * (year) { case "1": yearNumber = "1st"; break; case "2": yearNumber = "2nd";
 * break; case "3": yearNumber = "3rd"; break; case "4": yearNumber = "4th";
 * break; }
 * 
 * var student = ""; student += "<div class=\"student card bg-light m-2\"
 * style=\"max-width: 10rem;S float: left;\">"; student += "<div
 * class=\"card-body\">"; student += title + " " + name + ","; student += "<br>";
 * student += yearNumber + " year"; student += "</div>"; student += "<input
 * type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">"; student += "</div>";
 * 
 * return student; }
 * 
 * $(document).on("click", ".remove", function(event) {
 * $(this).closest(".student").remove();
 * 
 * $("#alertSuccess").text("Removed successfully."); $("#alertSuccess").show();
 * });
 */
