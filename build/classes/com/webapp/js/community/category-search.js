const form = document.getElementById('categorySearchForm');
const resultRow = document.getElementById('resultRow');

form.addEventListener('submit', function(e) {
	e.preventDefault(); 
	
	const formData = new FormData(form);
	
	const params = new URLSearchParams();
	for(let [key, value] of formData.entries()){
		params.append(key, value);
	}
	
	fetch('/category', {
		method: 'POST',
		headers: {
		     'Content-Type': 'application/x-www-form-urlencoded',
		 },
		body: params
	})
	.then(response => response.json())
	.then(data => {
		resultRow.innerHTML = ''; // 기존 결과 제거
		
		if(data.length === 0){
			resultRow.innerHTML = '<p class="text-center">결과가 없습니다.</p>';
			return;
		}
		
		data.forEach(category => {
			const col = document.createElement('div');
			col.className = 'col-md-3 mb-3';
			
			col.innerHTML = `
			  <div class="card text-center">
			    <div class="card-body">
			      <h5 class="card-title mb-3">${category.name}</h5>
			      <p class="card-text">${category.description || '카테고리 설명 없음'}</p>
			      <a href="/category/list?c=${category.id}&p=1" class="card-link">링크</a>
			    </div>
			  </div>
			`;
			
			resultRow.appendChild(col);
		});
	})
	.catch(error => {
		console.error('검색 오류:', error);
		resultRow.innerHTML = '<p class="text-danger">검색 중 오류가 발생했습니다.</p>';
	});
});