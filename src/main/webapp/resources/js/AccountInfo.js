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

accountEmployee();

var employeeAccoutnId;
var employeePssAcc;

//Function to retrieve employee account information for employee user
async function accountEmployee(){
	console.log("First function");
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employeeAccount');
	let data = await res.json();
	//let data1 = JSON.stringify(data);
	console.log(data);
	console.log("First function");
	populateAccount(data);
}

//function to populate the account update fields
function populateAccount(data){
	//e.preventDefault();
		const keys = Object.keys(data);
		const length = keys.length;
//start at 1 to skip the userId key and length -2 to avoid las 2 key values
		for(let i = 1; i < length - 2; i++){
		    const key = keys[i]
		    document.getElementById(key).value = data[key];
		}
}
//Code above loads the fields for the account to be updated
//-----------------------------------------------------------------------------------------
//Code below to make the update request 

let form = document.getElementById("UpdateAccount").addEventListener('submit', updateInfo);

async function updateInfo(e){
	
	e.preventDefault();
	//alert('POST method');
	let fName = document.getElementById('fName').value;
	let lName = document.getElementById('lName').value;
	let email = document.getElementById('email').value;
	let username = document.getElementById('username').value;
	let newPassword = document.getElementById('newPassword').value;
	let cPassword = document.getElementById('passwordConfirm').value;
	
	let newUserInfo = {
		fName,
		lName,
		email,
		username,
		newPassword,
		cPassword
	};
	
	try{
	req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employeeAccount', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
		body: JSON.stringify(newUserInfo)
	});
	
	let res = await req.json();
	
	if(res.transactionStatus === 'Successful'){
		alert(res.message);
		location.href = '../html/EmployeeDashboard.html';
	}else{
	alert(res.message);
	}
	}catch(e){
		console.log('An error ocurred');
	}
}