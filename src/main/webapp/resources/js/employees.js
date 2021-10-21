getEmployees();

//let mytable = document.getElementById('employees-table');

async function getEmployees(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employees');
	let data = await res.json();
	populateEmployees(data);
}

function populateEmployees(data){
	for(employeesObj of data){
		let mytable = document.getElementById('employees-table');
		//let employee = document.createElement('tr');
		let row = mytable.insertRow();
		/*mytable.innerHTML = `<td>${postObj.userId}</td>`
							 `<td>${postObj.username}</td>`
							 `<td>${postObj.fName}</td>`
							 `<td>${postObj.lName}</td>`
							 `<td>${postObj.email}</td>`;*/
		let cell1 = row.insertCell(0);
		let cell2 = row.insertCell(1);
		let cell3 = row.insertCell(2);
		let cell4 = row.insertCell(3);
		let cell5 = row.insertCell(4);
		cell1.innerHTML = employeesObj['userId'];
		cell2.innerHTML = employeesObj['username'];
		cell3.innerHTML = employeesObj['fName'];
		cell4.innerHTML = employeesObj['lName'];
		cell5.innerHTML = employeesObj['email'];
		console.log(cell2);
	}
}