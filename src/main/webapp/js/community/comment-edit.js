function enableEdit(commentId, content, postId, categoryId){
	// 기존 내용을 담은 태그를 입력폼으로 수정함
	const contentEl = document.getElementById(`content-${commentId}`);
	const commentEditButton = document.querySelectorAll(".commentEditButton");
	
	commentEditButton.forEach(button => button.style.display = "none");
	
	contentEl.innerHTML = `
		<form method="post" action="/category/list/post/comment/update?po=${postId}&c=${categoryId}">
			<input type="hidden" name="commentId" value="${commentId}" />
			<textarea name="content">${content}</textarea>
			<button type="submit">수정완료</button>
			<button type="button" onclick="cancelEdit(${commentId}, '${content}')">취소</button>
		</form>
	`;
}

function cancelEdit(commentId, originalContent){
	const commentEditButton = document.querySelector(".commentEditButton");

	commentEditButton.forEach(button => button.style.display = "inline");

	
	// 수정폼에서 일반 출력태그로 바꿈
	const contentEl = document.getElementById(`content-${commentId}`);
	
	contentEl.innerHTML = originalContent;
}