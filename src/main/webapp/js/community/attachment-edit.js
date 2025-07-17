function removeExistingFile(attachmentId){
	const fileDiv = document.getElementById('file_' + attachmentId);
	if(fileDiv){
		fileDiv.remove();
	}
}