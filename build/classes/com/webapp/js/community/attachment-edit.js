// 1. 현재 업로드된 파일 지우기
function removeExistingFile(attachmentId){
	const fileDiv = document.getElementById('file_' + attachmentId);
	if(fileDiv){
		fileDiv.remove();
	}
}

// 2. 새로운 파일 목록을 관리할 전역 변수 (FileList 또는 File 객체의 배열)
let selectedNewFiles = []; // 사용자가 새로 선택한 파일들을 저장한 배열

// 3. 파일 입력 필드 가져오기
const newFileInput = document.getElementById('file');

// 4. 선택된 새 파일들을 보여줄 DOM 요소 (JSP에 추가해야함)
const newFilesDisplayArea = document.createElement('div');
newFilesDisplayArea.id = 'newlySelectedFiles';
newFileInput.parentNode.insertBefore(newFilesDisplayArea, newFileInput.nextSibling); // 파일 입력 필드 바로 뒤에 추가

// 5. 파일 입력 필드의 'change' 이벤트 리스너
newFileInput.addEventListener('change', function(event) {
	// 새로 선택된 파일들 FileList 객체
	const files = event.target.files;
	
	// 기존 목록을 덮어쓰지 않고 추가
	for(let i =0; i < files.length; i++){
		const newFile = files[i];
		// 중복 파일명 체크 (필요하다면, 여기서는 간단히 추가)
		selectedNewFiles.push(newFile);
	}
	
	// 선택된 파일 목록을 UI에 업데이트
	updateNewFilesDisplay();
	
	// 중요: input type="file"의 파일 목록은 비워둡니다. (그래야 다음 선택 시 이전에 선택했던 파일들이 사라지지 않음)
	// 하지만 이 코드를 넣으면 FormData로 보낼 때 이 input의 파일이 전송되지 않으므로,
	// 이 방식 대신 Form submit을 가로채서 FormData를 직접 구성하는 방식(아래)을 사용해야 합니다.
	// event.target.value = ''; // 이걸 사용하면 파일이 서버로 안감.
});
 

//6. 선택된 파일목록을 UI에 표시하는 함수 
function updateNewFilesDisplay(){
	newFilesDisplayArea.innerHTML = ''; // 기존목록 초기화
	
	selectedNewFiles.forEach((file, index) => {
		const fileEntry = document.createElement('div');
		fileEntry.id = `new_file_${index}`; // 각 파일에 고유 id 부여
		fileEntry.innerHTML = `<span>${file.name} (${(file.size / 1024 / 1024).toFixed(2)} MB)</span>
		                       <button type="button" onclick="removeNewFile(${index})">삭제</button>`;
		newFilesDisplayArea.appendChild(fileEntry); 			
	});
}

// 7. 새로 추가된 파일 목록에서 특정 파일 제거 함수
function removeNewFile(index) {
	selectedNewFiles.splice(index, 1); // 배열에서 해당 인덱스의 파일 제거
	updateNewFilesDisplay(); // UI 업데이트
}

//8. 폼 제출 시 FormData 객체에 직접 파일 추가 
// 게시글 작성 폼의 submit 이벤트를 가로챈다.
document.querySelector('form').addEventListener('submit', function(event) {
	event.preventDefault(); // 기본 폼 제출 동작을 막음
	
	const form = event.target;
	
	const originalFileName = newFileInput.name; // 원래 name 속성 저장
	newFileInput.name = ''; // name 속성을 제거하여 FormData에 자동으로 포함되지 않도록 함
	
	const formData = new FormData(form); // 현재 폼의 모든 데이터를 FormData 객체에 담음
	
	
	// selectedNewFiles 배열에 있는 파일들을 FormData에 추가
	// 서버의 Part 객체는 name="file"로 넘어오도록 동일한 name 사용
	selectedNewFiles.forEach((file) => {
		formData.append('file', file, file.name); // 'file'은 서버에서 Part를 받을 때의 name
	});
	
	newFileInput.name = originalFileName; // 안정성을 위해 원래 이름으로 되돌리기
	
	// 서버로 FormData 전송 (XMLHttpRequest 또는 fetch API 사용)
	fetch(form.action, {
		method: form.method,
		body: formData
	})	
	.then(response => response.json()) 
	.then(data => {
		window.location.href = data.redirectUrl;
	})
	.catch(error => {
		console.error('Error:', error);
		alert('게시글 작성 중 오류가 발생했씁니다. ' + error.message);
	});
});

// 중요: JSP의 <input type="file" id="file" name="file" multiple>은 그대로 둡니다.
// 다만, form submit을 가로채서 JS가 FormData를 구성하여 보내기 때문에,
// 실제 이 input의 value는 서버로 직접 전송되지 않습니다.
// JS가 files 배열을 관리하고 그것을 FormData에 append하는 방식입니다.

