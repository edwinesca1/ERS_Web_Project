checkSession()

function checkSession(){
	fetch('http://localhost:8080/ExpenseReimbursementSystem/api/checkSession').then(function(Response) {
		console.log(Response.status)
		if(Response.status === 401)
		{
			alert('Access denied, you are not logged in!');
			location.href = '../html/login.html';
		}else{
			getPendingRequests();
		}
	})
}
//-------------------------------verifying session----------------------------------------------------------

async function getPendingRequests(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/Allreimbursements');
	let data = await res.json();
	populateRequests(data);
}


function populateRequests(data){
	let mytable = document.getElementById('pending-table');
	for(requestObj of data){
		if(requestObj['reimbStatusId'] == 1 || requestObj['reimbStatusId'] == "1"){
		let tableLength = mytable.length;
		let row = document.getElementsByTagName('tbody')[0].insertRow(tableLength);
		row.addEventListener('click', function(){ var ths = document.querySelectorAll("#pending-table th");
           let obj = [].reduce.call(ths, function(obj, th, i){
               obj[th.textContent] = row.cells[i].textContent;
               
		        return obj;
           }, {});
           setFormValues(obj);
           console.log(obj);});

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
			console.log(cellp2);
	  }
	}
}


function setFormValues(obj){
	console.log(obj)
	//e.preventDefault();
		const keys = Object.keys(obj);
		const length = keys.length;
//start at 1 to skip the userId key and length -2 to avoid las 2 key values
		for(let i = 0; i < length; i++){
		    const key = keys[i]
		    document.getElementById(key).value = obj[key];
		}
}	

document.getElementById('btnapprove').addEventListener('click', approveReimbursement);
document.getElementById('btndeny').addEventListener('click', denyReimbursement);

async function approveReimbursement(){
	//e.preventDefault();
	let	requestID = document.getElementById('ID').value;
	let	finalStatus = 2;
	
	let resol = {
		requestID,
		finalStatus
	};
	
	alert('approve: '+requestID);
	try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/ResolveReimbursement', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
			body: JSON.stringify(resol)
		});
	}catch(e){
		console.log('Something went wrong');
	}
	
}

async function denyReimbursement(){
	//e.preventDefault();
	let	requestID = document.getElementById('ID').value;
	let	finalStatus = 3;
	
	let resol = {
		requestID,
		finalStatus
	};
	
	alert('deny: '+requestID);
	try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/ResolveReimbursement', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
			body: JSON.stringify(resol)
		});
	}catch(e){
		console.log('Something went wrong');
	}
}


/*
let rowSelected = [].slice.call(document.querySelectorAll("pending-table tr"), 1).forEach(function(row){
      row.addEventListener("click", function(){
           var ths = document.querySelectorAll("pending-table th");
           var obj = [].reduce.call(ths, function(obj, th, i){
               obj[th.textContent] = row.cells[i].textContent;
               return obj;
           }, {});
           console.log(obj);
      });
});*/

