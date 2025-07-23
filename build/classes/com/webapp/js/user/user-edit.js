document.addEventListener('DOMContentLoaded', () => {
	const editBtn = document.getElementById('editBtn');
	const saveBtn = document.getElementById('saveBtn');
	const inputs = document.querySelectorAll('#userForm input');
	
	if (editBtn){
		editBtn.addEventListener('click', ()=> {
			inputs.forEach(input => input.removeAttribute('readonly'));
			editBtn.style.display = 'none';
			saveBtn.style.display = 'inline';
		});
	}
	
	const form = document.getElementById('userForm');
	if(form){
		form.addEventListener('submit', function (e) {
			e.preventDefault();
			
			const formData = new FormData(form);
			
			fetch('/user/update', {
				method: 'POST',
				body: formData
			})
			.then(res => res.json())
			.then(data => {
			});		
			inputs.forEach(input => input.setAttribute('readonly', true));
			saveBtn.style.display = 'none';
			editBtn.style.display = 'inline';		
		});
	}
});