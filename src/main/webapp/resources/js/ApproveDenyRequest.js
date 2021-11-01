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
			cellp2.innerHTML = requestObj['afullName'];
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

async function approveReimbursement(e){
	e.preventDefault();
	let	requestID = document.getElementById('ID').value;
	let	finalStatus = 2;
	
	let resol = {
		requestID,
		finalStatus
	};
	
	//alert('approve: '+requestID);
	try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/ResolveReimbursement', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
			body: JSON.stringify(resol)
		}).then(function(Response) {
		console.log(Response.status)
		if(Response.status === 200)
		{
			alert('Reimbursement resolved: Approved!');
			location.href = '../html/ApproveDenieRequest.html';
		}});
	}catch(e){
		console.log('Something went wrong');
	}
	
}

async function denyReimbursement(e){
	e.preventDefault();
	let	requestID = document.getElementById('ID').value;
	let	finalStatus = 3;
	
	let resol = {
		requestID,
		finalStatus
	};
	
	//alert('deny: '+requestID);
	try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/ResolveReimbursement', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
			body: JSON.stringify(resol)
		}).then(function(Response) {
		console.log(Response.status)
		if(Response.status === 200)
		{
			alert('Reimbursement resolved: Denied!');
			location.href = '../html/ApproveDenieRequest.html';
		}});;
	}catch(e){
		console.log('Something went wrong');
	}
}

//--------------------------------filtered search------------------------------------------------------

document.getElementById('searchbtn').addEventListener('click', filteredPendingReimbursments);

async function filteredPendingReimbursments(e){
	e.preventDefault();
	let filterValue = document.getElementById('searchEmploy').value;
	let rdbtn = document.getElementsByName('metod');
	let filterType;
              
            for(i = 0; i < rdbtn.length; i++) {
                if(rdbtn[i].checked)
                filterType = rdbtn[i].value;
            }
    let res;
    let data;
           
    if(filterType == 1){
		//alert('By Request ID');
		res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/filteredRequestI?reimb='+filterValue);
		data = await res.json();
	}else if(filterType == 3){
		//alert('By Username');
		res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/filteredRequestU?name='+filterValue);
		data = await res.json();
	}else{
		//alert('By Employee name');
		res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/filteredRequestE?eName='+filterValue);
		data = await res.json();
	}
	
	let table = document.getElementById('pending-table');
	 let row = table.getElementsByTagName('tbody')[0];
	 deleteTBody(row);
	 let node = document.createElement('tbody');
	 table.appendChild(node);
	 
	 populateRequests(data);
}

function deleteTBody (row) {
    row.parentNode.removeChild(row);
};


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

