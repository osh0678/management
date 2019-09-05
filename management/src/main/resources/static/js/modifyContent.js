function setModifyInfo(userName, telecomType, phoneId, userSeq, creditRating,
		salary, workName, workType, workYn, carYn, houseType, location, userNo) {
	document.getElementById("userName").value = userName
	document.getElementById("telecomType").value	= telecomType
	document.getElementById("phoneId").value		= phoneId
	document.getElementById("userSeq").value		= userSeq
	document.getElementById("creditRating").value	= creditRating
	document.getElementById("salary").value			= salary
	document.getElementById("workName").value		= workName
	document.getElementById("workType").value		= workType
	document.getElementById("workYn").value			= workYn
	document.getElementById("carYn").value			= carYn
	document.getElementById("houseType").value		= houseType
	document.getElementById("location").value		= location
	document.getElementById("userNo").value		= userNo
	$('#modifyInfo').modal('show');
}