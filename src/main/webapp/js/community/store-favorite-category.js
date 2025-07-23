document.querySelectorAll(".card-link").forEach(link => {
	link.addEventListener("click", function(event){
		const card = link.closest('.category-card');
		const categoryId = card.getAttribute("data-category-id");
		const categoryName = card.getAttribute("data-category-name");

		// localStorage 기반 횟수 기록
		const key = `category_${categoryId}`;
		const stored = JSON.parse(localStorage.getItem(key));

		const newData = {
			id: categoryId,
			name: categoryName,
			visitCount: stored ? stored.visitCount + 1 : 1,
		};


		localStorage.setItem(key, JSON.stringify(newData));
		console.log(`방문 기록 저장됨:`, newData);
		
	});
});
/** 
const categoryCards = document.querySelectorAll(".category-card");

categoryCards.forEach(card => {
		card.addEventListener("click", () => {
			const categoryId = card.getAttribute("data-category-id");
			const categoryName = card.getAttribute("data-category-name");
			
			// localStorage 기반 횟수 기록
			const key = `category_${categoryId}`;
			const stored = JSON.parse(localStorage.getItem(key));
			
			const newData = {
				id: categoryId,
				name: categoryName,
				visitCount: stored ? stored.visitCount + 1 : 1,
			};

			
			localStorage.setItem(key, JSON.stringify(newData));
			console.log(`방문 기록 저장됨:`, newData);
		});
});
*/