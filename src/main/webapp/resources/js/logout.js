removeCurrentSession();

async function removeCurrentSession(){
	await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/logout').then(function(Response) {
		console.log(Response.status)
		location.href = '../html/login.html';
	})
}