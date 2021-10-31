checkSession()

function checkSession(){
	fetch('http://localhost:8080/ExpenseReimbursementSystem/api/checkSession').then(function(Response) {
		console.log(Response.status)
		if(Response.status === 401)
		{
			alert('Access denied, you are not logged in!');
			location.href = '../html/login.html';
		}else{
			getReimbursements();
		}
	})
}
//-------------------------------verifying session----------------------------------------------------------

async function getReimbursements(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/Allreimbursements');
	let data = await res.json();
	populateRequests(data);
	populateRequestsR(data);
}

function populateRequests(data){
	let mytable1 = document.getElementById('pending-table');
	let node = document.createElement('tbody');
	mytable1.appendChild(node);
	mytable1.getElementsByTagName('tbody')[0].setAttribute('id','pending-tbody');
	for(requestObj of data){
		if(requestObj['reimbStatusId'] == 1 || requestObj['reimbStatusId'] == "1"){
			//let tableLength = mytable1.length;
			let row = document.getElementById('pending-tbody').insertRow();
			//let tableLength = mytable1.length;
			//let row = document.getElementsByTagName('tbody')[0];
			//let row = mytable1.insertRow();
			let cellp1 = row.insertCell(0);
			let cellp2 = row.insertCell(1);
			let cellp3 = row.insertCell(2);
			let cellp4 = row.insertCell(3);
			let cellp5 = row.insertCell(4);
			let cellp6 = row.insertCell(5);
			let cellp7 = row.insertCell(6);
			cellp1.innerHTML = requestObj['reimbId'];
			cellp2.innerHTML = requestObj['afullName'];
			cellp3.innerHTML = requestObj['reimbType'];
			cellp4.innerHTML = requestObj['description'];
			cellp5.innerHTML = requestObj['amount'];
			cellp6.innerHTML = requestObj['reimbStatus'];
			cellp7.innerHTML = requestObj['reimbSubmitted'];
			console.log(data);
			}
		}
		/*
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
			cellr2.innerHTML = requestObj1['afullName'];
			cellr3.innerHTML = requestObj1['rfullName'];
			cellr4.innerHTML = requestObj1['reimbType'];
			cellr5.innerHTML = requestObj1['description'];
			cellr6.innerHTML = requestObj1['amount'];
			cellr7.innerHTML = requestObj1['reimbStatus'];
			cellr8.innerHTML = requestObj1['reimbSubmitted'];
			cellr9.innerHTML = requestObj1['reimbResolved'];
			console.log(data);
		}
	}*/
}

function populateRequestsR(data){
	let mytable2 = document.getElementById('resolved-table');
	let node = document.createElement('tbody');
	mytable2.appendChild(node);
	mytable2.getElementsByTagName('tbody')[0].setAttribute('id','resolved-tbody');
	for(requestObj1 of data){
			if(requestObj1['reimbStatusId'] != 1 || requestObj1['reimbStatusId'] != "1"){
			//let tableLength = mytable2.length;
			let row = document.getElementById('resolved-tbody').insertRow();
			//let row = document.getElementsByTagName('tbody')[0];
			//let row = mytable2.insertRow();
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
			cellr2.innerHTML = requestObj1['afullName'];
			cellr3.innerHTML = requestObj1['rfullName'];
			cellr4.innerHTML = requestObj1['reimbType'];
			cellr5.innerHTML = requestObj1['description'];
			cellr6.innerHTML = requestObj1['amount'];
			cellr7.innerHTML = requestObj1['reimbStatus'];
			cellr8.innerHTML = requestObj1['reimbSubmitted'];
			cellr9.innerHTML = requestObj1['reimbResolved'];
			console.log(data);
}}}

document.getElementById('searchbtn').addEventListener('click', specificReimbEmployee);

async function specificReimbEmployee(e){
	e.preventDefault();
	
	let nameEmployee = document.getElementById('searchEmploy').value;
	
	res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/filteredRequestE?eName='+ nameEmployee);
	let data = await res.json();

	 let ptbody = document.getElementById('pending-tbody');
	 let ptable = ptbody.parentElement;
	 ptable.removeChild(ptbody);
	 //let row = document.getElementsByTagName('tbody')[0];
	 //row.parentNode.removeChild(row);
	 //deleteTBody(row);
	 //let node = document.createElement('tbody');
	 //table.appendChild(node);
	 
	 let rtbody = document.getElementById('resolved-tbody');
	 let rtable = rtbody.parentElement;
	 rtable.removeChild(rtbody);
	 //let table2 = document.getElementById('resolved-table');
	 //let row2 = table2.getElementsByTagName('tbody')[0];
	 //row2.parentNode.removeChild(row2);
	 //deleteTBody2(row2);
	 //let node2 = document.createElement('tbody');
	 //table2.appendChild(node2);
	 
	 populateRequests(data);
	 populateRequestsR(data);
}

function deleteTBody (row) {
	console.log('deleting tbody');
    row.parentNode.removeChild(row);
};

function deleteTBody2 (row2) {
	console.log('deleting tbody 2');
    row2.parentNode.removeChild(row2);
};

