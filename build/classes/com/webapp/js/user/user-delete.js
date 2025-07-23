
function submitDelete(){
	const form = document.getElementById('userDeleteForm');
	const formData = new FormData(form);
	
	fetch("/user/delete", {
		method: 'DELETE',
		body: new URLSearchParams(formData) // FormData -> URL 인코딩 형태로 변환
	})
	.then(res => res.json())
	.then(data => {
		if(data.success){
			alert("탈퇴 완료");
		} else{
			alert("오류 발생 : " + data.message);
		}
	})
	
}