document.querySelectorAll('.vote-button').forEach(button => {
	button.addEventListener('click', async function() {
		const postId = this.dataset.postId;
		const voteType = this.dataset.voteType; 
		
		try{
			const response = await fetch('/category/list/post/vote', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({postId: parseInt(postId), voteType: voteType})	
			});
			
			if(response.ok){
				const data = await response.json(); // {newLikeCount: N, newDislikeCount : M}
				
				//UI 업데이트
				document.getElementById(`likeCount-${postId}`).textContent = data.newLikeCount;
				document.getElementById(`dislikeCount-${postId}`).textContent = data.newDislikeCount;
				
				// 사용자가 투표했음을 나타내는 UI 변경 (선택사항)
                // 예: 버튼 색상 변경, 토글 등
                console.log('투표 성공! 새로운 좋아요 수:', data.newLikeCount, '싫어요 수:', data.newDislikeCount);
			} else {
				const errorData = await response.json();
				alert('투표 처리 중 오류 발생: ' + errorData.message);
			}
		} catch (error){
			console.error('AJAX 요청 실패:', error);
			alert('네트워크 오류가 발생했습니다. 다시 시도해주세요.');
		}
	});
});