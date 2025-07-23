function loadCategories(){
	const favorites = [];
	
	for(let key in localStorage){
		if(key.startsWith("category_")) {
			const value = localStorage.getItem(key);
			try {
				const obj = JSON.parse(value);
				if(obj && obj.id && obj.name && obj.visitCount) {
					favorites.push(obj)
				}
			} catch (e) {}
		}
	}
	
	// 방문수 기준 정렬
	favorites.sort((a, b) => b.visitCount - a.visitCount);
	
	const container = document.getElementById("favoriteCategoriesList");
	container.innerHTML = "";
	
	favorites.slice(0,5).forEach(category => {
		const a = document.createElement("a");
		a.href = `/category/list?c=${category.id}&p=1`;
		a.className = "btn btn-outline-primary rounded-pill px-3";
		a.textContent = `${category.name} (${category.visitCount})`;
		container.appendChild(a);
	});
}

loadCategories();

