getEmployees();

//let mytable = document.getElementById('employees-table');

async function getEmployees(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employees');
	let data = await res.json();
	populateEmployees(data);
}

function populateEmployees(data){
	let mytable = document.getElementById('employees-table');
	for(employeesObj of data){
		let tableLength = mytable.length;
		let row = document.getElementsByTagName('tbody')[0].insertRow(tableLength);
		//let employee = document.createElement('tr');
		////let row = mytable.insertRow();  ---->this is the good old one
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


document.getElementById('searchbtn').addEventListener('click', getSpecificEmployee);

async function getSpecificEmployee(e){
	//alert('function');
	e.preventDefault();
	
	let nameEmployee = document.getElementById('searchEmploy').value;
	
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employee?username='+nameEmployee);
	let data =await res.json();

	 let table = document.getElementById('employees-table');
	 let row = table.getElementsByTagName('tbody')[0];
	 deleteTBody(row);
	 let node = document.createElement('tbody');
	 table.appendChild(node);
	 
	 populateEmployee(data);
}

function populateEmployee(specificEmploy){
	let mytable = document.getElementById('employees-table');
	for(employeesObj of specificEmploy){
		let tableLength = mytable.length;
		//let row = document.createElement('tbody').insertRow(tableLength);
		let row = document.getElementsByTagName('tbody')[0].insertRow(tableLength);
		//let employee = document.createElement('tr');
		////let row = mytable.insertRow();  ---->this is the good old one
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
		
		//old_tbody.parentNode.replaceChild(old_tbody, row);
	
	}
}

function deleteTBody (row) {
    row.parentNode.removeChild(row);
};








