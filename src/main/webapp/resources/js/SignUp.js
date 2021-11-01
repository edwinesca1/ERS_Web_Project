let form = document.getElementById("login").addEventListener('submit', signup);

async function signup(e){
	e.preventDefault();
	let fName = document.getElementById('fName').value;
	let lName = document.getElementById('lName').value;
	let email = document.getElementById('email').value;
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let confirmpassword = document.getElementById("confirmpassword").value;
	
	let newUser = {
		fName,
		lName,
		email,
		username,
		password,
		confirmpassword
	};
	
	if(password != confirmpassword){
		alert('Passwords does not match!');
	}else{
		try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/signup', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(newUser)
		});
		let res = await req.json();
	
			if(res.transactionStatus === 'Successful'){
				alert(res.message);
				console.log('inside if transactionstatus');
				location.href = '../html/login.html';
				//window.location = '../html/login.html';
			}else{
				console.log('inside else transactionstatus');
				alert(res.message);
			}
		}catch(e){
			alert(e.message);
			//alert('Uh oh, something went wrong!');
		}
	}
}