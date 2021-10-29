checkSession()

function checkSession(){
	fetch('http://localhost:8080/ExpenseReimbursementSystem/api/checkSession').then(function(Response) {
		console.log(Response.status)
		if(Response.status === 401)
		{
			alert('Access denied, you are not logged in!');
			location.href = '../html/login.html';
		}
	})
}
//-------------------------------verifying session----------------------------------------------------------


getReimbursements();

async function getReimbursements(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employeeRequests');
	let data = await res.json();
	populateRequests(data);
}

function populateRequests(data){
	for(requestObj of data){
		if(requestObj['reimbStatusId'] == 1 || requestObj['reimbStatusId'] == "1"){
			let mytable1 = document.getElementById('pending-table');
			let row = mytable1.insertRow();
			let cellp1 = row.insertCell(0);
			let cellp2 = row.insertCell(1);
			let cellp3 = row.insertCell(2);
			let cellp4 = row.insertCell(3);
			let cellp5 = row.insertCell(4);
			let cellp6 = row.insertCell(5);
			let cellp7 = row.insertCell(6);
			cellp1.innerHTML = requestObj['reimbId'];
			cellp2.innerHTML = requestObj['aName'] + " "+ requestObj['aLastname'];
			cellp3.innerHTML = requestObj['reimbType'];
			cellp4.innerHTML = requestObj['description'];
			cellp5.innerHTML = requestObj['amount'];
			cellp6.innerHTML = requestObj['reimbStatus'];
			cellp7.innerHTML = requestObj['reimbSubmitted'];
			console.log(data);
			}
		}
		
		for(requestObj1 of data){
			if(requestObj1['reimbStatusId'] != 1 || requestObj1['reimbStatusId'] != "1"){
			let mytable2 = document.getElementById('resolved-table');
			let row = mytable2.insertRow();
			let cellr1 = row.insertCell(0);
			let cellr2 = row.insertCell(1);
			let cellr3 = row.insertCell(2);
			let cellr4 = row.insertCell(3);
			let cellr5 = row.insertCell(4);
			let cellr6 = row.insertCell(5);
			let cellr7 = row.insertCell(6);
			let cellr8 = row.insertCell(7);
			let cellr9 = row.insertCell(8);
			cellr1.innerHTML = requestObj1['reimbId'];
			cellr2.innerHTML = requestObj1['aName'] + " " + requestObj['aLastname'];
			cellr3.innerHTML = requestObj1['rName'] + " " + requestObj['rLastname'];
			cellr4.innerHTML = requestObj1['reimbType'];
			cellr5.innerHTML = requestObj1['description'];
			cellr6.innerHTML = requestObj1['amount'];
			cellr7.innerHTML = requestObj1['reimbStatus'];
			cellr8.innerHTML = requestObj1['reimbSubmitted'];
			cellr9.innerHTML = requestObj1['reimbResolved'];
			console.log(data);
		}
	}
}